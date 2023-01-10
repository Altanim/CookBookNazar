package com.nazar.cookbooknazar.services.impls;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.services.IngredientService;

import java.util.HashMap;
import java.util.Map;

public class IngredientServiceImpl implements IngredientService {
    private static Long ingId = 0L;
    public Map<Long, Ingredient> INGREDIENTS_MAP = new HashMap<>();

    @Override
    public Ingredient getIngredient(Long ingId) {
        return INGREDIENTS_MAP.get(ingId);
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient){
        INGREDIENTS_MAP.put(ingId, ingredient);
            return ingredient;
        }
}
