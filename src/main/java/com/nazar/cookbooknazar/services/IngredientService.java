package com.nazar.cookbooknazar.services;

import com.nazar.cookbooknazar.models.Ingredient;

public interface IngredientService {
    Ingredient getIngredient(Long ingId);
    Ingredient addIngredient(Ingredient ingredient);
}
