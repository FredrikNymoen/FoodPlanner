package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

/**
 * Manages user accounts, offering functions to create and update users' persistent info while ensuring some data integrity and security.
 * Utilizes <code>UserDao</code> for persistence and Argon2 for password hashing.
 *
 * <p>Key methods include <code>createUser</code> for new users and <code>updateUserDetails</code> for existing users,
 * both validating data for format, length, and uniqueness.</p>
 *
 * <p>Example:</p>
 * <pre>
 * UserService userService = new UserService();
 * // Create a new user
 * try {
 *     User newUser = userService.createUser("johnDoe", "securePassword123");
 *     System.out.println("User created successfully: " + newUser.getUsername());
 * } catch (UsernameAlreadyExistsException | IllegalArgumentException | Exception e) {
 *     System.out.println("Failed to create user: " + e.getMessage());
 * }
 * // Update a user
 * try {
 *     User existingUser = new User(1, "janeDoe", "newSecurePassword456");
 *     userService.updateUserDetails(existingUser);
 *     System.out.println("User updated successfully");
 * } catch (UsernameAlreadyExistsException | IllegalArgumentException | Exception e) {
 *     System.out.println("Failed to update user: " + e.getMessage());
 * }
 * </pre>
 */
public class UserService {
  private final UserDao userDao;
  private static final int MAX_USERNAME_LENGTH = 20;
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Creates a new user with unique username and a password. Validates inputs and uses Argon2 for password hashing.
   *
   * @param username Unique username within length limit.
   * @param password Password to be hashed.
   * @return Newly created User object.
   * @throws UsernameAlreadyExistsException if username exists.
   * @throws IllegalArgumentException for invalid inputs.
   */
  public User createUser(String username, String password)
      throws UsernameAlreadyExistsException, IllegalArgumentException {

    validate(username, password);
    String hashedPassword = hashPassword(password);

    return userDao.createUser(username, hashedPassword);
  }

  /**
   * Updates an existing user's details, checking for uniqueness and adhering to format and length constraints.
   *
   * @param user User object with updated info and existing identifier.
   * @return True if successful.
   * @throws UsernameAlreadyExistsException If updated username is not unique.
   * @throws IllegalArgumentException If information is invalid or does not meet constraints.
   */
  public boolean updateUserDetails(User user)
      throws UsernameAlreadyExistsException, IllegalArgumentException {
    validate(user.getUsername(), user.getPassword());
    return userDao.updateUser(user);
  }

  private String hashPassword(String password) {
    // hash password with Argon2
    Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
    char[] passwordChars = password.toCharArray();
    String hashedPassword;
    try {
      hashedPassword = argon2.hash(2, 65536, 1, passwordChars);
    } finally {
      argon2.wipeArray(passwordChars);
    }

    return hashedPassword;
  }

  private void validate (String username, String password) {
    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
      throw new IllegalArgumentException("Username, and password cannot be null or empty.");
    }
    if (username.length() > MAX_USERNAME_LENGTH) {
      throw new IllegalArgumentException("Username cannot exceed " + MAX_USERNAME_LENGTH + " characters.");
    }
  }
}