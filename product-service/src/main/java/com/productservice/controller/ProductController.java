package com.productservice.controller;
import com.productservice.dto.ProductDto;
import com.productservice.response.ProductResponse;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/registerProduct")
    public ResponseEntity<ProductResponse> registerProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.registerProduct(productDto));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto, id));
    }

    @GetMapping("/findAllProducts")
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/findProductById/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    @GetMapping("/findByProductName")
    public ResponseEntity<List<ProductResponse>> findByProductName(@RequestParam("productName") String productName) {
        return ResponseEntity.ok(productService.findProductByName(productName));
    }

    @GetMapping("/findProductByCategory")
    public ResponseEntity<List<ProductResponse>> findProductByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(productService.findProductByCategory(category));
    }

    @GetMapping("/findAllProductsByPagination")
    public ResponseEntity<Page<ProductResponse>> findAllProductsByPagination(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "5") int size,
                                                                             @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(productService.findAllProductsByPagination(page, size, sortBy));
    }

}
