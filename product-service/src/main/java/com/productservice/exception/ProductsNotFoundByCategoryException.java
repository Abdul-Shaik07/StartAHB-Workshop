package com.productservice.exception;

public class ProductsNotFoundByCategoryException extends RuntimeException {
    public ProductsNotFoundByCategoryException(String message) {
        super(message);
    }
}
