package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.PriceDto;
import com.augusto.soloveganbusiness.models.Price;
import com.augusto.soloveganbusiness.models.Price;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PriceMapper implements IMapper<PriceDto, Price> {

    private final ModelMapper modelMapper;

    @Override
    public Price toEntity(PriceDto priceDto) {
        Price price = modelMapper.map(priceDto, Price.class);
        return price;
    }

    @Override
    public PriceDto toDto(Price price) {
        PriceDto priceDto = modelMapper.map(price, PriceDto.class);
        return priceDto;
    }
}
