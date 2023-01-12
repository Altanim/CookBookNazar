package com.nazar.cookbooknazar.services;

import com.nazar.cookbooknazar.models.Recipe;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public interface RecipeService {
    Recipe addRecipe(Recipe recipe);
    Optional<Recipe> getRecipe(Long id);
    Optional<Recipe> edit(long id, Recipe recipe);

    Optional<Recipe> delete(long id);

    Map<Long,Recipe> getAll();
}