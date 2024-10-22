package org.example.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
//@EnableConfigurationProperties
public class PaymentConfig {
    private final PaymentURL paymentsUrl;

    public PaymentConfig(@Value("${service.products.url}") String paymentsUrl) {
        this.paymentsUrl = new PaymentURL(paymentsUrl);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    class PaymentURL{
        private final String value;

        public PaymentURL(String value) {
            this.value = value;
        }
    }
}
