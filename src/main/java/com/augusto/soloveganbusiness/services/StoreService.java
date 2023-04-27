package com.augusto.soloveganbusiness.services;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.StoreDto;
import com.augusto.soloveganbusiness.mappers.StoreMapper;
import com.augusto.soloveganbusiness.models.Store;
import com.augusto.soloveganbusiness.repositories.StoreRepository;

@Service
public class StoreService extends BaseService<StoreDto, Store> {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        super(storeRepository, storeMapper);
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }
}
