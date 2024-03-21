package ntnu.org.IDATG1005.grp3.dao.implementations;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.AuthenticationFailedException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Tag;
import ntnu.org.IDATG1005.grp3.model.objects.User;

/**
 * Implementation of UserDao for managing user data access.
 */
public class UserDaoImpl implements UserDao {

  private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public User registerUser(String username, String password) throws UsernameAlreadyExistsException {
    String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, username);
      pstmt.setString(2, password);
      pstmt.executeUpdate();

      try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int userId = generatedKeys.getInt(1);
          User user = new User(userId, username, password);
          user.setInventory(new Inventory(new HashMap<>()));
          return user;
        }
      }
    } catch (SQLException e) {
      if (e.getSQLState().startsWith("23")) {
        if (e.getMessage().contains("username")) {
          throw new UsernameAlreadyExistsException(username);
        }
      }
      logger.log(Level.SEVERE, "There was an error with the SQL query on creating a user", e);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User loginUser(String username, String password) throws AuthenticationFailedException {
    String sql = "SELECT user_id, username, household_id, password FROM user WHERE username = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, username);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        int userId = rs.getInt("user_id");
        String userUsername = rs.getString("username");
        int householdId = rs.getInt("household_id");
        String storedHash = rs.getString("password"); // fetch the stored hash

        // verify the password with the stored hash
        if (verifyPassword(password, storedHash)) {
          User user = new User(userId, userUsername, null);
          user.setHousehold(
              setHouseholdIfPartOf(householdId)); // check if user is part of a household
          user.setInventory(setInventoryIfExists(userId));
          user.setChosenRecipes(setChosenRecipesIfExists(userId));

          return user;
        } else {
          throw new AuthenticationFailedException();
        }
      } else {
        throw new AuthenticationFailedException();
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on finding a user", e);
      return null; // could be better error handling
    }
  }

  private boolean verifyPassword(String password, String storedHash) {
    Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
    char[] passwordChars = password.toCharArray();
    try {
      return argon2.verify(storedHash, passwordChars);
    } finally {
      argon2.wipeArray(passwordChars);
    }
  }

  @Override
  public void saveUserInventory(Integer userId, Collection<InventoryIngredient> ingredients) {
    String sql = "INSERT INTO inventory_ingredient (user_id, ingredient_id, amount) VALUES (?, ?, ?) " +
        "ON DUPLICATE KEY UPDATE amount = VALUES(amount)";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      for (InventoryIngredient ingredient : ingredients) {
        pstmt.setInt(1, userId);
        pstmt.setInt(2, ingredient.getIngredient().getIngredientId());
        pstmt.setBigDecimal(3, BigDecimal.valueOf(ingredient.getQuantity()));
        pstmt.executeUpdate();
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on saving user inventory", e);
    }
  }

  @Override
  public void saveUserHousehold(Integer userId, Integer householdId) {
    String sql = "UPDATE user SET household_id = ? WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      if (householdId == null) {
        pstmt.setNull(1, Types.INTEGER);
      } else {
        pstmt.setInt(1, householdId);
      }
      pstmt.setLong(2, userId);
      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        logger.log(Level.WARNING, "No user found with the provided userId, or householdId does not exist.");
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on saving user household", e);
    }
  }


  public void updateUsername(Integer userId, String newUsername) throws UsernameAlreadyExistsException {
    String checkSql = "SELECT COUNT(*) FROM user WHERE username = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
      checkStmt.setString(1, newUsername);
      ResultSet resultSet = checkStmt.executeQuery();
      if (resultSet.next() && resultSet.getInt(1) > 0) {
        throw new UsernameAlreadyExistsException("Username already exists: " + newUsername);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error when checking for existing username", e);
    }
    String updateSql = "UPDATE user SET username = ? WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
      updateStmt.setString(1, newUsername);
      updateStmt.setInt(2, userId);
      int affectedRows = updateStmt.executeUpdate();
      if (affectedRows == 0) {
        logger.log(Level.WARNING, () -> "No user found with the provided userId: " + userId);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error when updating username", e);
    }
  }

  public void updatePassword(Integer userId, String newPassword) {
    // update the password for a given user
    String sql = "UPDATE user SET password = ? WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, newPassword);
      pstmt.setInt(2, userId);
      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        logger.log(Level.WARNING, "No user found with the provided userId: " + userId);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error when updating password", e);
    }
  }


  private Household setHouseholdIfPartOf(Integer householdId) {
    if (householdId == null) {
      return null; // not part of any household
    }

    String sql = "SELECT name, join_code FROM household WHERE household_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, householdId);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        String name = rs.getString("name");
        String joinCode = rs.getString("join_code");

        return new Household(householdId, name, joinCode);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on fetching household", e);
    }
    return null;
  }

  public Inventory setInventoryIfExists(Integer userId) {
    Inventory inventory = new Inventory(new HashMap<>()); // initialize an empty Inventory

    // fetch inventory items for the user
    String sql = "SELECT ii.amount, ii.ingredient_id, i.name, i.image, i.unit_id " +
        "FROM inventory_ingredient ii JOIN ingredient i ON ii.ingredient_id = i.ingredient_id " +
        "WHERE ii.user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, userId);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        Ingredient ingredient = new Ingredient(
            rs.getInt("ingredient_id"),
            rs.getString("name"),
            rs.getString("image"),
            MeasurementUnit.fromId(rs.getInt("unit_id")));
        double amount = rs.getDouble("amount");

        InventoryIngredient inventoryIngredient = new InventoryIngredient(ingredient, amount);

        inventory.getIngredients().put(ingredient, inventoryIngredient);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on fetching user inventory", e);
    }

    return inventory;
  }

  public List<Recipe> setChosenRecipesIfExists(Integer userId) {
    List<Recipe> recipes = new ArrayList<>();

    // fetch recipe ids associated with user
    String sql = "SELECT recipe_id " +
        "FROM user_recipe " +
        "WHERE user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, userId);
      ResultSet rs = pstmt.executeQuery();

      // collect all recipe ids
      Set<Integer> recipeIdsForUser = new HashSet<>();
      while (rs.next()) {
        int recipeId = rs.getInt("recipe_id");
        recipeIdsForUser.add(recipeId);
      }

      // filter MainApp.appRecipes by the collected recipe ids
      for (Recipe recipe : MainApp.appRecipes) {
        if (recipeIdsForUser.contains(recipe.getRecipeId())) {
          recipes.add(recipe);
        }
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on fetching user's recipes", e);
    }

    return recipes;
  }

  /**
   * Persist chosen recipes to the db.
   *
   * @param userId the id of the user which want.
   * @param recipeList the list of recipes to be stored.
   */
  public void saveChosenRecipes(Integer userId, List<Recipe> recipeList) {

    // convert list to set of integers
    Set<Integer> newRecipeIds = new HashSet<>();
    for (Recipe r : recipeList) {
      newRecipeIds.add(r.getRecipeId());
    }

    // fetch currently saved recipes for the user
    String fetchSql = "SELECT recipe_id FROM user_recipe WHERE user_id = ?";
    Set<Integer> currentRecipes = new HashSet<>();

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement fetchStmt = conn.prepareStatement(fetchSql)) {
      fetchStmt.setInt(1, userId);
      ResultSet rs = fetchStmt.executeQuery();

      while (rs.next()) {
        currentRecipes.add(rs.getInt("recipe_id"));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error when fetching user's recipes", e);
      return;
    }

    // determine recipes to delete and to add
    Set<Integer> toDelete = new HashSet<>(currentRecipes);
    toDelete.removeAll(newRecipeIds);
    Set<Integer> toAdd = new HashSet<>(newRecipeIds);
    toAdd.removeAll(currentRecipes);

    // delete old recipes
    if (!toDelete.isEmpty()) {
      String deleteSql = "DELETE FROM user_recipe WHERE user_id = ? AND recipe_id = ?";
      try (Connection conn = DatabaseConnection.getConnection();
          PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
        for (Integer recipeId : toDelete) {
          deleteStmt.setInt(1, userId);
          deleteStmt.setInt(2, recipeId);
          deleteStmt.addBatch();
        }
        deleteStmt.executeBatch();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, "SQL error when deleting recipes", e);
      }
    }

    // insert new recipes
    if (!toAdd.isEmpty()) {
      String insertSql = "INSERT INTO user_recipe (user_id, recipe_id) VALUES (?, ?)";
      try (Connection conn = DatabaseConnection.getConnection();
          PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
        for (Integer recipeId : toAdd) {
          insertStmt.setInt(1, userId);
          insertStmt.setInt(2, recipeId);
          insertStmt.addBatch();
        }
        insertStmt.executeBatch();
      } catch (SQLException e) {
        logger.log(Level.SEVERE, "SQL error when inserting new recipes", e);
      }
    }
  }

  @Override
  public void deleteUser(Integer userId) {
    String sql = "DELETE FROM user WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, userId);
      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        logger.log(Level.WARNING, "No user found with the provided userId: " + userId);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error when deleting user", e);
    }
  }
}