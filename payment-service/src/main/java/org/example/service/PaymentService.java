package org.example.service;

import org.example.controller.PaymentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class.getName());
    private final RestTemplate restTemplate;
    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProductByUser(String paymentsUrl, @PathVariable Long userId) {

        //подготовить параметр
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", userId.toString());

        //Сформировать uri
        URI uri = UriComponentsBuilder.fromUriString(paymentsUrl)
                .buildAndExpand(urlParams).toUri();
        log.info("request for {}", uri);

        return restTemplate.postForObject(uri, null, String.class);
    }
}
