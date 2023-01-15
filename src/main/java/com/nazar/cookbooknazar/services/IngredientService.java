package com.nazar.cookbooknazar.services;

import com.nazar.cookbooknazar.models.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientService {
    private final Map<Long, Ingredient> INGREDIENTS_MAP = new HashMap<>();
    private long idGenerator = 1L;
    public Ingredient addIngredient(Ingredient ingredient){
        INGREDIENTS_MAP.put(idGenerator++, ingredient);
        return ingredient;
    }
    public Optional<Ingredient> getIngredient(long id){
        return Optional.ofNullable(INGREDIENTS_MAP.get(id));
    }

    public Optional<Ingredient> update(long id, Ingredient ingredient) {
        return Optional.ofNullable(INGREDIENTS_MAP.replace(id,ingredient));
    }

    public Optional<Ingredient> delete(long id) {
        return Optional.ofNullable(INGREDIENTS_MAP.remove(id));
    }

    public Map<Long,Ingredient> getAll() {
        return new HashMap<>(INGREDIENTS_MAP);
    }
}
