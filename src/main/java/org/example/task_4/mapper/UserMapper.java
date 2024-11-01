package org.example.task_4.mapper;

import org.example.task_4.db.entity.Users;
import org.example.task_4.db.repository.UserRepository;
import org.example.task_4.dto.UsersDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestBodyMapperInterface<UsersDTO, Users> {
    private final UserRepository userRepository; //Не уверен, что правильно здесь репозиторий использовать

    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users map(UsersDTO request) {
        if(request.id() != null) {
            return userRepository.findById(request.id()).orElseThrow(() ->{throw  new RuntimeException("Пользователь не найден :" + request.id());});
        }
        throw  new RuntimeException("Необходимо указать пользователя" );
    }

    public Users mapById(Long id) {
        if(id!= null) {
            return userRepository.findById(id).orElseThrow(() ->{throw  new RuntimeException("Пользователь не найден :" + id);});
        }
        throw  new RuntimeException("Необходимо указать пользователя" );
    }
}
