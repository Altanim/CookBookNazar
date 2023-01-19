package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.models.Recipe;
import com.nazar.cookbooknazar.services.RecipeService;
import com.nazar.cookbooknazar.services.ValidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@RestController
@Tag(name = "Рецепты", description = "рецепты")
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final ValidateService validateService;

    public RecipeController(RecipeService recipeService, ValidateService validateService) {
        this.recipeService = recipeService;
        this.validateService = validateService;
    }

    @Operation(summary = "Добавление рецепта", description = "Добавление рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Добавление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @PostMapping
    public ResponseEntity<Recipe> add(@RequestBody Recipe recipe) {
        if (validateService.isNotValid(recipe)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @Operation(summary = "Получение рецепта", description = "Получение рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Получение успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> get(@PathVariable long id) {
        return ResponseEntity.of(recipeService.getRecipe(id));
    }

    @Operation(summary = "Изменение рецепта", description = "Изменение рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Изменение прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable long id, @RequestBody Recipe recipe) {
        if (validateService.isNotValid(recipe)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.of(recipeService.update(id, recipe));
    }

    @Operation(summary = "Удаление рецепта", description = "Удаление рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Удаление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable long id) {
        return ResponseEntity.of(recipeService.delete(id));
    }

    @Operation(summary = "Получение списка рецептов", description = "Получение списка рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Получение успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @GetMapping
    public Map<Long, Recipe> getAll() {
        return recipeService.getAll();
    }

    @GetMapping("/download/{id}")
    @Operation(summary = "Получение рецепта по id", description = "Получение рецепта")
    @ApiResponse(responseCode = "200",
            description = "Успешно")
    public ResponseEntity downloadRecipeById(@PathVariable Long id) {
        try {
            Path path = recipeService.CreateRecipeTextFile(id);
            InputStreamResource inputStream = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.doc\"")
                    .contentLength(Files.size(path))
                    .body(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @GetMapping("/download/all")
    @Operation(summary = "Получение всех рецептов", description = "Получение всех рецептов")
    @ApiResponse(responseCode = "200",
            description = "Successfully")
    public ResponseEntity downloadAllRecipes() {
        try {
            Path path = recipeService.CreateRecipeTextFileAll();
            InputStreamResource inputStream = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AllRecipes.doc\"")
                    .contentLength(Files.size(path))
                    .body(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

