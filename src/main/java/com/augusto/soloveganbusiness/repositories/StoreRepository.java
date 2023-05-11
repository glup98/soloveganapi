package com.augusto.soloveganbusiness.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.augusto.soloveganbusiness.models.Store;

@Repository
public interface StoreRepository extends BaseRepository<Store> {

    @Query("SELECT s FROM Store s WHERE s.user.id = :userId")
    Page<Store> findByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT s FROM Store s WHERE s.user.id = :userId")
    List<Store> findByUserIdEntity(@Param("userId") Long userId);

    boolean existsByName(String name);
}
