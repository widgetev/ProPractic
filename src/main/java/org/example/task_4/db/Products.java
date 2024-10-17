package org.example.task_4.db;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Setter @Getter

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
public class Products {

    private Long id;
    private String accNum;
    private BigDecimal sum;
    private ProductType type;
    private Long userId;
    public Products(String accNum, BigDecimal sum, ProductType type) {
        this.accNum = accNum;
        this.sum = sum;
        this.type = type;
    }
    public Products(String accNum, BigDecimal sum, ProductType type, Long userId) {
        this.accNum = accNum;
        this.sum = sum;
        this.type = type;
        this.userId = userId;
    }

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
