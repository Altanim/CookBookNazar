package com.nazar.cookbooknazar.services;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.models.Recipe;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {
    private final Map<Long, Recipe> RECIPES_MAP = new HashMap<>();
    private long idGenerator = 1L;
    public Recipe addRecipe(Recipe recipe){
        RECIPES_MAP.put(idGenerator++, recipe);
        return recipe;
    }
    public Optional<Recipe> getRecipe(long id){
        return Optional.ofNullable(RECIPES_MAP.get(id));
    }

    public Optional<Recipe> update(long id, Recipe recipe) {
        return Optional.ofNullable(RECIPES_MAP.replace(id,recipe));
    }

    public Optional<Recipe> delete(long id) {
        return Optional.ofNullable(RECIPES_MAP.remove(id));
    }

    public Map<Long,Recipe> getAll() {
        return new HashMap<>(RECIPES_MAP);
    }
}
