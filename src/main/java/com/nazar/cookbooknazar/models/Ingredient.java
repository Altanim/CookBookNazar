package com.nazar.cookbooknazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;


public class Ingredient  {
    private String ingName;
    private int weight;
    private String measureUnit;

    public Ingredient() {
    }

    public Ingredient(String ingName, int weight, String measureUnit) {
        this.ingName = ingName;
        this.weight = weight;
        this.measureUnit = measureUnit;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}