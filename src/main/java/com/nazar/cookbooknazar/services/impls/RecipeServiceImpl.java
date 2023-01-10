package com.nazar.cookbooknazar.services.impls;

import com.nazar.cookbooknazar.models.Recipe;
import com.nazar.cookbooknazar.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Long recipeId = 0L;
    public Map<Long,Recipe> RECIPES_MAP = new HashMap<>();
    @Override
    public Recipe addRecipe(Recipe recipe) {
        RECIPES_MAP.put(recipeId,recipe);
        recipeId++;
        return recipe;
    }

    @Override
    public Recipe getRecipe(Long recipeId) {
        return RECIPES_MAP.get(recipeId);
    }
    }

