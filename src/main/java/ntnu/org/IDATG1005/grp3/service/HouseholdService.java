package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.implementations.HouseholdDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.model.Household;

/**
 * Provides service operations for managing households.
 */
public class HouseholdService {
  private final HouseholdDao householdDao = new HouseholdDaoImpl();

  /**
   * Creates a new household.
   *
   * @return The newly created Household object.
   */
  public Household createHousehold() {
    return householdDao.createHousehold();
  }

  /**
   * Finds a household by its join code.
   *
   * @param joinCode The join code associated with the household.
   * @return The Household object if found, or null if not found.
   */
  public Household findHouseholdByJoinCode(String joinCode) {
    return householdDao.findHouseholdByJoinCode(joinCode);
  }

  /**
   * Updates the name of an existing household.
   *
   * @param h The household to update.
   * @param newName The new name to assign to the household.
   * @return true if the update was successful, false otherwise.
   */
  public boolean updateHouseholdName(Household h, String newName) {
    return householdDao.updateName(h, newName);
  }

  /**
   * Updates the join code of an existing household.
   *
   * @param h The household to update.
   * @param newJoinCode The new join code to assign to the household.
   * @return true if the update was successful, false otherwise.
   */
  public boolean updateHouseholdJoinCode(Household h, String newJoinCode) {
    return householdDao.updateJoinCode(h, newJoinCode);
  }
}