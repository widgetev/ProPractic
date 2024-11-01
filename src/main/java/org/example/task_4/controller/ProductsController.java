package org.example.task_4.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.task_4.dto.ErrorResponse;
import org.example.task_4.dto.ProductDTO;
import org.example.task_4.dto.ProductsResponse;
import org.example.task_4.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

    @PostMapping(value = "/")
    public ProductsResponse updateProduct(@RequestBody ProductDTO product) throws SQLException {
        log.info("update ProductDTO = " + product);
        return new ProductsResponse(List.of(productService.update(product)));
    }

    @PostMapping(value = "/create")
    public ProductsResponse createProduct(@RequestBody ProductDTO product) throws SQLException {
        log.info("create ProductDTO = " + product);
        return new ProductsResponse(List.of(productService.create(product)));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handlerEntityNotFoundException(EntityNotFoundException e){
        return  new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Продукт не найден");
    }
}
