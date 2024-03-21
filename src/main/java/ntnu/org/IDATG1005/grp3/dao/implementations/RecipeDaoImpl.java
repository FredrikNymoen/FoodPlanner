package ntnu.org.IDATG1005.grp3.dao.implementations;

import ntnu.org.IDATG1005.grp3.dao.interfaces.RecipeDao;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ntnu.org.IDATG1005.grp3.model.objects.Tag;

public class RecipeDaoImpl implements RecipeDao {

  /**
   * Returns all recipes in the database in one call.
   *
   * @return List of recipes.
   */
  @Override
  public List<Recipe> findAllRecipes() {
    List<Recipe> recipes = new ArrayList<>();
    String sql = "SELECT * FROM recipe";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        RecipeInfo info = new RecipeInfo(
            rs.getString("title"),
            rs.getString("image_url"),
            rs.getInt("cook_time"),
            rs.getString("difficulty"),
            rs.getFloat("rating"),
            rs.getString("description")
        );

        int recipeId = rs.getInt("recipe_id");
        List<RecipeIngredient> recipeIngredients = getRecipeIngredients(recipeId, conn);
        List<Direction> directions = getDirections(recipeId, conn);
        List<Tag> tags = new ArrayList<>();

        Recipe recipe = new Recipe(info, recipeIngredients, directions, tags, rs.getInt("persons_adults"), rs.getInt("persons_children"), recipeId);
        recipes.add(recipe);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return recipes;
  }

  private List<RecipeIngredient> getRecipeIngredients(int recipeId, Connection conn) {
    List<RecipeIngredient> ingredients = new ArrayList<>();
    String sql = "SELECT ri.amount, i.ingredient_id, i.name, i.image, i.unit_id FROM recipe_ingredient ri "
        + "JOIN ingredient i ON ri.ingredient_id = i.ingredient_id WHERE ri.recipe_id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, recipeId);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          ingredients.add(new RecipeIngredient(
              new Ingredient(rs.getInt("ingredient_id"), rs.getString("name"), rs.getString("image"), MeasurementUnit.fromId(rs.getInt("unit_id"))),
              rs.getDouble("amount")
          ));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ingredients;
  }



  private List<Direction> getDirections(int recipeId, Connection conn) {
    List<Direction> directionsList = new ArrayList<>();
    String sql = "SELECT direction_step, direction FROM direction WHERE recipe_id = ? ORDER BY direction_step";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, recipeId);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          directionsList.add(new Direction(rs.getInt("direction_step"), rs.getString("direction")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return directionsList;
  }
}
