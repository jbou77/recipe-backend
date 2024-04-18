package com.example.recipebackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {
    private String name;
    private List<String> ingredients;
    private List<String> instructions;
}
