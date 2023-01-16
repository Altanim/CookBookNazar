package com.nazar.cookbooknazar.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazar.cookbooknazar.models.Ingredient;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientFilesService ingredientFilesService;
    private Map<Long, Ingredient> INGREDIENTS_MAP = new HashMap<>();
    private long id = 1L;

    public IngredientService(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }

    @Nullable
    public Ingredient addIngredient(Ingredient ingredient) {
        if (!INGREDIENTS_MAP.containsKey(id)) {
            INGREDIENTS_MAP.put(id++, ingredient);
            saveToFile();
            return ingredient;
        }
        id++;
        addIngredient(ingredient);
        return ingredient;
    }

    @Nullable
    public Optional<Ingredient> getIngredient(long id) {
        return Optional.ofNullable(INGREDIENTS_MAP.get(id));
    }

    @Nullable
    public Optional<Ingredient> update(long id, Ingredient ingredient) {
        return Optional.ofNullable(INGREDIENTS_MAP.replace(id, ingredient));
    }
    @Nullable
    public Optional<Ingredient> delete(long id) {
        return Optional.ofNullable(INGREDIENTS_MAP.remove(id));
    }
    @Nullable
    public Map<Long, Ingredient> getAll() {
        return new HashMap<>(INGREDIENTS_MAP);
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(INGREDIENTS_MAP);
            ingredientFilesService.saveToFileIng(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = ingredientFilesService.readFromFileIng();
            INGREDIENTS_MAP = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostConstruct
    private void init() {

    }
}
