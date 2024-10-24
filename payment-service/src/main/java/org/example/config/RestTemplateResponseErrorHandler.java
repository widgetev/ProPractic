package org.example.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.Exception5xxDTO;
import org.example.exception.IntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.HashMap;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class.getName());
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        log.info(" Response in Handler {} \n {} ", response.getStatusCode(), response.getStatusText());
        return response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if(response.getStatusCode().is4xxClientError()) {
            throw new IntegrationException("Продукт не найден");
        } else if(response.getStatusCode().is5xxServerError()) {
            ObjectMapper mapper = new ObjectMapper();
            Exception5xxDTO map = mapper.readValue(response.getBody(),Exception5xxDTO.class);
            throw new IntegrationException("Ошибочка", map);
        }

    }
}
