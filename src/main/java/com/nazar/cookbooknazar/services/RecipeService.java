package com.nazar.cookbooknazar.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazar.cookbooknazar.models.Recipe;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RecipeService {
    private final RecipeFilesService recipeFilesService;
    private Map<Long, Recipe> RECIPES_MAP = new HashMap<>();
    private long id = 1L;

    public RecipeService(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }
    @Nullable
    public Recipe addRecipe(Recipe recipe) {
        RECIPES_MAP.put(id++, recipe);
        saveToFile();
        return recipe;
    }
    @Nullable
    public Optional<Recipe> getRecipe(long id) {
        return Optional.ofNullable(RECIPES_MAP.get(id));
    }
    @Nullable
    public Optional<Recipe> update(long id, Recipe recipe) {
        saveToFile();
        return Optional.ofNullable(RECIPES_MAP.replace(id, recipe));
    }
    @Nullable
    public Optional<Recipe> delete(long id) {
        return Optional.ofNullable(RECIPES_MAP.remove(id));
    }
    @Nullable
    public Map<Long, Recipe> getAll() {
        return new HashMap<>(RECIPES_MAP);
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(RECIPES_MAP);
            recipeFilesService.saveToFileRec(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = recipeFilesService.readFromFileRec();
            RECIPES_MAP = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostConstruct
    private void init() {

    }
}
