package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.model.User;

/**
 * Provides service operations for managing users.
 */
public class UserService {
  private final UserDao userDao = new UserDaoImpl();

  /**
   * Creates a new user with the specified username, email, and password.
   *
   * @param username The username of the new user.
   * @param email The email of the new user.
   * @param password The password for the new user account.
   * @return The newly created User object, or null if the creation failed.
   */
  public User createUser(String username, String email, String password) {
    return userDao.createUser(username, email, password);
  }

  /**
   * Updates an existing user's details in the database.
   *
   * @param user The user to update, containing the updated information.
   * @return true if the update was successful, false otherwise.
   */
  public boolean updateUserDetails(User user) {
    return userDao.updateUser(user);
  }
}