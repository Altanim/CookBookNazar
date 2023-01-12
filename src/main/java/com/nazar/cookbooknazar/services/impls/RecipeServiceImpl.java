package com.nazar.cookbooknazar.services.impls;

import com.nazar.cookbooknazar.models.Recipe;
import com.nazar.cookbooknazar.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    public Map<Long,Recipe> RECIPES_MAP = new HashMap<>();
    private long idGenerator = 1;
    @Override
    public Recipe addRecipe(Recipe recipe){
        RECIPES_MAP.put(idGenerator++, recipe);
        return recipe;
    }
    @Override
    public Optional<Recipe> getRecipe(Long id) {
        return Optional.ofNullable(RECIPES_MAP.get(id));
    }
    @Override
    public Optional<Recipe> edit(long id, Recipe recipe){
        return Optional.ofNullable(RECIPES_MAP.replace(id,recipe));
    }
    @Override
    public Optional<Recipe> delete(long id){
        return Optional.ofNullable(RECIPES_MAP.remove(id));
    }
    @Override
    public Map<Long,Recipe> getAll(){
        return new HashMap<>(RECIPES_MAP);
    }
    }

