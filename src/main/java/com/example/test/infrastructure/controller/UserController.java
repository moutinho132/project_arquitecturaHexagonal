package com.example.test.infrastructure.controller;

import com.example.test.application.SecurityManagementService;
import com.example.test.infrastructure.api.UserApi;
import com.example.test.infrastructure.mapper.UserApiMapper;
import com.example.test.infrastructure.request.UserLoginRequest;
import com.example.test.infrastructure.request.UserRequest;
import com.example.test.infrastructure.response.UserNewResponse;
import com.example.test.infrastructure.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController implements UserApi {
    private final SecurityManagementService userManagerService;
    private final UserApiMapper userApiMapper;

    /*@Override
    public String userValidate(@RequestBody UserRequest request) {
        return userManagerService.validateUserLogin(request)?"Benvenido Estimado":"Usuario no autorizado";
    }*/

    @Override
    public UserNewResponse UserLogin(UserLoginRequest request) {
        return userManagerService.validateUserLoginApp(request);
    }

    @Override
    public UserResponse saveUser(@RequestBody UserRequest request) {
        return userApiMapper.modelToResponse(userManagerService.saveUser(userApiMapper.requestToModel(request)));
    }

    @Override
    public List<UserResponse> getAll() {
        return userApiMapper.modelToResponseList(userManagerService.getAll());
    }

    @Override
    public UserResponse findById(Integer id) {
        return userApiMapper.modelToResponse(userManagerService.findById(id));
    }

    @Override
    public boolean deleteUser(Integer id) {
        return userManagerService.delete(id);
    }

    @Override
    public UserResponse findCurrentUser(String token) {
        return  userApiMapper.modelToResponse(userManagerService.findCurrentUser(token));
    }
}
