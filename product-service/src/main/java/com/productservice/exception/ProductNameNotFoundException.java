package com.productservice.exception;

public class ProductNameNotFoundException extends RuntimeException {
    public ProductNameNotFoundException(String message) {
        super(message);
    }
}
