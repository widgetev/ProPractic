package org.example.task_4.dto;

import java.util.List;

public record UsersDTO(Long id, String username, List<ProductDTO> products) {
    @Override
    public String toString() {
        return "UsersDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
