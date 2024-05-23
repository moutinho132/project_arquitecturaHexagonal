package com.example.test.infrastructure.mapper;

import com.example.test.domain.models.User;
import com.example.test.infrastructure.request.UserRequest;
import com.example.test.infrastructure.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserApiMapper {

    UserResponse modelToResponse(final User model);

    User requestToModel(final UserRequest request);

    List<UserResponse> modelToResponseList(final List<User> users);
}
