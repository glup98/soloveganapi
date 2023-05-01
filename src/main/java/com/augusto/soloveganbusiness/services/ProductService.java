package com.augusto.soloveganbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.ProductDto;
import com.augusto.soloveganbusiness.exceptions.ResourceNotFoundException;
import com.augusto.soloveganbusiness.mappers.ProductMapper;
import com.augusto.soloveganbusiness.models.Product;
import com.augusto.soloveganbusiness.models.User;
import com.augusto.soloveganbusiness.repositories.ProductRepository;
import com.augusto.soloveganbusiness.repositories.UserRepository;

@Service
public class ProductService extends BaseService<ProductDto, Product> {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, UserRepository userRepository,
            ProductMapper productMapper) {
        super(productRepository, productMapper);
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public List<ProductDto> getProductsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        List<Product> products = productRepository.findByUsersContaining(user);
        return convertToDtoList(products);
    }

}
