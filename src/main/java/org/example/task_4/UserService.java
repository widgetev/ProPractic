package org.example.task_4;

import org.example.task_4.db.UserDAO;
import org.example.task_4.db.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    Users create(String username) {
        Users user = new Users(username);
        userDAO.save(user);
        return user;
    }

    void del(Users user) {
        userDAO.delete(user);
    }

    Users get(Long id) {
        return userDAO.get(id);
    }

    public List<Users> getAll() {
        return userDAO.getAll();
    }
}
