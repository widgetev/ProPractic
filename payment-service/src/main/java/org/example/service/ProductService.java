package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.example.config.PaymentConfig;
import org.example.dto.PaymentResponse;
import org.example.dto.Product;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Работает с запросами по продуктам
 */
@Slf4j
@Service
public class ProductService {
    private final PaymentConfig config;
    private final RestTemplate restTemplate;

    public ProductService(PaymentConfig config, RestTemplate restTemplate) {
        this.config = config;
        this.restTemplate = restTemplate;
    }

    public PaymentResponse getProductByUser(Long userId) throws JsonProcessingException {
        //подготовить параметр
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("uid", userId.toString());
        return restTemplate.postForObject(config.getProductsByUserURL(), null, PaymentResponse.class,urlParams);
    }

    public PaymentResponse getProductBypPidUid(Long pid, Long uid) {
        //подготовить параметр
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("pid", pid.toString());
        urlParams.put("uid", uid.toString());
        return restTemplate.postForObject(config.getURLProductBypPidUid(),null, PaymentResponse.class,urlParams);
    }
    public PaymentResponse getProductByUidAccnum(Long uid, String accnum) {
        //подготовить параметр
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("uid", uid.toString());
        urlParams.put("accnum", accnum);
        return restTemplate.postForObject(config.getProductByUidAccnum(),null, PaymentResponse.class,urlParams);
    }

    public PaymentResponse updateProduct(Product product) {
        //подготовить параметр
        HttpEntity<Product> request = new HttpEntity<>(product);
        return restTemplate.postForObject(config.getUpdateURL() ,request, PaymentResponse.class);
    }

}
