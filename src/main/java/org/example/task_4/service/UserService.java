package org.example.task_4.service;

import lombok.extern.slf4j.Slf4j;
import org.example.task_4.db.entity.User;
import org.example.task_4.db.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User create(String username) {
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
        log.info("New user ID = {}", user.getId());
        return user;
    }
    User get(Long id) {
        return userRepository.getReferenceById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
