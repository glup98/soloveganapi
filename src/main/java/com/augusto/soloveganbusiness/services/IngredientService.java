package com.augusto.soloveganbusiness.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.IngredientDto;
import com.augusto.soloveganbusiness.mappers.IngredientMapper;
import com.augusto.soloveganbusiness.models.Ingredient;
import com.augusto.soloveganbusiness.models.Product;
import com.augusto.soloveganbusiness.repositories.IngredientRepository;

@Service
public class IngredientService extends BaseService<IngredientDto, Ingredient> {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        super(ingredientRepository, ingredientMapper);
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    public void createIngredients(String ingredientsString, Product product) {
        // Dividir el string por comas
        String[] ingredientNames = ingredientsString.split(",");

        // Para cada nombre de ingrediente
        for (String ingredientName : ingredientNames) {
            // Limpiar los espacios en blanco al principio y al final
            ingredientName = ingredientName.trim();
            // Comprobar si el ingrediente ya existe y si no, crearlo
            checkAndCreateIngredient(ingredientName, product);
        }
    }

    private void checkAndCreateIngredient(String ingredientName, Product product) {
        // Comprobar si el ingrediente ya existe
        Optional<Ingredient> existingIngredient = ingredientRepository.findByIngredientName(ingredientName);

        // Si existe, añadir el producto a la lista de productos del ingrediente
        if (existingIngredient.isPresent()) {
            existingIngredient.get().getProducts().add(product);
            ingredientRepository.save(existingIngredient.get());
        } else {
            // Si no existe, crearlo y añadir el producto a la lista de productos del
            // ingrediente
            createIngredient(ingredientName, product);
        }
    }

    private void createIngredient(String ingredientName, Product product) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientName);
        ingredient.getProducts().add(product);
        ingredientRepository.save(ingredient);
    }
}
