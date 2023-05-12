package com.augusto.soloveganbusiness.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.augusto.soloveganbusiness.dto.ProductDto;
import com.augusto.soloveganbusiness.dto.ProductRequestDto;
import com.augusto.soloveganbusiness.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/{storeId}/products")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto,
            @PathVariable Long storeId) {
        ProductDto createdProductDto = productService.createProduct(productRequestDto, storeId);
        ProductDto productReceived = productRequestDto.getProductDto();
        if (productReceived.getImgUrl() != null && !productReceived.getImgUrl().isEmpty()) {
            String imageUrl = productReceived.getImgUrl();
            String imgRoute = "/img/" + createdProductDto.getId();
            File directory = new File("src/main/resources/static" + imgRoute);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            try {
                URL url = new URL(imageUrl);
                InputStream inputStream = url.openStream();
                byte[] bytes = inputStream.readAllBytes();
                BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(directory.getAbsolutePath() + "/" + createdProductDto.getId() + ".jpg")));
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
                System.out.println("se subio el archivo");
                createdProductDto.setImgRoute(imgRoute + "/" + createdProductDto.getId() + ".jpg");
                productService.save(createdProductDto);
            } catch (IOException e) {
                System.out.println("error" + e);
                e.printStackTrace();
            }
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
                .buildAndExpand(createdProductDto.getId()).toUri();
        return ResponseEntity.created(location).body(createdProductDto);
    }

    @PutMapping("/products/{productId}/{storeId}")
    public ResponseEntity<ProductDto> updateProducts(@PathVariable(value = "productId") Long productId,
            @RequestBody ProductRequestDto productRequestDto,
            @PathVariable Long storeId) {
        ProductDto updatedProductDto = productService.updateProduct(productId, productRequestDto, storeId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(updatedProductDto);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto productDto = productService.findById(productId);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> productPage = productService.findAll(pageable);
        return ResponseEntity.ok().body(productPage);
    }

    @GetMapping("/products/user/{userId}")
    public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable Long userId) {
        List<ProductDto> productDtos = productService.getProductsByUserId(userId);
        return ResponseEntity.ok().body(productDtos);
    }

}
