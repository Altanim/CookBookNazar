package com.nazar.cookbooknazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


public class Recipe {
    private String recipeName;
    private int cookingTimeMinutes;
    private List<Ingredient> ingredients;
    private List<String> steps;

    public Recipe() {
    }

    public Recipe(String recipeName, int cookingTimeMinutes, List<Ingredient> ingredients, List<String> steps) {
        this.recipeName = recipeName;
        this.cookingTimeMinutes = cookingTimeMinutes;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getCookingTimeMinutes() {
        return cookingTimeMinutes;
    }

    public void setCookingTimeMinutes(int cookingTimeMinutes) {
        this.cookingTimeMinutes = cookingTimeMinutes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
