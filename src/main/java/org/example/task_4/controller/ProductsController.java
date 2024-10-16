package org.example.task_4.controller;

import org.example.task_4.ProductService;
import org.example.task_4.UserService;
import org.example.task_4.db.Products;
import org.example.task_4.db.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/{id}")
    public Products get(@PathVariable Long id) {
        return productService.get(id);

    }

    @RequestMapping(value = "/", params = "userId")
    public Products get(@RequestParam String userId) {
        return productService.get(Long.getLong(userId));

    }
}
