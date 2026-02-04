package com.productservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductIdNotFoundException.class)
    public ResponseEntity<ErrorResponseMessage> productIdNotFoundException(ProductIdNotFoundException productIdNotFoundException) {
        return ResponseEntity.ok(new ErrorResponseMessage(productIdNotFoundException.getMessage(), "NOT_FOUND"));
    }

    @ExceptionHandler(ProductNameNotFoundException.class)
    public ResponseEntity<ErrorResponseMessage> productNameNotFoundException(ProductNameNotFoundException productNameNotFoundException) {
        return ResponseEntity.ok(new ErrorResponseMessage(productNameNotFoundException.getMessage(), "NOT_FOUND"));
    }

    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseEntity<ErrorResponseMessage> productsNotFoundException(ProductsNotFoundException productsNotFoundException) {
        return ResponseEntity.ok(new ErrorResponseMessage(productsNotFoundException.getMessage(), "NOT_FOUND"));
    }

    @ExceptionHandler(ProductsNotFoundByCategoryException.class)
    public ResponseEntity<ErrorResponseMessage> productsNotFoundByCategoryException(ProductsNotFoundByCategoryException productsNotFoundByCategoryException) {
        return ResponseEntity.ok(new ErrorResponseMessage(productsNotFoundByCategoryException.getMessage(), "NOT_FOUND"));
    }
}
