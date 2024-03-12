package ntnu.org.IDATG1005.grp3.dao.implementations;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.User;

/**
 * Implementation of the UserDao interface, providing concrete methods to interact with the database
 * for operations related to the User entity.
 */
public class UserDaoImpl implements UserDao {

  private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

  /**
   * Creates a new user in the database with the provided username, email, and password.
   *
   * @param username The username of the new user.
   * @param password The password for the new user account.
   * @return A new User object if the user was successfully created, or null if the operation failed.
   * @throws UsernameAlreadyExistsException If the specified username already exists in the database.
   */
  @Override
  public User createUser(String username, String password) throws UsernameAlreadyExistsException {
    String sql = "INSERT INTO user (username, password) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, username);
      pstmt.setString(2, password);
      pstmt.executeUpdate();

      try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int userId = generatedKeys.getInt(1);
          return new User(userId, username, password);
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
   * Updates an existing user's details in the database.
   *
   * @param user The user to update, containing the new state to be persisted.
   * @return true if the update was successful, false otherwise.
   * @throws UsernameAlreadyExistsException If the specified username already exists in the database.
   */
  @Override
  public boolean updateUser(User user) throws UsernameAlreadyExistsException {
    String sql = "UPDATE user SET username = ?, password = ? WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, user.getUsername());
      pstmt.setString(2, user.getPassword());
      pstmt.setInt(3, user.getUserId());

      int affectedRows = pstmt.executeUpdate();
      return affectedRows > 0;
    } catch (SQLException e) {
      if (e.getSQLState().startsWith("23")) {
        if (e.getMessage().contains("username")) {
          throw new UsernameAlreadyExistsException(user.getUsername());
        }
      }
      logger.log(Level.SEVERE, "There was an error updating the user", e);
      return false;
    }
  }
}