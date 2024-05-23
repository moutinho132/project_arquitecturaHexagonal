package com.example.test.application.excepcion;

import com.example.test.domain.models.Person;
import com.example.test.domain.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@AllArgsConstructor
public class PersonManagementService {
    private final PersonService personService;

    @Transactional
    public Person save(final Person model) {
        validations(model);

        return personService.save(build(model));
    }

    private Person build(final Person model) {
        final AtomicReference<Person> built = new AtomicReference<>(generateId(model));
        return built.get();
    }

    private void  validations(final Person model) {
        validId(model.getId());
    }

    private void validId(final Integer id) {
        if (Objects.nonNull(id)) {
            personService.existsById(id);
        }
    }

    private Person generateId(final Person model) {
        if (Objects.isNull(model.getId())) {
            return personService
                    .save(model);
        }
        return model;
    }

}
