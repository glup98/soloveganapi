package com.augusto.soloveganbusiness.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.augusto.soloveganbusiness.models.NutritionalInformation;

@Repository
public interface NutritionalInformationRepository extends BaseRepository<NutritionalInformation> {
    Optional<NutritionalInformation> findByDescription(String description);
}
