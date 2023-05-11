package com.augusto.soloveganbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.ProductDto;
import com.augusto.soloveganbusiness.dto.ProductRequestDto;
import com.augusto.soloveganbusiness.exceptions.EntityAlreadyExistsException;
import com.augusto.soloveganbusiness.exceptions.ResourceNotFoundException;
import com.augusto.soloveganbusiness.mappers.ProductMapper;
import com.augusto.soloveganbusiness.models.Product;
import com.augusto.soloveganbusiness.models.Store;
import com.augusto.soloveganbusiness.models.User;
import com.augusto.soloveganbusiness.repositories.ProductRepository;
import com.augusto.soloveganbusiness.repositories.StoreRepository;
import com.augusto.soloveganbusiness.repositories.UserRepository;

@Service
public class ProductService extends BaseService<ProductDto, Product> {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ProductMapper productMapper;
    private final PriceService priceService;

    public ProductService(ProductRepository productRepository, UserRepository userRepository,
            StoreRepository storeRepository,
            ProductMapper productMapper,
            PriceService priceService) {
        super(productRepository, productMapper);
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
        this.storeRepository = storeRepository;
        this.priceService = priceService;
    }

    public ProductDto createProduct(ProductRequestDto productRequestDto, Long storeId) {
        ProductDto productDtoReceived = productRequestDto.getProductDto();
        if (productRepository.existsByName(productDtoReceived.getName())) {
            throw new EntityAlreadyExistsException("El producto ya existe");
        }
        Product product = productMapper.toEntity(productDtoReceived);
        productRepository.save(product);
        priceService.addProductAndStoreToPrice(productRequestDto.getPriceDto(), storeId, product);
        return productMapper.toDto(product);
    }

    public List<ProductDto> getProductsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        List<Product> products = productRepository.findByUsersContaining(user);
        return convertToDtoList(products);
    }

    public List<ProductDto> getProductsByStoreId(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + storeId));
        List<Product> products = productRepository.findByStoresContaining(store);
        return convertToDtoList(products);
    }
}
