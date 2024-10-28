package org.example.dto;

import java.math.BigDecimal;

public record PaymentDTO(Long userId, Long productId, String accNum, BigDecimal sum) {
    @Override
    public String toString() {
        return "PaymentDTO{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", accNum='" + accNum + '\'' +
                ", sum=" + sum +
                '}';
    }
}
