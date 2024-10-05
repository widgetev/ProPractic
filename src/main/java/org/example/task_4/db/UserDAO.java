package org.example.task_4.db;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {

    private Users user;

    Users newUser(){
        return new Users();
    }

}
