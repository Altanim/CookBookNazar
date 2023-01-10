package com.nazar.cookbooknazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient  {
    private String ingName;
    private String measureUnit;
    private int weight;
    private final int id;
    private static int idGenerator = 0;
    public Ingredient(String ingName, String measureUnit, int weight) {
        this.ingName = ingName;
        this.measureUnit = measureUnit;
        this.weight = weight;
        id = idGenerator++;
    }

}