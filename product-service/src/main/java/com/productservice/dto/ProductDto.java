package com.productservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotBlank(message = "product name should not be a blank")
    @Size(min = 5, max = 150)
    private String productName;
    @NotBlank(message = "description should not be a blank")
    @Size(min = 5, max = 350)
    private String description;
    @NotBlank(message = "category should not be a blank")
    @Size(min = 5, max = 50)
    private String category;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
    private Double price;
    @NotNull(message = "stock is required")
    @Min(5)
    @Max(100)
    private Long stock;
}
