package com.augusto.soloveganbusiness.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.StoreDto;
import com.augusto.soloveganbusiness.exceptions.EntityAlreadyExistsException;
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

    public Store setUserToStore(Store store, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        store.setUser(user);
        return store;
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
}
