package com.nazar.cookbooknazar.services;

import com.nazar.cookbooknazar.models.Recipe;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);
    Recipe getRecipe(Long recipeId);


}