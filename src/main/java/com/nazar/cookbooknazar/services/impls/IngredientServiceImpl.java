package com.nazar.cookbooknazar.services.impls;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    public Map<Long, Ingredient> INGREDIENTS_MAP = new HashMap<>();
    private long idGenerator = 1;

    @Override
    public Ingredient addIngredient(Ingredient ingredient){
        INGREDIENTS_MAP.put(idGenerator++, ingredient);
        return ingredient;
    }
    @Override
    public Optional<Ingredient> getIngredient(Long id) {
        return Optional.ofNullable(INGREDIENTS_MAP.get(id));
    }
    @Override
    public Optional<Ingredient> edit(long id, Ingredient ingredient){
        return Optional.ofNullable(INGREDIENTS_MAP.replace(id,ingredient));
    }
    @Override
    public Optional<Ingredient> delete(long id){
        return Optional.ofNullable(INGREDIENTS_MAP.remove(id));
    }
    @Override
    public Map<Long,Ingredient> getAll(){
        return new HashMap<>(INGREDIENTS_MAP);
    }
}
