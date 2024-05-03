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
    public void addRecipe(Recipe recipe) throws SQLException {
        String insertSql = "INSERT INTO recipes (recipe_name, ingredients, instructions) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            // Set the recipe name
            statement.setString(1, recipe.getName());

            // Convert ingredients list to array for insertion
            String[] ingredientsArray = recipe.getIngredients().toArray(new String[0]);
            statement.setArray(2, connection.createArrayOf("VARCHAR", ingredientsArray));

            // Convert instructions list to array for insertion
            String[] instructionsArray = recipe.getInstructions().toArray(new String[0]);
            statement.setArray(3, connection.createArrayOf("VARCHAR", instructionsArray));

            // Execute the insert statement
            statement.executeUpdate();
        }
    }

}
