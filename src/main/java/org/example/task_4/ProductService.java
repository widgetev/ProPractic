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
    Products create(String accnum, BigDecimal sum, ProductType type, Long userId) {
        Products products = new Products(accnum, sum, type, userId);
        productDAO.save(products);
        return products;
    }

    void del(Products p) {
        productDAO.delete(p);
    }

    public Products get(Long id) {
        return productDAO.get(id);
    }
    public List<Products> getByUserId(Long id) {
        return productDAO.getByUserId(id);
    }
    List<Products> getAll() {
        return productDAO.getAll();
    }
}
