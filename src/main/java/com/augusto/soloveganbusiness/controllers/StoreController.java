package com.augusto.soloveganbusiness.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/create/{userId}/stores")
    public ResponseEntity<StoreDto> createStore(@Valid @RequestBody StoreDto storeDto,
            @PathVariable(value = "userId") Long userId) {
        StoreDto savedStoreDto = storeService.createStore(storeDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStoreDto);
    }

    @GetMapping("/stores/{storeId}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long storeId) {
        StoreDto storeDto = storeService.findById(storeId);
        return ResponseEntity.ok(storeDto);
    }

    @PutMapping("/stores/{storeId}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable(value = "storeId") Long storeId,
            @RequestBody StoreDto storeDto) {
        StoreDto updatedStoreDto = storeService.updateStore(storeId, storeDto);
        return ResponseEntity.ok(updatedStoreDto);
    }

    @GetMapping("/{userId}/stores")
    public ResponseEntity<List<StoreDto>> getAllStoresByUser(@PathVariable(value = "userId") Long userId) {
        List<StoreDto> storesDto = storeService.findAllByUser(userId);
        if (storesDto.isEmpty()) {
            // Devuelve un código 404 si no se encuentra ninguna tienda para
            // el usuario
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(storesDto);
        }
    }

    @GetMapping("/stores")
    public ResponseEntity<List<StoreDto>> getAllStores() {
        List<StoreDto> storesDto = storeService.findAll();
        return ResponseEntity.ok(storesDto);
    }
}
