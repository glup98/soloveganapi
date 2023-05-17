package com.augusto.soloveganbusiness.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.NutritionalInformationDto;
import com.augusto.soloveganbusiness.mappers.NutritionalInformationMapper;
import com.augusto.soloveganbusiness.models.NutritionalInformation;
import com.augusto.soloveganbusiness.repositories.NutritionalInformationRepository;

@Service
public class NutritionalInformationService extends BaseService<NutritionalInformationDto, NutritionalInformation> {

    private final NutritionalInformationRepository nutritionalInformationRepository;
    private final NutritionalInformationMapper nutritionalInformationMapper;

    public NutritionalInformationService(NutritionalInformationRepository nutritionalInformationRepository,
            NutritionalInformationMapper nutritionalInformationMapper) {
        super(nutritionalInformationRepository, nutritionalInformationMapper);
        this.nutritionalInformationRepository = nutritionalInformationRepository;
        this.nutritionalInformationMapper = nutritionalInformationMapper;
    }

    public NutritionalInformation createOrGetNutritionalInformation(
            NutritionalInformationDto nutritionalInformationDto) {
        Optional<NutritionalInformation> existingNutritionalInformation = nutritionalInformationRepository
                .findByDescription(nutritionalInformationDto.getDescription());
        if (existingNutritionalInformation.isPresent()) {
            return existingNutritionalInformation.get();
        } else {
            NutritionalInformation nutritionalInformation = new NutritionalInformation();
            nutritionalInformation.setDescription(nutritionalInformationDto.getDescription());
            return nutritionalInformationRepository.save(nutritionalInformation);
        }
    }
}
