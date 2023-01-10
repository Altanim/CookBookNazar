package com.nazar.cookbooknazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private String recipeName;
    private int cookingTimeMinutes;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<String> steps = new ArrayList<>();
    private int id;
    private int idGenerator = 0;

    public Recipe(String recipeName, int cookingTimeMinutes, List<Ingredient> ingredients, List<String> steps) {
        id = idGenerator++;
    }
}
