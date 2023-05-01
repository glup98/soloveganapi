package com.augusto.soloveganbusiness.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.augusto.soloveganbusiness.dto.ProductDto;
import com.augusto.soloveganbusiness.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProductDto = productService.createProduct(productDto);
        Long productId = createdProductDto.getId();
        if (!productDto.getImgUrl().isEmpty()) {
            String imageUrl = productDto.getImgUrl();
            String imgRoute = "/img/" + productId;
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
                                new File(directory.getAbsolutePath() + "/" + productId + ".jpg")));
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
                System.out.println("se subio el archivo");
                createdProductDto.setImgRoute(imgRoute + "/" + productId + ".jpg");
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

    @GetMapping("/products/{userId}")
    public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable Long userId) {
        List<ProductDto> productDtos = productService.getProductsByUserId(userId);
        return ResponseEntity.ok().body(productDtos);
    }

}
