package com.productservice.controller;
import com.productservice.dto.ProductDto;
import com.productservice.response.ProductResponse;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/registerProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> registerProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.registerProduct(productDto));
    }

    @PutMapping("/admin/updateProduct/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto, id));
    }

    @GetMapping("/user/findAllProducts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/user/findProductById/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @DeleteMapping("/admin/deleteProductById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    @GetMapping("/user/findByProductName")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ProductResponse>> findByProductName(@RequestParam("productName") String productName) {
        return ResponseEntity.ok(productService.findProductByName(productName));
    }

    @GetMapping("/user/findProductByCategory")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ProductResponse>> findProductByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(productService.findProductByCategory(category));
    }

    @GetMapping("/user/findAllProductsByPagination")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<ProductResponse>> findAllProductsByPagination(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "5") int size,
                                                                             @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(productService.findAllProductsByPagination(page, size, sortBy));
    }

}
