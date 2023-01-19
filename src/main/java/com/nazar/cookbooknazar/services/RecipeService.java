package com.nazar.cookbooknazar.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazar.cookbooknazar.models.Recipe;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {
    private final RecipeFilesService recipeFilesService;
    private Map<Long, Recipe> RECIPES_MAP = new HashMap<>();
    private long id = 1L;

    public RecipeService(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }

    @PostConstruct
    private void init() {

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

    public Path CreateRecipeTextFile(Long id) throws IOException {
        Path path = recipeFilesService.CreateTempFile("RecipeFile");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            writer.append(RECIPES_MAP.get(id).getRecipeName() + "\n" +
                    RECIPES_MAP.get(id).getCookingTimeMinutes() + "\n" +
                    "Ингредиенты:" + "\n" +
                    RECIPES_MAP.get(id).getIngredients() + "\n" +
                    "Шаги приготовления: " + "\n" +
                    RECIPES_MAP.get(id).getIngredients() + "\n");
        }
        return path;
    }

    public Path CreateRecipeTextFileAll() throws IOException {
        Path path = recipeFilesService.CreateTempFile("RecipeFiles");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            writer.append(RECIPES_MAP.toString());
        }
        return path;
    }


}
