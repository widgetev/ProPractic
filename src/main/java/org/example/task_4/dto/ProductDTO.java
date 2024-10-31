package org.example.task_4.dto;

import java.math.BigDecimal;

public record ProductDTO(Long id, String accNum, BigDecimal sum, ProductTypeDTO type, Long userId) {

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", accNum='" + accNum + '\'' +
                ", sum=" + sum +
                ", type=" + type +
                ", userId=" + userId +
                '}';
    }
}
