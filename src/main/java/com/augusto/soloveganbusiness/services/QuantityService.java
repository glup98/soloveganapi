package com.augusto.soloveganbusiness.services;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.QuantityDto;
import com.augusto.soloveganbusiness.exceptions.ResourceNotFoundException;
import com.augusto.soloveganbusiness.mappers.QuantityMapper;
import com.augusto.soloveganbusiness.models.NutritionalInformation;
import com.augusto.soloveganbusiness.models.Product;
import com.augusto.soloveganbusiness.models.Quantity;
import com.augusto.soloveganbusiness.repositories.NutritionalInformationRepository;
import com.augusto.soloveganbusiness.repositories.QuantityRepository;

@Service
public class QuantityService extends BaseService<QuantityDto, Quantity> {
    private final QuantityRepository quantityRepository;
    private final QuantityMapper quantityMapper;
    private final NutritionalInformationRepository nutritionalInformationRepository;

    public QuantityService(QuantityRepository quantityRepository, QuantityMapper quantityMapper,
            NutritionalInformationRepository nutritionalInformationRepository) {
        super(quantityRepository, quantityMapper);
        this.quantityRepository = quantityRepository;
        this.quantityMapper = quantityMapper;
        this.nutritionalInformationRepository = nutritionalInformationRepository;
    }

    public Quantity createQuantity(QuantityDto quantityDto, Product product,
            NutritionalInformation nutritionalInformation) {
        Quantity quantity = quantityMapper.toEntity(quantityDto);
        quantity.setProduct(product);
        quantity.setNutritionalInformation(nutritionalInformation);
        return quantityRepository.save(quantity);
    }

    public void addProductAndNutritionalInformationToQuantity(QuantityDto quantityDto, Long nutritionalInformationId,
            Product product) {
        Quantity quantity = quantityMapper.toEntity(quantityDto);
        NutritionalInformation nutritionalInformation = nutritionalInformationRepository
                .findById(nutritionalInformationId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        quantity.setNutritionalInformation(nutritionalInformation);
        quantity.setProduct(product);
        quantityRepository.save(quantity);
    }
}
