package com.nazar.cookbooknazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient  {
    private String ingName;
    private int weight;
    private String measureUnit;
}