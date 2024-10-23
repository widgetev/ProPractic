package org.example.service;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class.getName());
    private final RestTemplate restTemplate;
    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProductByUser(String paymentsUrl, @PathVariable Long userId) {
        //подготовить параметр
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("uid", userId.toString());

        //Сформировать uri
        return execPostRequest(paymentsUrl, urlParams);
    }

    public String getProductBypPidUid(String paymentsUrl, @PathVariable Long pid, @PathVariable Long uid) {

        //подготовить параметр
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("pid", pid.toString());
        urlParams.put("uid", uid.toString());

        return execPostRequest(paymentsUrl, urlParams);
    }
    public String getProductByUidAccnum(String paymentsUrl, @PathVariable Long uid, @PathVariable String accnum) {

        //подготовить параметр
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("uid", uid.toString());
        urlParams.put("accnum", accnum);

        return execPostRequest(paymentsUrl, urlParams);
    }

    @Nullable
    private String execPostRequest(String paymentsUrl, Map<String, String> urlParams) {
        //Сформировать uri
        String uri = UriComponentsBuilder.fromUriString(paymentsUrl)
                .buildAndExpand(urlParams).toString();
        log.info("request for {}", uri);
        return restTemplate.postForObject(uri, null, String.class);
    }
}
