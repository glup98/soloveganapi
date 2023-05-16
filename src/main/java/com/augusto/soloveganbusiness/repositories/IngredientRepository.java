package com.augusto.soloveganbusiness.repositories;

import java.util.Optional;

import com.augusto.soloveganbusiness.models.Ingredient;

public interface IngredientRepository extends BaseRepository<Ingredient> {

    Optional<Ingredient> findByIngredientName(String ingredientName);

}
