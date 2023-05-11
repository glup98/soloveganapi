package com.augusto.soloveganbusiness.controllers;

import java.net.URI;
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

import com.augusto.soloveganbusiness.dto.StoreDto;
import com.augusto.soloveganbusiness.models.Store;
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
        StoreDto createdStoreDto = storeService.createStore(storeDto, userId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
                .buildAndExpand(createdStoreDto.getId()).toUri();
        return ResponseEntity.created(location).body(createdStoreDto);
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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(updatedStoreDto);
    }

    @GetMapping("/stores")
    public ResponseEntity<Page<StoreDto>> getAllStores(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StoreDto> storesPage = storeService.findAll(pageable);
        System.out.println(storesPage);
        return ResponseEntity.ok().body(storesPage);
    }

    @GetMapping("/{userId}/stores")
    public ResponseEntity<Page<Store>> getAllStoresByUser(
            @PathVariable(value = "userId") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> storesPage = storeService.findAllByUser(userId, pageable);
        return ResponseEntity.ok().body(storesPage);
    }

    @GetMapping("/{userId}/entitystores")
    public ResponseEntity<List<StoreDto>> getAllStoresByUser(
            @PathVariable(value = "userId") Long userId) {
        List<StoreDto> storesPage = storeService.findAllByUserEntity(userId);
        return ResponseEntity.ok().body(storesPage);
    }
}
