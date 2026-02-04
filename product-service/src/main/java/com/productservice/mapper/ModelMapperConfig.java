package com.productservice.mapper;

import com.productservice.dto.ProductDto;
import com.productservice.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(ProductDto.class, Product.class)
                .addMappings(mapper -> mapper.skip(Product::setId));
        return modelMapper;
    }
}
