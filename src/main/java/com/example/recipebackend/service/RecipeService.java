package com.example.recipebackend.service;

import com.example.recipebackend.dto.Recipe;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    private Connection connection;

    public RecipeService() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipes";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setName(resultSet.getString("recipe_name"));
                // Get ingredients as List<String>
                Array ingredientsArray = resultSet.getArray("ingredients");
                if (ingredientsArray != null) {
                    String[] ingredients = (String[]) ingredientsArray.getArray();
                    recipe.setIngredients(List.of(ingredients));
                } else {
                    recipe.setIngredients(new ArrayList<>());
                }

                // Get instructions as List<String>
                Array instructionsArray = resultSet.getArray("instructions");
                if (instructionsArray != null) {
                    String[] instructions = (String[]) instructionsArray.getArray();
                    recipe.setInstructions(List.of(instructions));
                } else {
                    recipe.setInstructions(new ArrayList<>());
                }

                recipes.add(recipe);
            }
        }
        return recipes;
    }

    // Implement methods for adding, updating, and deleting recipes as needed
}
