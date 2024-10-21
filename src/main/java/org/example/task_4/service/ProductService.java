package org.example.task_4.service;

import org.example.task_4.db.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class.getName());
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
        log.info( "Call getByUserId id = " + id);
        return productDAO.getByUserId(id);
    }
    public List<Products> getAll() {
        return productDAO.getAll();
    }
}
