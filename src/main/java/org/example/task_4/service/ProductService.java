package org.example.task_4.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.task_4.db.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(productDAO.get(id)).orElseThrow(EntityNotFoundException::new);
    }
    public List<Products> getByUserId(Long id) {
        log.info( "Call getByUserId id = " + id);
        return wrpapList(productDAO.getByUserId(id));
    }
    public List<Products> getByAccnum(Long uid, String accnum) {
        log.info( "Call getByAccNum = " + accnum);
        return wrpapList(productDAO.getByAccNum(uid, accnum));
    }

    public Products getByProductIdUserId(Long pid, Long uid) {
        log.info( "Call getByUserId id = " + uid);
        return Optional.ofNullable(productDAO.getByProductIdUserId(pid,uid)).orElseThrow(EntityNotFoundException::new);
    }

    public List<Products> getAll() {
        return wrpapList(productDAO.getAll());
    }

    List<Products> wrpapList(List<Products> productsList){
        return Optional.ofNullable(productsList)
                .filter(a -> !a.isEmpty())
                .orElseThrow(EntityNotFoundException::new);
    }
}
