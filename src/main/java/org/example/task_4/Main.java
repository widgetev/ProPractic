
package org.example.task_4;

import org.example.task_4.db.Users;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.sql.*;

@ComponentScan
public class Main {
        static UserService userService;
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(Main.class);

        userService = context.getBean(UserService.class);
        System.out.println("\n=======================================");
        Users vasya = userService.create("Vasya2");
        Users vasya2 = userService.create("Vasya1986");
        Users alien = userService.create("sadfadsfsq1990");

        System.out.println(userService.getAll());
        System.out.println("\n=======================================");

        System.out.println(userService.get(vasya2.getId()));
        System.out.println("\n=======================================");

        userService.del(vasya2);
        userService.del(vasya);

        System.out.println(userService.getAll());


    }


}
