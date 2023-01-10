package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.models.Recipe;
import com.nazar.cookbooknazar.services.IngredientService;
import com.nazar.cookbooknazar.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("/{recipeId}")
    public ResponseEntity getRecipe(@PathVariable Long recipeId) {
            Recipe recipe = recipeService.getRecipe(recipeId);
            if(recipe == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(recipe);
        }
        @PostMapping("/add")
        public ResponseEntity addRecipe(@RequestBody Recipe recipe) {
            Recipe addedRecipe = recipeService.addRecipe(recipe);
            return ResponseEntity.ok(addedRecipe);
        }
}
