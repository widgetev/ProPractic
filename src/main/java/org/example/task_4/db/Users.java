package org.example.task_4.db;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter @Getter

@NoArgsConstructor
@EqualsAndHashCode
@Component
public class Users {

    private Long id;
    private String username;
    private List<Products> products;
    public Users(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
