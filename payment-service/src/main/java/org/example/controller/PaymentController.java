package org.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.service.PaymentService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class.getName());

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/user/{userId}/products")
    public String getUserProducts(@Value("${service.products.byuser.url}") String paymentsUrl, @PathVariable Long userId){
        return paymentService.getProductByUser(paymentsUrl,userId);
    }
}
