package com.example.test.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse implements Serializable {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String email;
    private final String fullName;
    private final String password;
}
