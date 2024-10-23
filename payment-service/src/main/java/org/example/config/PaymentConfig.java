package org.example.config;

import lombok.Getter;
import org.example.properties.ProductsUrl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@ConfigurationProperties(prefix = "service.integrations.payment-service")
public class PaymentConfig {

    private final ProductsUrl productsUrl ;

    public PaymentConfig(ProductsUrl productsUrl) {
        this.productsUrl = productsUrl;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .rootUri(productsUrl.getBase())
                .build();
    }

    public String getProductsByUserURL() {
        return productsUrl.getUser();
    }

    public String getURLProductBypPidUid() {
        return productsUrl.getPid()
                + productsUrl.getUser();
    }
    public String getProductByUidAccnum() {
        return productsUrl.getUser()
                + productsUrl.getAccnum();
    }

}
