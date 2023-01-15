package com.nazar.cookbooknazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {
    private String recipeName;
    private int cookingTimeMinutes;
    private List<Ingredient> ingredients;
    private List<String> steps;
}
