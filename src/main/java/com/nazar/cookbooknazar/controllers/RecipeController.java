package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.models.Recipe;
import com.nazar.cookbooknazar.services.IngredientService;
import com.nazar.cookbooknazar.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
   private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @PostMapping("/add")
        public Recipe addRecipe(@RequestBody Recipe recipe) {
            Recipe addedRecipe = recipeService.addRecipe(recipe);
            return recipeService.addRecipe(recipe);
        }
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        return ResponseEntity.of(recipeService.getRecipe(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> edit(@PathVariable long id,
                                           @RequestBody Recipe recipe){
        return ResponseEntity.of(recipeService.edit(id, recipe));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable long id){
        return ResponseEntity.of(recipeService.delete(id));
    }
    @GetMapping
    public Map<Long, Recipe> getAll(){
        return recipeService.getAll();

    }
}
