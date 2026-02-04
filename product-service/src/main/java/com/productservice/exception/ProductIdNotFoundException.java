package com.productservice.exception;

public class ProductIdNotFoundException extends RuntimeException {
    public ProductIdNotFoundException(String message) {
        super(message);
    }
}
