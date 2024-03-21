package ntnu.org.IDATG1005.grp3.model.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a household, including its identification, name, join code, and users.
 */
public class Household {

  private final Integer householdId;
  private String name;
  private String joinCode;
  private final ArrayList<User> users;

  /**
   * Constructs a new Household with the specified name, join code, and ID.
   * Initializes an empty list of users.
   *
   * @param name The name of the household.
   * @param joinCode The join code for the household.
   * @param householdId The unique identifier for the household.
   */
  public Household(Integer householdId, String name, String joinCode) {
    this.householdId = householdId;
    this.name = name;
    this.joinCode = joinCode;
    users = new ArrayList<>();
  }

  /**
   * Gets the household ID.
   *
   * @return The unique identifier of the household.
   */
  public Integer getHouseholdId() {
    return householdId;
  }

  /**
   * Gets the household name.
   *
   * @return The name of the household.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the join code of the household.
   *
   * @return The join code for the household.
   */
  public String getJoinCode() {
    return joinCode;
  }

  /**
   * Gets the list of users in the household.
   *
   * @return A list of users belonging to the household.
   */
  public List<User> getUsers() {
    return users;
  }

  /**
   * Sets the name of the household.
   *
   * @param name The new name for the household.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the join code for the household.
   *
   * @param joinCode The new join code for the household.
   */
  public void setJoinCode(String joinCode) {
    this.joinCode = joinCode;
  }
}