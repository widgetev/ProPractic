package org.example.task_4.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    private final String username;
    private final String password;
    private final String url;
    public ApplicationConfig( @Value("${spring.datasource.username}") String username
            , @Value("${spring.datasource.password}") String password
            , @Value("${spring.datasource.url}") String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }


    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(org.postgresql.Driver.class.getName());
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setPoolName("springHikariCP");

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(dataSource())
                .locations("classpath:db/migration")
                .load();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }


}