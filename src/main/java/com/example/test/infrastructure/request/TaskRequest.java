package com.example.test.infrastructure.request;

import com.example.test.domain.models.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TaskRequest {
    private Integer id;
    private String description;
    private String name;
    private Customer customer;
}
