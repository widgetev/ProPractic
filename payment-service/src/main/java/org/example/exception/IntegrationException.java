package org.example.exception;

public class IntegrationException extends RuntimeException{
    private Exception5xxDTO exception5xxDTO;

   public IntegrationException(String message) {
        super(message);
    }

    public IntegrationException(String message, Exception5xxDTO exception5xxDTO) {
        super(message);
        this.exception5xxDTO = exception5xxDTO;
    }

    public Exception5xxDTO getException5xxDTO() {
        return exception5xxDTO;
    }

}
