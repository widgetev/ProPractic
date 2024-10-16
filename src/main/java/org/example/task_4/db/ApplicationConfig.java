package org.example.task_4.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class ApplicationConfig {

    //@Value("${service.username}")
    private String username = "postgres";
    //@Value("${service.password}")
    private  String password = "example";

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(org.postgresql.Driver.class.getName());
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/");
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setPoolName("springHikariCP");

        return new HikariDataSource(hikariConfig);
    }

//    @Bean
//    public Flyway flyway() {
//        Flyway flyway = Flyway.configure()
//                .dataSource(dataSource())
//                .locations("classpath:db/migration")
//                .load();
//        flyway.migrate();
//        return flyway;
//    }


}