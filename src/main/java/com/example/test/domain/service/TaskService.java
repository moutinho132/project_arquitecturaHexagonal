package com.example.test.domain.service;

import com.example.test.application.excepcion.CustomerNotFoundException;
import com.example.test.application.excepcion.TaskNotFoundException;
import com.example.test.domain.mapper.CustomerMapper;
import com.example.test.domain.mapper.TaskMapper;
import com.example.test.domain.models.Customer;
import com.example.test.domain.models.Task;
import com.example.test.domain.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;
    private final CustomerMapper customerMapper;

    public Task save(final Task model) {
        if (Objects.requireNonNullElse(model.getId(), 0) != 0) {
            existsById(model.getId());
        }
        return mapper.entityToModel(repository.save(mapper.modelToEntity(model)));
    }

    public List<Task> findAll(final Pageable pageable) {
        return mapper.entitiesToModelList(repository
                .findAll(pageable)
                .toList());
    }

    public Task findById(final Integer id, final String token) {
        return mapper.entityToModel(repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id)));
    }

    public void deleteById(final Integer id) {
        existsById(id);
        repository.deleteById(id);
    }

    public List<Task> findByCustomer(final Customer customer, final String token) {
        return mapper.entitiesToModelList(repository.findByCustomer(customerMapper.modelToEntity(customer)));
    }

    public void existsById(final Integer id) {
        if (!repository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
    }
}
