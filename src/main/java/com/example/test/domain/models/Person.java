package com.example.test.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class Person {

    private final Integer id;

    @With
    private final String name;

    @With
    private final String surname;
    @With
    private String dni;

    private final LocalDate dateOfBirth;


}
