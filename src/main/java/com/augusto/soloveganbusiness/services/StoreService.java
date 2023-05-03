package com.augusto.soloveganbusiness.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.StoreDto;
import com.augusto.soloveganbusiness.exceptions.EntityAlreadyExistsException;
import com.augusto.soloveganbusiness.exceptions.ResourceConflictException;
import com.augusto.soloveganbusiness.exceptions.ResourceNotFoundException;
import com.augusto.soloveganbusiness.mappers.StoreMapper;
import com.augusto.soloveganbusiness.models.Store;
import com.augusto.soloveganbusiness.models.User;
import com.augusto.soloveganbusiness.repositories.StoreRepository;
import com.augusto.soloveganbusiness.repositories.UserRepository;

@Service
public class StoreService extends BaseService<StoreDto, Store> {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    private final UserRepository userRepository;

    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper, UserRepository userRepository) {
        super(storeRepository, storeMapper);
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public StoreDto createStore(StoreDto storeDto, Long userId) {
        if (storeRepository.existsByName(storeDto.getName())) {
            throw new EntityAlreadyExistsException("La tienda ya esta registrada");
        }
        Store store = storeMapper.toEntity(storeDto);
        store = setUserToStore(store, userId);
        Store savedStore = storeRepository.save(store);
        return storeMapper.toDto(savedStore);
    }

    public StoreDto updateStore(Long storeId, StoreDto storeDto) {
        String newName = storeDto.getName();
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found for this id :: " + storeId));
        if (!store.getName().equals(newName)) { // El nombre de la tienda ha cambiado
            validateStoreName(newName); // Validar que no exista otra tienda con el mismo nombre
            store.setName(newName); // Actualizar el nombre de la tienda
        }
        // Actualizar otros campos necesarios de la tienda
        storeRepository.save(store);
        return storeMapper.toDto(store);
    }

    public Store setUserToStore(Store store, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        store.setUser(user);
        return store;
    }

    public Page<Store> findAllByUser(Long userId, Pageable pageable) {
        Page<Store> storesByUser = storeRepository.findByUserId(userId, pageable);
        System.out.println(storesByUser);
        return storesByUser;
    }

    public List<StoreDto> findAllByUserEntity(Long userId) {
        List<Store> storesByUser = storeRepository.findByUserIdEntity(userId);
        List<StoreDto> storeDtos = convertToDtoList(storesByUser);
        System.out.println(storesByUser);
        return storeDtos;
    }

    public void validateStoreName(String name) {
        if (storeRepository.existsByName(name)) {
            throw new ResourceConflictException("Ya existe una tienda con el nombre " + name);
        }
    }

}
