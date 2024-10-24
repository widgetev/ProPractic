package org.example.task_4.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.task_4.db.dto.ErrorResponse;
import org.example.task_4.db.dto.ProductsResponse;
import org.example.task_4.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }
    @RequestMapping(value = "/")
    public ProductsResponse  getAll() {
        log.info("Call getAll()");
        return new ProductsResponse(productService.getAll());
    }
    @RequestMapping(value = "/{id}")
    public ProductsResponse get(@PathVariable Long id) {
        log.info("Call get product by id = " + id);
        return new ProductsResponse(List.of(productService.get(id)));
    }
    @RequestMapping(value = "/user/{id}")
    public ProductsResponse getByUser(@PathVariable Long id) {
        log.info("Call get ALL products by user ID = " + id);
        return new ProductsResponse((productService.getByUserId(id)));

    }

    @RequestMapping(value = "/user/{uid}/accnum/{accnum}")
    public ProductsResponse getByUser(@PathVariable Long uid, @PathVariable String accnum) {
        log.info("Call get user products by num  {} {} }", uid, accnum );
        return new ProductsResponse(productService.getByAccnum(uid,accnum));

    }
    @RequestMapping(value = "/{pid}/user/{uid}")
    public ProductsResponse getByUser(@PathVariable Long pid, @PathVariable Long uid) {
        log.info("Call get ALL products by user ID = " + uid);
        return new ProductsResponse(List.of(productService.getByProductIdUserId(pid, uid)));

    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handlerEntityNotFoundException(EntityNotFoundException e){
        return  new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Почему-то не нашлось");
    }

}
