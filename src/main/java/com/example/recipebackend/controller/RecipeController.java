package com.example.recipebackend.controller;


import com.example.recipebackend.dto.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.recipebackend.service.RecipeService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getAllRecipes() throws SQLException {
        return recipeService.getAllRecipes();
    }

    // Implement methods for adding, updating, and deleting recipes
}
