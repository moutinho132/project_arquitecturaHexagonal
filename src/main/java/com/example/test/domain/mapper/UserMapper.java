package com.example.test.domain.mapper;

import com.example.test.domain.entities.UserEntity;
import com.example.test.domain.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User entityToModel(UserEntity entity);

    UserEntity modelToEntity(User model);

    List<User> modelToList(List<UserEntity> userEntities);
}
