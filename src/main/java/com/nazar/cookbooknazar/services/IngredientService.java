package com.nazar.cookbooknazar.services;

import com.nazar.cookbooknazar.models.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
@Service
public interface IngredientService {
    Optional<Ingredient> getIngredient(Long ingId);
    Ingredient addIngredient(Ingredient ingredient);

    Optional<Ingredient> edit(long id, Ingredient ingredient);

    Optional<Ingredient> delete(long id);

    Map<Long,Ingredient> getAll();
}
