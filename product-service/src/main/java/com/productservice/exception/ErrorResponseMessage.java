package com.productservice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponseMessage {
    private String message;
    private String status;
    private LocalDateTime timestamp;
    public ErrorResponseMessage(String message, String status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
