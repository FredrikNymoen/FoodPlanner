package ntnu.org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Inventory;

/**
 * Represents a user with a unique ID, username, email address, and password.
 */
public class User {
  private final Integer userId;
  private String username;
  private Inventory inventory;
  private String email;
  private String password; // Storing password in this manner is not a best practice

  /**
   * Constructs a new User with the specified ID, username, email, and password.
   *
   * @param userId The unique identifier for the user.
   * @param username The username of the user.
   * @param email The email address of the user.
   * @param password The password for the user account.
   */
  public User(Integer userId, String username, String email, String password) {
    this.userId = userId;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  /**
   * Gets the user's ID.
   *
   * @return The unique identifier of the user.
   */
  public int getUserId() {
    return userId;
  }

  /**
   * Gets the user's username.
   *
   * @return The username of the user.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the user's email address.
   *
   * @return The email address of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the user's password.
   * Caution: Returning passwords in plain text is a security risk.
   *
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the user's username.
   *
   * @param username The new username for the user.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Sets the user's email address.
   *
   * @param email The new email address for the user.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the user's password.
   * Again not the best security practice.
   *
   * @param password The new password for the user.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  public Inventory getInventory() {
    return inventory;
  }
}