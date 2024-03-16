package ntnu.org.IDATG1005.grp3.dao.implementations;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
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
  public User loginUser(String username, String password) {
    // updated SQL query to fetch the password hash along with other user details
    // I know this sucks but
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

        // Verify the password with the stored hash
        if (verifyPassword(password, storedHash)) {
          User user = new User(userId, userUsername, null);
          user.setHousehold(setHouseholdIfPartOf(householdId)); // check if user is part of a household
          user.setInventory(setInventoryIfExists(userId));

          return user;
        } else {
          // If password verification fails, return null or handle accordingly
          return null;
        }
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on finding a user", e);
    }
    // If user not found or any other issue, return null
    return null;
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
  public void saveUserInventory(Integer userId, Inventory inventory) {

    String sql = "INSERT INTO inventory_ingredient (user_id, ingredient_id, amount) VALUES (?, ?, ?) " +
        "ON DUPLICATE KEY UPDATE amount = VALUES(amount)";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      for (InventoryIngredient ingredient : inventory.getIngredients().values()) {
        pstmt.setInt(1, userId);
        pstmt.setInt(2, ingredient.getInventoryIngredientId());
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

      // if householdId is null, removes the user from any household
      if (householdId == null) {
        pstmt.setNull(1, Types.INTEGER);
      } else {
        pstmt.setInt(1, householdId);
      }
      pstmt.setLong(2, userId);

      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        // no rows affected means either the user doesn't exist or the household doesn't exist.
        logger.log(Level.WARNING, "No user found with the provided userId, or householdId does not exist.");
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on saving user household", e);
    }
  }

  public void updateUsername(Integer userId, String newUsername) throws UsernameAlreadyExistsException {
    // Check if the new username already exists in the database
    String checkSql = "SELECT COUNT(*) FROM user WHERE username = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

      checkStmt.setString(1, newUsername);
      ResultSet resultSet = checkStmt.executeQuery();
      if (resultSet.next() && resultSet.getInt(1) > 0) {
        // If count is greater than 0, then the username already exists
        throw new UsernameAlreadyExistsException("Username already exists: " + newUsername);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error when checking for existing username", e);
      // Handle more gracefully according to your error handling policies
    }

    // If we reach this point, it's safe to update the username
    String updateSql = "UPDATE user SET username = ? WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

      updateStmt.setString(1, newUsername);
      updateStmt.setInt(2, userId);
      int affectedRows = updateStmt.executeUpdate();
      if (affectedRows == 0) {
        logger.log(Level.WARNING, "No user found with the provided userId: " + userId);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error when updating username", e);
      // Handle more gracefully according to your error handling policies
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

  private Inventory setInventoryIfExists(Integer userId) {
    Inventory inventory = new Inventory(new HashMap<>()); // initialize an empty Inventory

    // fetch inventory items for the user
    String sql = "SELECT ii.inventory_ingredient_id, ii.amount, ii.ingredient_id, i.name, i.image, i.unit_id " +
        "FROM inventory_ingredient ii JOIN ingredient i ON ii.ingredient_id = i.ingredient_id " +
        "WHERE ii.user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, userId);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        int ingredientId = rs.getInt("ingredient_id");
        Ingredient ingredient = new Ingredient(
            rs.getInt("ingredient_id"),
            rs.getString("name"),
            rs.getString("image"),
            MeasurementUnit.fromUnitId(rs.getInt("unit_id")));
        double amount = rs.getDouble("amount");

        InventoryIngredient inventoryIngredient = new InventoryIngredient(ingredientId, ingredient, amount);

        inventory.getIngredients().put(ingredient, inventoryIngredient);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "SQL error on fetching user inventory", e);
    }

    return inventory;
  }
}