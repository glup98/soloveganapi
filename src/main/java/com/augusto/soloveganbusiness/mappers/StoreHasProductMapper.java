package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.StoreHasProductDto;
import com.augusto.soloveganbusiness.models.StoreHasProduct;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StoreHasProductMapper implements IMapper<StoreHasProductDto, StoreHasProduct> {

    private final ModelMapper modelMapper;

    @Override
    public StoreHasProduct toEntity(StoreHasProductDto storeHasProductDto) {
        StoreHasProduct storeHasProduct = modelMapper.map(storeHasProductDto, StoreHasProduct.class);
        return storeHasProduct;
    }

    @Override
    public StoreHasProductDto toDto(StoreHasProduct storeHasProduct) {
        StoreHasProductDto storeHasProductDto = modelMapper.map(storeHasProduct, StoreHasProductDto.class);
        return storeHasProductDto;
    }
}
