
package org.example.task_4;

import org.example.task_4.db.ProductType;
import org.example.task_4.db.Products;
import org.example.task_4.db.Users;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.math.BigDecimal;
import java.sql.*;

@ComponentScan
public class Main {
        static UserService userService;
        static ProductService productService;
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(Main.class);


        userService = context.getBean(UserService.class);
        productService = context.getBean(ProductService.class);
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


        System.out.println("\n\n\n=======================================");
        Products acc1 = productService.create("408178100999999", BigDecimal.valueOf(0.0), ProductType.ACC);
        Products acc2 = productService.create("2201000000000000 ", BigDecimal.valueOf(999999.9), ProductType.CARD);
        System.out.println("\n=======================================");
        System.out.println(productService.getAll());
        System.out.println("\n=======================================");

    }


}
