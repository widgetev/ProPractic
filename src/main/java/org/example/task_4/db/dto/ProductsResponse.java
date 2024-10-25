package org.example.task_4.db.dto;

import org.example.task_4.db.Product;

import java.util.List;

public record ProductsResponse(List<Product> productsList) {
}
