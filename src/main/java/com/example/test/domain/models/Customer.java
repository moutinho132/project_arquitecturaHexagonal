package com.example.test.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class Customer {

    private final Integer id;

    @With
    private final Person person;

    @With
    private final User creationUser;

    @With
    private final User modificationUser;

    @With
    private final LocalDateTime creationTime;

    @With
    private final LocalDateTime modificationTime;
}
