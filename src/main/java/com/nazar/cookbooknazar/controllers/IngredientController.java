package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.services.IngredientService;
import com.nazar.cookbooknazar.services.ValidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@Tag(name = "Ингредиенты", description = "всё, что относится к ингредиентам")
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
        if (!validateService.isNotValid(ingredient)) {
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
        return ResponseEntity.of(Objects.requireNonNull(ingredientService.getIngredient(id)));
    }

    @Operation(summary = "Изменение ингредиента", description = "Изменение ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Изменение прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable long id, @RequestBody Ingredient ingredient) {
        if (!validateService.isNotValid(ingredient)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.of(Objects.requireNonNull(ingredientService.update(id, ingredient)));
    }

    @Operation(summary = "Удаление ингредиента", description = "Удаление ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Удаление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable long id) {
        return ResponseEntity.of(Objects.requireNonNull(ingredientService.delete(id)));
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
}