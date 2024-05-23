package com.example.test.domain.service;

import com.example.test.domain.entities.UserEntity;
import com.example.test.domain.mapper.UserMapper;
import com.example.test.domain.models.User;
import com.example.test.domain.repository.UserRepository;
import com.example.test.infrastructure.request.UserLoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository repository;
    /*private User user = new User();

    public boolean validateUser(String login, String password){
        if(user.validateUserLogin(login, password)){
            System.out.println("Bienvenido User");
            return true;
        }
        return false;
    }*/

    public User saveUser(final User model) {
        /**
         * User user=null;
         *         UserEntity entity = userMapper.modelToEntity(model);
         *         UserEntity userSave = repository.save(entity);
         *         if(userSave!=null){
         *             user =userMapper.entityToModel(userSave);
         *         }
         *         return user;
         */
        return userMapper.entityToModel(repository.save(userMapper.modelToEntity(model)));
    }

    public List<User> getAll(){
       return userMapper.modelToList(repository.findAll());
    }

    public User findById(final Integer id){
        User user=null;
        if(Objects.nonNull(id)){
            Optional<UserEntity> resultUserEntity = repository.findById(id);
            if(resultUserEntity.isPresent()){
                user =  userMapper.entityToModel(resultUserEntity.get());
            }
        }
        return user;
    }

    public User findByEmail(final String email){
        User user=null;
        if(Objects.nonNull(email)){
            Optional<UserEntity> resultUserEntity = repository.findByEmail(email);
            if(resultUserEntity.isPresent()){
                user =  userMapper.entityToModel(resultUserEntity.get());
            }
        }
        return user;
    }



    public User findByEmailAndPassword(final UserLoginRequest request){
        User user=null;
        if(Objects.nonNull(request.getEmail()) && Objects.nonNull(request.getEmail())){
            Optional<UserEntity> resultUserEntity = repository.findByEmailAndPassword(request.getEmail(),request.getPassword());
            if(resultUserEntity.isPresent()){
                user =  userMapper.entityToModel(resultUserEntity.get());
            }
        }
        return user;
    }

    public boolean deleteUser(final Integer id){
        User user = findById(id);
        if(user != null ){
            repository.delete(userMapper.modelToEntity(user));
            return true;
        }
        return false;
    }

}
