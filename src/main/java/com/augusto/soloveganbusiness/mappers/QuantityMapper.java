package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.QuantityDto;
import com.augusto.soloveganbusiness.models.Quantity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuantityMapper implements IMapper<QuantityDto, Quantity> {
    private final ModelMapper modelMapper;

    @Override
    public Quantity toEntity(QuantityDto quantityDto) {
        Quantity quantity = modelMapper.map(quantityDto, Quantity.class);
        return quantity;
    }

    @Override
    public QuantityDto toDto(Quantity quantity) {
        QuantityDto quantityDto = modelMapper.map(quantity, QuantityDto.class);
        return quantityDto;
    }
}
