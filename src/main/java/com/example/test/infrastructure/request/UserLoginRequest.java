package com.example.test.infrastructure.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserLoginRequest {
    private  String email;
    private  String password;
}
