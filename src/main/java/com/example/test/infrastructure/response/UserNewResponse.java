package com.example.test.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserNewResponse {

    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private String token;
}
