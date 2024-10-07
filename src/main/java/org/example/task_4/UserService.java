package org.example.task_4;

import org.example.task_4.db.UserDAO;
import org.example.task_4.db.Users;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    Users create(String username) throws SQLException {
        Users user = new Users(username);
        userDAO.save(user);
        return user;
    }

    void del(Users user) throws SQLException {
        userDAO.delete(user);
    }

    Users get(Long id) throws SQLException {
        return userDAO.get(id);
    }

    List<Users> getAll() throws SQLException {
        return userDAO.getAll();
    }
}
