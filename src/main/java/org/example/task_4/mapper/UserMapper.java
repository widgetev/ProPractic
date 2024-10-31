package org.example.task_4.mapper;

import org.example.task_4.db.entity.Users;
import org.example.task_4.db.repository.UserRepository;
import org.example.task_4.dto.UsersDTO;

import java.util.Objects;

public class UserMapper implements RequestBodyMapperInterface<UsersDTO, Users> {
    UserRepository userRepository; //Не уверен, что правильно здесь репозиторий использовать
    @Override
    public Users map(UsersDTO request) {
        if(request.id() != null) {
            return userRepository.findById(request.id()).orElse(null);
        }
        return null;
    }

    public Users mapById(Long id) {
        if(id!= null) {
            return userRepository.findById(id).orElse(null);
        }
        return null;
    }
}
