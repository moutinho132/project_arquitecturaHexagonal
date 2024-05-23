package com.example.test.domain.repository;

import com.example.test.domain.entities.CustomerEntity;
import com.example.test.domain.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer>,
        JpaSpecificationExecutor<TaskEntity> {
    List<TaskEntity> findByCustomer(final CustomerEntity customer);
}
