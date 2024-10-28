package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.PaymentDTO;
import org.example.dto.PaymentResponse;
import org.example.dto.Product;
import org.example.exception.PaymentParamException;
import org.example.exception.ProductNotFoundException;
import org.example.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Выполняет платеж. При необходимости обращается к продуктовому сервису
 */
@Slf4j
@Service
public class PaymentService {

    private final ProductService productService;

    public PaymentService(ProductService productService) {
        this.productService = productService;
    }

    public PaymentResponse doPayment(PaymentDTO payment) {
        if(payment.userId()==null || (payment.productId()==null && payment.accNum() == null) || payment.sum() == null)
            throw new PaymentParamException("Не верные параметры платежа");

        List<Product> productsList = this.productService.getProductBypPidUid(payment.productId(), payment.userId()).getProductsList();
        if(productsList == null || productsList.size() != 1)
            throw new ProductNotFoundException("Product not found");

        Product product = productsList.get(0);
        BigDecimal balance = product.getSum();

        if(Objects.isNull(balance) || balance.compareTo(payment.sum()) < 0) {
            throw new RequestException(HttpStatus.BAD_REQUEST, "Low balance");
        }

        product.setSum(balance.subtract(payment.sum()));

        this.productService.updateProduct(product);

        return new PaymentResponse(List.of(product));
    }
}
