package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.models.Ingredient;
import com.nazar.cookbooknazar.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/add")
    public Ingredient addIgredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        return ResponseEntity.of(ingredientService.getIngredient(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> edit(@PathVariable long id,
                           @RequestBody Ingredient ingredient){
        return ResponseEntity.of(ingredientService.edit(id, ingredient));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable long id){
        return ResponseEntity.of(ingredientService.delete(id));
    }
    @GetMapping
    public Map<Long, Ingredient> getAll(){
        return ingredientService.getAll();

    }
}