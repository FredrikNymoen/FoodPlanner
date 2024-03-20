package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.AuthenticationFailedException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.FailedToLoadInventoryException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

public class UserService {
  private final UserDao userDao;
  private static final int MAX_USERNAME_LENGTH = 20;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Creates a new user with hashed password.
   * @param username The desired unique username.
   * @param password The user's password.
   * @return The created User object.
   * @throws UsernameAlreadyExistsException If the username already exists.
   * @throws IllegalArgumentException If inputs are invalid.
   */
  public User registerUser(String username, String password) throws UsernameAlreadyExistsException, IllegalArgumentException {
    validateUsernameAndPassword(username, password);
    String hashedPassword = hashPassword(password);
    return userDao.registerUser(username, hashedPassword);
  }

  /**
   * Logs in a user with provided credentials.
   * @param username The username of the user.
   * @param password The password of the user.
   * @return The User object on success, null on failure.
   */
  public User authenticateUser(String username, String password) throws AuthenticationFailedException {
    User user = userDao.loginUser(username, password);
    if (user != null) {
      return user;
    } else {
      throw new AuthenticationFailedException();
    }
  }

  /**
   * Persists a user's inventory.
   * @param user The user whose inventory is to be saved.
   */
  public void saveUserInventory(User user) throws FailedToLoadInventoryException {
    if (user == null || user.getInventory() == null || user.getUserId() == null) {
      System.out.println("Saving inventory failed because of null");
      return;
    }
    try {
      userDao.saveUserInventory(user.getUserId(), user.getInventory().getIngredients().values());
    } catch (Exception e) {
      throw new FailedToLoadInventoryException();
    }
  }


  /**
   * Updates a user's username.
   * @param user The user to update.
   * @param newUsername The new username.
   */
  public void changeUserUsername(User user, String newUsername) throws UsernameAlreadyExistsException, IllegalArgumentException {
    if (user == null || newUsername == null || newUsername.isEmpty()) {
      throw new IllegalArgumentException("User and new username cannot be null or empty.");
    }
    if (newUsername.length() > MAX_USERNAME_LENGTH) {
      throw new IllegalArgumentException("Username cannot exceed " + MAX_USERNAME_LENGTH + " characters.");
    }
    userDao.updateUsername(user.getUserId(), newUsername);
    user.setUsername(newUsername);
  }


  /**
   * Updates a user's password.
   * @param user The user to update.
   * @param newPassword The new password.
   */
  public void changeUserPassword(User user, String newPassword) {
    if (user == null || newPassword == null || newPassword.isEmpty()) {
      throw new IllegalArgumentException("User and new password cannot be null or empty.");
    }
    String hashedPassword = hashPassword(newPassword);
    userDao.updatePassword(user.getUserId(), hashedPassword);
  }

  /**
   * Persists the household for a user.
   * @param user The user to associate with a household.
   */
  public void saveUserHousehold(User user) {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null.");
    }
    Integer householdId = (user.getHousehold() != null) ? user.getHousehold().getHouseholdId() : null;
    userDao.saveUserHousehold(user.getUserId(), householdId);
  }

  /**
   * Persists the household for a user.
   * @param user The user to associate with a household.
   */
  public void saveChosenRecipes(User user) {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null.");
    }
    userDao.saveChosenRecipes(user.getUserId(), user.getChosenRecipes());
  }

  private String hashPassword(String password) {
    Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
    char[] passwordChars = password.toCharArray();
    try {
      return argon2.hash(2, 65536, 1, passwordChars);
    } finally {
      argon2.wipeArray(passwordChars);
    }
  }

  private void validateUsernameAndPassword(String username, String password) {
    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
      throw new IllegalArgumentException("Username and password cannot be null or empty.");
    }
    if (username.length() > MAX_USERNAME_LENGTH) {
      throw new IllegalArgumentException("Username cannot exceed " + MAX_USERNAME_LENGTH + " characters.");
    }
  }
}
