package org.example.task_4.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Setter @Getter

@NoArgsConstructor
@EqualsAndHashCode
@Component
public class Products {

    private Long id;
    private String accNum;
    private BigDecimal sum;
    private ProductType type;

    public Products(String accNum, BigDecimal sum, ProductType type) {
        this.accNum = accNum;
        this.sum = sum;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", accNum='" + accNum + '\'' +
                ", sum=" + sum +
                ", type=" + type +
                '}';
    }
}
