package com.example.test.application;

import com.example.test.domain.TaskStatusEnum;
import com.example.test.domain.models.Customer;
import com.example.test.domain.models.Task;
import com.example.test.domain.service.CustomerService;
import com.example.test.domain.service.TaskService;
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
public class TaskManagementService {
    private final SecurityManagementService securityManagementService;
    private final TaskService service;
    private final CustomerService customerService;

    @Transactional
    public Task saveTask(final Task model, final String token) {
        validateCustomer(model);
        return service.save(build(model,token));
    }

    @Transactional
    public List<Task> findAll(final Integer page, final Integer size, final String direction, final String attribute) {
        final Sort.Direction directionEnum =
                Arrays.stream(Sort.Direction.values()).anyMatch(v -> v.name().equalsIgnoreCase(direction))
                        ? Sort.Direction.fromString(direction)
                        : Sort.Direction.DESC;
        final Sort sort = Sort.by(directionEnum, attribute);
        final Pageable pageable = PageRequest.of(page, size, sort);
        return service.findAll( pageable).stream()
                .collect(Collectors.toList());
    }

    public Task findById(final Integer id,final String token) {
        return service.findById(id,token);
    }
    public void deleteById(final Integer id) {
         service.deleteById(id);
    }

    public List<Task> findByCustomer(final Integer id,final String token) {
        customerService.existsById(id);
        Customer customer = customerService.findById(id,token);
        return service.findByCustomer(customer,token);
    }

    @Transactional
    public Task updateCompletedTask(final Integer id, final String token) {
        Task taskUpdate = null ;
        service.existsById(id);
        final Task task = service.findById(id,token);
       return service.save(Task
               .builder()
                       .id(task.getId())
                       .modificationUser(securityManagementService.findCurrentUser(token))
                       .modificationTime(LocalDateTime.now())
                       .creationUser(task.getCreationUser())
                       .creationTime(task.getCreationTime())
                       .customer(task.getCustomer())
                       .description(task.getDescription())
                       .name(task.getName())
                       .state(TaskStatusEnum.COMPLETED)
               .build());
    }

    private void validateCustomer(Task model) {
        customerService.existsById(model.getCustomer().getId());
    }

    private Task build(final Task model,final String token) {
        final AtomicReference<Task> built = new AtomicReference<>(model);
        built.set(buildCreationData(built.get(),token));
        built.set(buildModificationData(built.get(),token));
        built.set(buildCustomer(built.get(),token));
        built.set(buildStatus(built.get()));
        return built.get();
    }

    private Task buildCreationData(final Task model,final String token) {
        if (Objects.isNull(model.getCreationTime())) {
            return model
                    .withCreationTime(LocalDateTime.now())
                    .withCreationUser(securityManagementService.findCurrentUser(token));
        }
        return model;
    }

    private Task buildStatus(final Task model) {
        if (Objects.isNull(model.getState())) {
            return model.withState(TaskStatusEnum.ACCEPTED);
        }
        return model;
    }

    private Task buildCustomer(final Task model,final String token) {
        if (Objects.nonNull(model.getCustomer())) {
           Customer customer = customerService.findById(model.getCustomer().getId(),token);
            return model
                    .withCustomer(customer);
        }
        return model;
    }

    private Task buildModificationData(final Task model,final String token) {
        if (!Objects.isNull(model.getId())) {
            return model
                    .withModificationTime(LocalDateTime.now())
                    .withModificationUser(securityManagementService.findCurrentUser(token));
        }
        return model;
    }
}
