package com.example.test.application;

import com.example.test.application.excepcion.BusinessRuleException;
import com.example.test.application.excepcion.FieldIsMissingException;
import com.example.test.application.excepcion.PersonManagementService;
import com.example.test.domain.models.Customer;
import com.example.test.domain.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerManagementService {
    private final CustomerService customerService;
    private final SecurityManagementService securityManagementService;
    private final PersonManagementService personManagementService;

    @Transactional
    public Customer saveCustomer(final Customer model,final String token) {
        validPerson(model,token);
      return customerService.save(build(model,token));
    }


    @Transactional
    public List<Customer> findAll(final Integer page, final Integer size, final String direction, final String attribute) {
        final Sort.Direction directionEnum =
                Arrays.stream(Sort.Direction.values()).anyMatch(v -> v.name().equalsIgnoreCase(direction))
                        ? Sort.Direction.fromString(direction)
                        : Sort.Direction.ASC;
        final Sort sort = Sort.by(directionEnum, attribute);
        final Pageable pageable = PageRequest.of(page, size, sort);
        return customerService.findAll( pageable).stream()
                .collect(Collectors.toList());
    }
    private void validPerson(final Customer model,final String token) {
        if (Objects.isNull(model)) {
            throw new FieldIsMissingException("Person is empty");
        }
        if (Objects.nonNull(model.getId())
                && model.getId() != 0
                && (Objects.isNull(model.getPerson().getId()) || model.getPerson().getId() == 0)) {
                throw new BusinessRuleException("Person id is mandatory");
        }
        if (Objects.nonNull(model.getId())) {
            final Customer customer = customerService.findById(model.getId(),token);
            if (model.getPerson().getId().intValue() != customer.getPerson().getId()) {
                throw new BusinessRuleException("PERSON_ID_DOES_NOT_CORRESPOND_MESSAGE");
            }
        }
    }

    private Customer build(final Customer model,final String token) {
        final AtomicReference<Customer> built = new AtomicReference<>(model);
        built.set(buildPerson(built.get()));
        built.set(buildCreationData(built.get(),token));
        built.set(buildModificationData(built.get(),token));
        return built.get();
    }

    private Customer buildPerson(final Customer model) {
        if (!Objects.isNull(model.getPerson())) {
            return model.withPerson(personManagementService.save(model.getPerson()));
        }
        return model;
    }

    private Customer buildCreationData(final Customer model,final String token) {
        if (Objects.isNull(model.getCreationTime())) {
            return model
                    .withCreationTime(LocalDateTime.now())
                    .withCreationUser(securityManagementService.findCurrentUser(token));
        }
        return model;
    }

    private Customer buildModificationData(final Customer model,final String token) {
        if (!Objects.isNull(model.getId())) {
            return model
                    .withModificationTime(LocalDateTime.now())
                    .withModificationUser(securityManagementService.findCurrentUser(token));
        }
        return model;
    }

}
