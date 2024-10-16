
package org.example.task_4;

import org.example.task_4.db.ApplicationConfig;
import org.example.task_4.db.ProductType;
import org.example.task_4.db.Products;
import org.example.task_4.db.Users;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.math.BigDecimal;
import java.sql.*;

@SpringBootApplication
//@EnableConfigurationProperties(ApplicationConfig.class)
public class Main {
        static UserService userService;
        static ProductService productService;
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class,args);

        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(Main.class);
        userService = context.getBean(UserService.class);
        productService = context.getBean(ProductService.class);
        System.out.println("\n=======================================");

        System.out.println(userService.getAll());


        System.out.println("\n\n\n=======================================");
        for (int i = 0; i < 3; i++) {
            productService.create("4081781010000010000" + i, BigDecimal.valueOf(0.0), ProductType.ACC, 2L);
            productService.create("220100000000000" + i, BigDecimal.valueOf(999999.9), ProductType.CARD, (long) i);
        }

        productService.create("2201000000000003 ", BigDecimal.valueOf(999999.9), ProductType.CARD);
        productService.create("2201000000000004 ", BigDecimal.valueOf(999999.9), ProductType.CARD);

        System.out.println("\n=======================================");
        System.out.println(productService.getAll());
        System.out.println("\n=======================================");



    }


}
