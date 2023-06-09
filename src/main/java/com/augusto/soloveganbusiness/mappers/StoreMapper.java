package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.StoreDto;
import com.augusto.soloveganbusiness.models.Store;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StoreMapper implements IMapper<StoreDto, Store> {

    private final ModelMapper modelMapper;

    @Override
    public Store toEntity(StoreDto storeDto) {
        Store store = modelMapper.map(storeDto, Store.class);
        return store;
    }

    @Override
    public StoreDto toDto(Store store) {
        StoreDto storeDto = modelMapper.map(store, StoreDto.class);
        return storeDto;
    }
}
