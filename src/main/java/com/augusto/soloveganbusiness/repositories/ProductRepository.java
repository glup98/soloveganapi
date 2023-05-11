package com.augusto.soloveganbusiness.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.augusto.soloveganbusiness.models.Product;
import com.augusto.soloveganbusiness.models.Store;
import com.augusto.soloveganbusiness.models.User;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
    List<Product> findByUsersContaining(User user);

    List<Product> findByStoresContaining(Store store);

    boolean existsByName(String name);

}
