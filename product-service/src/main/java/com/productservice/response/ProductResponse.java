package com.productservice.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private String description;
    private String category;
    private Double price;
    private Long stock;
}
