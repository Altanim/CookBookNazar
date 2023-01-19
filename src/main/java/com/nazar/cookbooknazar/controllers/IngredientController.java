package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.services.IngredientService;
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
import java.util.Objects;

@RestController
@Tag(name = "Ингредиенты", description = "ингредиенты")
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
    private final ValidateService validateService;

    public IngredientController(IngredientService ingredientService, ValidateService validateService) {
        this.ingredientService = ingredientService;
        this.validateService = validateService;
    }

    @Operation(summary = "Добавление ингредиента", description = "Добавление ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Добавление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @PostMapping
    public ResponseEntity<Ingredient> add(@RequestBody Ingredient ingredient) {
        if (validateService.isNotValid(ingredient)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @Operation(summary = "Получение ингредиента", description = "Получение ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Получение прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> get(@PathVariable long id) {
        return ResponseEntity.of(ingredientService.getIngredient(id));
    }

    @Operation(summary = "Изменение ингредиента", description = "Изменение ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Изменение прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable long id, @RequestBody Ingredient ingredient) {
        if (validateService.isNotValid(ingredient)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.of(ingredientService.update(id, ingredient));
    }

    @Operation(summary = "Удаление ингредиента", description = "Удаление ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Удаление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable long id) {
        return ResponseEntity.of(ingredientService.delete(id));
    }

    @Operation(summary = "Получение списка ингредиентов", description = "Получение списка ингредиентов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Получение успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @GetMapping
    public Map<Long, Ingredient> getAll() {
        return ingredientService.getAll();
    }

    @GetMapping("/download/{id}")
    @Operation(summary = "Получение ингредиента по id", description = "Получение ингредиента")
    @ApiResponse(responseCode = "200",
            description = "Успешно")
    public ResponseEntity downloadIngredientById(@PathVariable Long id) {
        try {
            Path path = ingredientService.CreateIngredientTextFile(id);
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
    @Operation(summary = "Получение всех ингредиентов", description = "Получение всех ингредиентов")
    @ApiResponse(responseCode = "200",
            description = "Успешно")
    public ResponseEntity downloadAllIngredients() {
        try {
            Path path = ingredientService.CreateIngredientTextFileAll();
            InputStreamResource inputStream = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AllIngredients.doc\"")
                    .contentLength(Files.size(path))
                    .body(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}