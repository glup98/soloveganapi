package com.augusto.soloveganbusiness.repositories;

import java.util.List;

import com.augusto.soloveganbusiness.models.Price;

public interface PriceRepository extends BaseRepository<Price> {

    List<Price> findPricesByStoreId(Long storeId);

}
