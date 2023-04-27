package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.NutritionalInformationDto;
import com.augusto.soloveganbusiness.models.NutritionalInformation;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NutritionalInformationMapper implements IMapper<NutritionalInformationDto, NutritionalInformation> {
    private final ModelMapper modelMapper;

    @Override
    public NutritionalInformation toEntity(NutritionalInformationDto nutritionalInformationDto) {
        NutritionalInformation nutritionalInformation = modelMapper.map(nutritionalInformationDto,
                NutritionalInformation.class);
        return nutritionalInformation;
    }

    @Override
    public NutritionalInformationDto toDto(NutritionalInformation nutritionalInformation) {
        NutritionalInformationDto nutritionalInformationDto = modelMapper.map(nutritionalInformation,
                NutritionalInformationDto.class);
        return nutritionalInformationDto;
    }
}
