package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.example.config.PaymentConfig;
import org.example.dto.PaymentDTO;
import org.example.dto.PaymentResponse;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.service.PaymentService;
@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ProductService productService;
    public PaymentController(PaymentService paymentService, ProductService productService) {
        this.paymentService = paymentService;
        this.productService = productService;
    }

    /**
     * Эти методы нужны чтобы "запрашивать продукты у платежного сервиса" в разных вариациях :
     * getUserProducts - все продукты пользователя
     * getProductByIdUIserId(pid, uid) - по id продукта и id пользователя
     * getProductByIdUIserId(uid, AccNum) - по id пользователя и номеру счета/карты
     */
    @GetMapping("/user/{userId}/products")
    public PaymentResponse getUserProducts(@PathVariable Long userId) throws JsonProcessingException {
        PaymentResponse response= productService.getProductByUser(userId);
        return response;
    }

    //Выбор продукта. Т.е. ищем конкретный продукт по конкретному пользователю
    //это же запрос проверить наличие такого продукта?
    @GetMapping("/product/{pid}/user/{uid}")
    public PaymentResponse getProductByIdUIserId(@PathVariable Long pid, @PathVariable Long uid){
        return productService.getProductBypPidUid(pid,uid);
        //return new PaymentResponse(HttpStatus.OK.name(), "",paymentService.getProductBypPidUid(pid,uid));
    }
    //это же запрос проверить наличие такого продукта
    @GetMapping("/product/user/{uid}/accnum/{accnum}")
    public PaymentResponse getProductByIdUIserId(@PathVariable Long uid, @PathVariable String accnum){
        return productService.getProductByUidAccnum(uid, accnum);
    }

    /**
     * Это метод оплаты
     */
    @PostMapping("/")
    public PaymentResponse doPayment(@RequestBody PaymentDTO payment) {
        return paymentService.doPayment(payment);
    }
}
