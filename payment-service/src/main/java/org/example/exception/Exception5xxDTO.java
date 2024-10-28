package org.example.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Exception5xxDTO {
    private String path;
    private String error;
    private String status;
    private Timestamp timestamp;


}
