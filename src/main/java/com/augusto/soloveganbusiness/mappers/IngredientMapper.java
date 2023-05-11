package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.IngredientDto;
import com.augusto.soloveganbusiness.models.Ingredient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IngredientMapper implements IMapper<IngredientDto, Ingredient> {

    private final ModelMapper modelMapper;

    @Override
    public Ingredient toEntity(IngredientDto ingredientDto) {
        Ingredient ingredient = modelMapper.map(ingredientDto, Ingredient.class);
        return ingredient;
    }

    @Override
    public IngredientDto toDto(Ingredient ingredient) {
        IngredientDto ingredientDto = modelMapper.map(ingredient, IngredientDto.class);
        return ingredientDto;
    }

}
