package com.example.recipebackend.controller;


import com.example.recipebackend.dto.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.recipebackend.service.RecipeService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/getAllRecipes")
    public List<Recipe> getAllRecipes() throws SQLException {
        return recipeService.getAllRecipes();
    }

    @PostMapping("/addOneRecipe")
    public void addOneRecipe(@RequestBody Recipe recipe) throws SQLException {

        recipeService.addRecipe(recipe);
    }



    // Implement methods for adding, updating, and deleting recipes
}
