package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.ProductDto;
import com.augusto.soloveganbusiness.models.Product;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper implements IMapper<ProductDto, Product> {

    private final ModelMapper modelMapper;

    @Override
    public Product toEntity(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        return product;
    }

    @Override
    public ProductDto toDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

}
