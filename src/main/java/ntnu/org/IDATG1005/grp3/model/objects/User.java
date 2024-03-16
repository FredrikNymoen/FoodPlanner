package ntnu.org.IDATG1005.grp3.model.objects;


/**
 * Represents a user with a unique ID, username, email address, and password.
 */
public class User {
  private final Integer userId;
  private String username;
  private Inventory inventory;
  private String password; // hashed at service layer, so stored in plain at ram
  private Household household;

  /**
   * Constructs a new User with the specified ID, username, email, and password.
   *
   * @param userId The unique identifier for the user.
   * @param username The username of the user.
   * @param password The password for the user account.
   */
  public User(Integer userId, String username, String password) {
    this.userId = userId;
    this.username = username;
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

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Return the household the user is part of.
   *
   * @return household that the user is part of, null if not part of any.
   */
  public Household getHousehold() {
    return household;
  }

  public void setHousehold(Household household) {
    this.household = household;
  }

  public boolean isAssociatedWithHousehold () {
    return household != null;
  }
}