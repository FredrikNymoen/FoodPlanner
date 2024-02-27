package ntnu.org.IDATG1005.grp3.dao.interfaces;

import ntnu.org.IDATG1005.grp3.model.Household;

/**
 * Defines the data access operations for managing households.
 */
public interface HouseholdDao {

  /**
   * Creates a new household with a generated name and join code.
   *
   * @return The newly created Household object, or null if creation fails.
   */
  Household createHousehold();

  /**
   * Updates the name of an existing household.
   *
   * @param h The household to update.
   * @param newName The new name to assign to the household.
   * @return true if the update succeeds, false otherwise.
   */
  boolean updateName(Household h, String newName);

  /**
   * Updates the join code of an existing household.
   *
   * @param h The household to update.
   * @param newJoinCode The new join code to assign to the household.
   * @return true if the update succeeds, false otherwise.
   */
  boolean updateJoinCode(Household h, String newJoinCode);

  /**
   * Finds a household by its join code.
   *
   * @param joinCode The join code of the household to find.
   * @return The found Household object, or null if no household matches the given join code.
   */
  Household findHouseholdByJoinCode(String joinCode);
}
