package ntnu.org.IDATG1005.grp3.dao.implementations;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.utility.Utility;

/**
 * Implementation of the HouseholdDao interface for interacting with household data in the database.
 */
public class HouseholdDaoImpl implements HouseholdDao {

  private static final Logger logger = Logger.getLogger(HouseholdDaoImpl.class.getName());
  private static final int MAX_RETRY = 5;

  /**
   * Creates a new household in the database with a unique name and join code.
   *
   * @return The created Household object, or null if the creation failed.
   */
  @Override
  public Household createHousehold() {

    String sql = "INSERT INTO household (name, join_code) VALUES (?, ?)";

    int attempt = 0;

    while (attempt < MAX_RETRY) {

      String name = Utility.generateRandomName("Household", 3);
      String joinCode = Utility.generateRandomJoinCode(5);

      try (Connection conn = DatabaseConnection.getConnection();
          PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        pstmt.setString(1, name);
        pstmt.setString(2, joinCode);
        int affectedRows = pstmt.executeUpdate();

        if (affectedRows > 0) {
          try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
              int householdId = generatedKeys.getInt(1);
              return new Household(householdId, name, joinCode);
            } else {
              logger.log(Level.SEVERE, "Creating household succeeded, but no ID was retrieved.");
              return null;
            }
          }
        } else {
          logger.log(Level.SEVERE, "Failed to create a household");
          return null;
        }
      } catch (SQLException e) {
        // Handle unique constraint violation for join codes
        if (e.getSQLState().startsWith("23")) {
          logger.log(Level.WARNING, "Join code already exists, retrying...", e);
          attempt++;
          continue;
        }
        logger.log(Level.SEVERE, "There was an error with the SQL query", e);
        return null;
      }
    }
    logger.log(Level.SEVERE, "Max retry attempts reached, failed to generate a unique join code");
    return null;
  }

  /**
   * Finds a household by its join code.
   *
   * @param joinCode The join code of the household to find.
   * @return The found Household object, or null if not found.
   * @throws HouseholdNotFoundException if the join code is not found.
   */
  @Override
  public Household findHouseholdByJoinCode(String joinCode) throws HouseholdNotFoundException {
    String sql = "SELECT household_id, name, join_code FROM household WHERE join_code = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, joinCode);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        Household household = new Household(rs.getInt("household_id"), rs.getString("name"), rs.getString("join_code"));
        refreshUsers(household); // retrieves users associated with the household
        return household;
      } else {
        throw new HouseholdNotFoundException(joinCode);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "There was an error with SQL query", e);
    }
    return null;
  }

  /**
   * Updates the name of an existing household.
   *
   * @param household The household to update with a new name.
   * @return true if the update was successful, false otherwise.
   */
  @Override
  public boolean updateHouseholdName(Household household) {
    String sql = "UPDATE household SET name = ? WHERE household_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, household.getName());
      pstmt.setInt(2, household.getHouseholdId());

      // execute the update
      int affectedRows = pstmt.executeUpdate();

      return affectedRows > 0;
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "There was an error updating the household name", e);
      return false;
    }
  }

  /**
   * Updates the join code of an existing household.
   *
   * @param household The household to update with a new join code.
   * @return true if the update was successful, false otherwise.
   */
  @Override
  public boolean updateHouseholdJoinCode(Household household) {
    String sql = "UPDATE household SET join_code = ? WHERE household_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, household.getJoinCode());
      pstmt.setInt(2, household.getHouseholdId());

      // execute the update
      int affectedRows = pstmt.executeUpdate();

      return affectedRows > 0;
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "There was an error updating the household join code", e);
      return false;
    }
  }

  @Override
  public boolean refreshUsers(Household household) {
    String sql = "SELECT user_id, username " +
        "FROM user " +
        "WHERE household_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, household.getHouseholdId());

      // execute the query
      ResultSet rs = pstmt.executeQuery();

      // clear all users
      household.getUsers().clear();

      while (rs.next()) {
        int userId = rs.getInt("user_id");
        String username = rs.getString("username");
        household.getUsers().add(new User(userId, username, null));
      }

      return !household.getUsers().isEmpty();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "There was an error querying for users in the household", e);
      return false;
    }
  }
}