package org.example.controller;

import org.example.config.PaymentConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    //private static final Logger log = LoggerFactory.getLogger(PaymentController.class.getName());
    private final PaymentConfig config;

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService, PaymentConfig config) {
        this.paymentService = paymentService;
        this.config = config;
    }

    @GetMapping("/user/{userId}/products")
    public String getUserProducts(@PathVariable Long userId){
        return paymentService.getProductByUser(config.getProductsByUserURL(),userId);
    }

     //Выбор продукта. Т.е. ищем конкретный продукт по конкретному пользователю
    //это же запрос проверить наличие такого продукта?
    @GetMapping("/product/{pid}/user/{uid}")
    public String getProductByIdUIserId(@PathVariable Long pid, @PathVariable Long uid){
        return paymentService.getProductBypPidUid(config.getURLProductBypPidUid(),pid,uid);
    }
    //это же запрос проверить наличие такого продукта
    @GetMapping("/product/user/{uid}/accnum/{accnum}")
    public String getProductByIdUIserId(@PathVariable Long uid, @PathVariable String accnum){
        return paymentService.getProductByUidAccnum(config.getProductByUidAccnum(),uid, accnum);
    }
}
