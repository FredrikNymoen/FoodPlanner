package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.model.objects.Household;

/**
 * Provides service operations for managing households.
 */
public class HouseholdService {
  private HouseholdDao householdDao;

  public HouseholdService (HouseholdDao household) {
    householdDao = household;
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
   * Updates the details of an existing household.
   *
   * @param household The household to update with new values already set.
   * @return true if the update was successful, false otherwise.
   */
  public boolean updateHouseholdDetails(Household household) {
    return householdDao.updateHousehold(household);
  }
}