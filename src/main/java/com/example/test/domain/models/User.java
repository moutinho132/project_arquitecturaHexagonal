package com.example.test.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@AllArgsConstructor
@Getter
@Builder
public class User {

    private final Integer id;
    @With
    private final String name;
    @With
    private final String surname;
    @With
    private final String email;
    @With
    private final String fullName;
    @With
    private final String password;

}
