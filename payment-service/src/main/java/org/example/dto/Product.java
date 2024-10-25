package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class Product {
    private Long id;
    private String accNum;
    private BigDecimal sum;
    private String type;
    private Long userId;
}
