package com.productservice.service;

import com.productservice.dto.ProductDto;
import com.productservice.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IProductService {

    ProductResponse registerProduct(ProductDto productDto);
    ProductResponse updateProduct(ProductDto productDto, Long id);
    List<ProductResponse> findAllProducts();
    String deleteProductById(Long id);
    ProductResponse findProductById(Long id);
    List<ProductResponse> findProductByName(String productName);
    List<ProductResponse> findProductByCategory(String category);
    Page<ProductResponse> findAllProductsByPagination(int page, int size, String sortBy);
}
