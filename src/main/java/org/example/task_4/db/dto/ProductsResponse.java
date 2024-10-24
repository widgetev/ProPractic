package org.example.task_4.db.dto;

import org.example.task_4.db.Products;

import java.util.List;

public record ProductsResponse(List<Products> productsList) {
}
