package ntnu.org.IDATG1005.grp3.dao.implementations;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.model.Household;
import ntnu.org.IDATG1005.grp3.utility.Utility;

public class HouseholdDaoImpl implements HouseholdDao {

  private static final Logger logger = Logger.getLogger(HouseholdDaoImpl.class.getName());
  private static final int MAX_RETRY = 5;

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
              return new Household(name, joinCode, householdId);
            } else {
              logger.log(Level.SEVERE, "Creating household succeeded, but no ID was retrieved.");
              return null;
            }
          }
        } else {
          logger.log(Level.SEVERE, "Failed to generate a household");
          return null;
        }
      } catch (SQLException e) {
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

  @Override
  public Household findHouseholdByJoinCode(String joinCode) {
    String sql = "SELECT * FROM household WHERE join_code = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, joinCode);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return new Household(rs.getString(2), rs.getString(3), rs.getInt(1));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "There was an error with SQL query", e);
    }
    return null;
  }

  @Override
  public boolean updateName(int householdId, String newName) {
    String sql = "UPDATE household SET name = ? WHERE household_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, newName);
      pstmt.setInt(2, householdId);
      int affectedRows = pstmt.executeUpdate();

      return affectedRows > 0;
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "There was an error updating the household name", e);
      return false;
    }
  }

  @Override
  public boolean updateJoinCode(int householdId, String newJoinCode) {

    String sql = "UPDATE household SET join_code = ? WHERE household_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, newJoinCode);
      pstmt.setInt(2, householdId);
      int affectedRows = pstmt.executeUpdate();

      return affectedRows > 0;
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "There was an error updating the household join code", e);
      return false;
    }
  }
}