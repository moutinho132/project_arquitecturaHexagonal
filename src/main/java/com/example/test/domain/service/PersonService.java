package com.example.test.domain.service;

import com.example.test.application.excepcion.PersonNotFoundException;
import com.example.test.domain.entities.PersonEntity;
import com.example.test.domain.mapper.PersonMapper;
import com.example.test.domain.models.Person;
import com.example.test.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
@Service
public class PersonService {
    private PersonRepository repository;
    private PersonMapper mapper;

    public Person save(final Person model) {
        if (Objects.requireNonNullElse(model.getId(), 0) != 0) {
            existsById(model.getId());
        }

        final PersonEntity entity = mapper.modelToEntity(model);
        return mapper.entityToModel(repository.save(entity));
    }

    public List<Person> findAll() {
        return mapper.entitiesToModelList(repository.findAll());
    }
    public void deleteById(final Integer id) {
        existsById(id);

        repository.deleteById(id);
    }

    public void existsById(final Integer id) {
        if (!repository.existsById(id)) {
            throw new PersonNotFoundException(id);
        }
    }

    public Person findById(final Integer id) {
        return mapper.entityToModel(repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id)));
    }
}
