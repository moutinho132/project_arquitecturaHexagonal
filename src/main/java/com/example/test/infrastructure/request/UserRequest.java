package com.example.test.infrastructure.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequest {
    private  Integer id;
    private  String name;
    private  String surname;
    private  String email;
    private  String fullName;
    private String password;
}
