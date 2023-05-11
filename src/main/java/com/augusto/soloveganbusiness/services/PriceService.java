package com.augusto.soloveganbusiness.services;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.PriceDto;
import com.augusto.soloveganbusiness.exceptions.ResourceNotFoundException;
import com.augusto.soloveganbusiness.mappers.PriceMapper;
import com.augusto.soloveganbusiness.models.Price;
import com.augusto.soloveganbusiness.models.Product;
import com.augusto.soloveganbusiness.models.Store;
import com.augusto.soloveganbusiness.repositories.PriceRepository;
import com.augusto.soloveganbusiness.repositories.StoreRepository;

@Service
public class PriceService extends BaseService<PriceDto, Price> {

    private final PriceRepository priceRepository;
    private final StoreRepository storeRepository;
    private final PriceMapper priceMapper;

    public PriceService(PriceRepository priceRepository, PriceMapper priceMapper, StoreRepository storeRepository) {
        super(priceRepository, priceMapper);
        this.priceRepository = priceRepository;
        this.storeRepository = storeRepository;
        this.priceMapper = priceMapper;
    }

    public void addProductAndStoreToPrice(PriceDto priceDto, Long storeId, Product product) {
        Price price = priceMapper.toEntity(priceDto);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        price.setStore(store);
        price.setProduct(product);
        priceRepository.save(price);
    }
}
