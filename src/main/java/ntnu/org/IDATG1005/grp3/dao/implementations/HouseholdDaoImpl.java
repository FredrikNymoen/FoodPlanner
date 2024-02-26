package ntnu.org.IDATG1005.grp3.dao.implementations;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.model.Household;

public class HouseholdDaoImpl implements HouseholdDao {

  private static final Logger logger = Logger.getLogger(HouseholdDaoImpl.class.getName());

  @Override
  public Household createHousehold(Household household) {
    String sql = "INSERT INTO household (name, join_code) VALUES (?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, household.getName());
      pstmt.setString(2, household.getJoinCode());
      int affectedRows = pstmt.executeUpdate();

      if (affectedRows > 0) {
        return new Household(household.getName(), household.getJoinCode());
      } else {
        logger.log(Level.SEVERE, "Failed to generate a household");
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "There was an error with SQL query", e);
    }
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
        return new Household(rs.getString("name"), rs.getString("join_code"));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "There was an error with SQL query", e);
    }
    return null;
  }
}