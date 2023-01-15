package com.nazar.cookbooknazar.services;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.models.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ValidateService {
    public boolean isNotValid(Recipe recipe){
        return StringUtils.isBlank(recipe.getRecipeName()) &&
                !CollectionUtils.isEmpty(recipe.getIngredients()) &&
                !CollectionUtils.isEmpty(recipe.getSteps()) &&
                recipe.getCookingTimeMinutes() > 0;
    }
    public boolean isNotValid(Ingredient ingredient){
        return StringUtils.isBlank(ingredient.getIngName()) &&
                StringUtils.isBlank(ingredient.getMeasureUnit()) &&
                ingredient.getWeight() > 0;
    }
    }


