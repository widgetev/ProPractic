package org.example.task_4.controller;

import org.example.task_4.service.ProductService;
import org.example.task_4.db.Products;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {
    private static final Logger log = LoggerFactory.getLogger(ProductsController.class.getName());
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }
    @RequestMapping(value = "/")
    public List<Products>  getAll() {
        log.info("Call getAll()");
        return productService.getAll();
    }
    @RequestMapping(value = "/{id}")
    public Products get(@PathVariable Long id) {
        log.info("Call get product by id = " + id);
        return productService.get(id);
    }
    @RequestMapping(value = "/user/{id}")
    public List<Products> getByUser(@PathVariable Long id) {
        log.info("Call get ALL products by user ID = " + id);
        return productService.getByUserId(id);

    }

    @RequestMapping(value = "/user/{uid}/accnum/{accnum}")
    public List<Products> getByUser(@PathVariable Long uid, @PathVariable String accnum) {
        log.info("Call get user products by num  {} {} }", uid, accnum );
        return productService.getByAccnum(uid,accnum);

    }
    @RequestMapping(value = "/{pid}/user/{uid}")
    public Products getByUser(@PathVariable Long pid, @PathVariable Long uid) {
        log.info("Call get ALL products by user ID = " + uid);
        return productService.getByProductIdUserId(pid, uid);

    }
}
