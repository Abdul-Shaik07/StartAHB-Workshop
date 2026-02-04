package com.productservice.service;
import com.productservice.dto.ProductDto;
import com.productservice.exception.ProductIdNotFoundException;
import com.productservice.exception.ProductNameNotFoundException;
import com.productservice.exception.ProductsNotFoundByCategoryException;
import com.productservice.exception.ProductsNotFoundException;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public ProductResponse registerProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductIdNotFoundException("Id is not found to update"));
        modelMapper.map(productDto, product);
        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) {
            throw new ProductsNotFoundException("products not found");
        }
        return products.stream()
                .map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public String deleteProductById(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product deleted successfully";
        } else {
            throw new ProductIdNotFoundException("Product Id doesn't exist to delete");
        }
    }

    @Override
    public ProductResponse findProductById(Long id) {
        Product returningProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductIdNotFoundException("Product Id doesn't exist to get the product"));
        return mapToResponse(returningProduct);
    }

    @Override
    public List<ProductResponse> findProductByName(String productName) {
        List<Product> byProductName = productRepository.findByProductName(productName);
        if(byProductName.isEmpty()) {
            throw new ProductNameNotFoundException("Product name is not found");
        }
        return  byProductName.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findProductByCategory(String category) {
        List<Product> productByCategory = productRepository.findProductByCategory(category);
        if(productByCategory.isEmpty()) {
            throw new ProductsNotFoundByCategoryException("products not found by category called: " +category);
        }
        return productByCategory.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> findAllProductsByPagination(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> allProducts = productRepository.findAll(pageRequest);
        if(allProducts.isEmpty()) {
            throw new ProductsNotFoundByCategoryException("products not found");
        }
        return allProducts.map(this::mapToResponse);
    }


    private ProductResponse mapToResponse(Product product) {
        return modelMapper.map(product, ProductResponse.class);
    }
}
