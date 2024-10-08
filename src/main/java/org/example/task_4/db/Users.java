package org.example.task_4.db;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter @Getter

@NoArgsConstructor
@EqualsAndHashCode
@Component
public class Users {

    private Long id;

    public Users(String username) {
        this.username = username;
    }

    private String username;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
