package com.augusto.soloveganbusiness.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.augusto.soloveganbusiness.dto.PriceDto;
import com.augusto.soloveganbusiness.dto.ProductRequestDto;
import com.augusto.soloveganbusiness.services.PriceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class PriceController {
    private final PriceService priceService;

    @PostMapping("create/prices")
    public ResponseEntity<PriceDto> createPrice(@Valid @RequestBody ProductRequestDto productRequestDto) {
        PriceDto createdPriceDto = priceService.save(productRequestDto.getPriceDto());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
                .buildAndExpand(createdPriceDto.getId()).toUri();
        return ResponseEntity.created(location).body(createdPriceDto);
    }
}
