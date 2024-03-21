package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.model.objects.Household;

/**
 * Provides service operations for managing households.
 */
public class HouseholdService {
  private final HouseholdDao householdDao;

  /**
   * Constructs householdService with dao.
   * @param householdDao corresponding dao.
   */
  public HouseholdService (HouseholdDao householdDao) {
    this.householdDao = householdDao;
  }

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
   * @throws HouseholdNotFoundException if the join code is not found.
   */
  public Household findHouseholdByJoinCode(String joinCode) throws HouseholdNotFoundException {
    return householdDao.findHouseholdByJoinCode(joinCode);
  }

  /**
   * Refreshes the users of a specified household, updating its user list.
   *
   * @param household The household to refresh users for.
   * @return true if the refresh was successful, false otherwise.
   */
  public boolean refreshHouseholdUsers(Household household) {
    return householdDao.refreshUsers(household);
  }

  /**
   * Updates the name of a given household.
   *
   * @param household The household to update.
   * @return true if the update was successful, false otherwise.
   */
  public boolean updateHouseholdName(Household household) {
    return householdDao.updateHouseholdName(household);
  }

  /**
   * Updates the join code of a given household.
   *
   * @param household The household to update.
   * @return true if the update was successful, false otherwise.
   */
  public boolean updateHouseholdJoinCode(Household household) {
    return householdDao.updateHouseholdJoinCode(household);
  }
}