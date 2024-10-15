package org.example.task_4;

import org.example.task_4.db.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    Products create(String accnum, BigDecimal sum, ProductType type) {
        Products products = new Products(accnum, sum, type);
        productDAO.save(products);
        return products;
    }

    void del(Products user) {
        productDAO.delete(user);
    }

    Products get(Long id) {
        return productDAO.get(id);
    }

    List<Products> getAll() {
        return productDAO.getAll();
    }
}
