package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    IngredientService ingredientService;
    @GetMapping("/{ingredientId}")
    public ResponseEntity getIngredient(@PathVariable Long ingId) {
        Ingredient ingredient = ingredientService.getIngredient(ingId);
        if(ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @PostMapping("/add")
    public ResponseEntity addIgredient(@RequestBody Ingredient ingredient) {
        Ingredient addedIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(addedIngredient);
    }
}