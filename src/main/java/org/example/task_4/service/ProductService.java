package org.example.task_4.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.task_4.db.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class.getName());
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    Product create(String accnum, BigDecimal sum, ProductType type) {
        Product product = new Product(accnum, sum, type);
        productDAO.save(product);
        return product;
    }
    Product create(String accnum, BigDecimal sum, ProductType type, Long userId) {
        Product product = new Product(accnum, sum, type, userId);
        productDAO.save(product);
        return product;
    }

    void del(Product p) {
        productDAO.delete(p);
    }

    public Product get(Long id) {
        return Optional.ofNullable(productDAO.get(id)).orElseThrow(EntityNotFoundException::new);
    }
    public List<Product> getByUserId(Long id) {
        log.info( "Call getByUserId id = " + id);
        return wrapList(productDAO.getByUserId(id));
    }
    public List<Product> getByAccnum(Long uid, String accnum) {
        log.info( "Call getByAccNum = " + accnum);
        return wrapList(productDAO.getByAccNum(uid, accnum));
    }

    public Product getByProductIdUserId(Long pid, Long uid) {
        log.info( "Call getByUserId id = " + uid);
        return Optional.ofNullable(productDAO.getByProductIdUserId(pid,uid)).orElseThrow(EntityNotFoundException::new);
    }
    public List<Product> getAll() {
        return wrapList(productDAO.getAll());
    }

    public Product update(Product product) throws SQLException {
        log.info( "update Product : {}  ", product);
        return Optional.ofNullable(productDAO.update(product)).orElseThrow(EntityNotFoundException::new);
    }

    List<Product> wrapList(List<Product> productsList){
        return Optional.ofNullable(productsList)
                .filter(a -> !a.isEmpty())
                .orElseThrow(EntityNotFoundException::new);
    }
}
