package com.augusto.soloveganbusiness.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.NutritionalInformationDto;
import com.augusto.soloveganbusiness.dto.NutritionalInformationRequestDto;
import com.augusto.soloveganbusiness.dto.ProductDto;
import com.augusto.soloveganbusiness.dto.ProductRequestDto;
import com.augusto.soloveganbusiness.dto.QuantityDto;
import com.augusto.soloveganbusiness.exceptions.EntityAlreadyExistsException;
import com.augusto.soloveganbusiness.exceptions.ResourceConflictException;
import com.augusto.soloveganbusiness.exceptions.ResourceNotFoundException;
import com.augusto.soloveganbusiness.mappers.ProductMapper;
import com.augusto.soloveganbusiness.models.NutritionalInformation;
import com.augusto.soloveganbusiness.models.Product;
import com.augusto.soloveganbusiness.models.Quantity;
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
    private final IngredientService ingredientService;
    private final QuantityService quantityService;
    private final NutritionalInformationService nutritionalInformationService;

    public ProductService(ProductRepository productRepository, UserRepository userRepository,
            StoreRepository storeRepository,
            ProductMapper productMapper,
            PriceService priceService,
            IngredientService ingredientService,
            QuantityService quantityService,
            NutritionalInformationService nutritionalInformationService) {
        super(productRepository, productMapper);
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
        this.storeRepository = storeRepository;
        this.priceService = priceService;
        this.ingredientService = ingredientService;
        this.quantityService = quantityService;
        this.nutritionalInformationService = nutritionalInformationService;
    }

    public ProductDto createProduct(ProductRequestDto productRequestDto, Long storeId) {
        ProductDto productDtoReceived = productRequestDto.getProductDto();
        if (productRepository.existsByName(productDtoReceived.getName())) {
            throw new EntityAlreadyExistsException("El producto ya existe");
        }
        Product product = productMapper.toEntity(productDtoReceived);
        productRepository.save(product);

        List<NutritionalInformationRequestDto> nutritionalInformationRequestDtos = productRequestDto
                .getNutritionalInformations();

        for (NutritionalInformationRequestDto nutritionalInformationRequestDto : nutritionalInformationRequestDtos) {
            NutritionalInformationDto nutritionalInformationDto = new NutritionalInformationDto();
            nutritionalInformationDto.setDescription(nutritionalInformationRequestDto.getDescription());

            NutritionalInformation nutritionalInformation = nutritionalInformationService
                    .createOrGetNutritionalInformation(nutritionalInformationDto);

            for (QuantityDto quantityDto : nutritionalInformationRequestDto.getQuantities()) {
                Quantity quantity = quantityService.createQuantity(quantityDto, product, nutritionalInformation);
                nutritionalInformation.getQuantities().add(quantity);
            }
            getNutritionalInformationForProduct(product).add(nutritionalInformation);
        }

        priceService.addProductAndStoreToPrice(productRequestDto.getPriceDto(), storeId, product);
        // Crear los ingredientes y vincularlos con el producto
        ingredientService.createIngredients(productRequestDto.getIngredients(), product);

        return productMapper.toDto(product);
    }

    public List<NutritionalInformation> getNutritionalInformationForProduct(Product product) {
        List<NutritionalInformation> nutritionalInformationList = new ArrayList<>();
        for (Quantity quantity : product.getQuantities()) {
            nutritionalInformationList.add(quantity.getNutritionalInformation());
        }
        return nutritionalInformationList;
    }

    public ProductDto updateProduct(Long productId, ProductRequestDto productRequestDto, Long storeId) {
        String newName = productRequestDto.getProductDto().getName();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found for this id :: " + productId));
        if (!product.getName().equals(newName)) { // El nombre del producto ha cambiado
            validateProductName(newName); // Validar que no exista otro producto con el mismo nombre
            // Actualizar el producto
            product.setName(newName);
            product.setDescription(productRequestDto.getProductDto().getDescription());
            product.setPortionValue(productRequestDto.getProductDto().getPortionValue());
        }
        // Actualizar otros campos necesarios de la tienda
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

    public void validateProductName(String name) {
        if (productRepository.existsByName(name)) {
            throw new ResourceConflictException("Ya existe un producto con el nombre " + name);
        }
    }
}
