package com.augusto.soloveganbusiness.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augusto.soloveganbusiness.dto.StoreDto;
import com.augusto.soloveganbusiness.services.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/create/stores")
    public ResponseEntity<StoreDto> createStore(@Valid @RequestBody StoreDto storeDto) {
        StoreDto savedStoreDto = storeService.save(storeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStoreDto);
    }
}
