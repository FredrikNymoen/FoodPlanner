package ntnu.org.IDATG1005.grp3.dao.interfaces;

import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.model.objects.Household;

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
   * Finds a household by its join code.
   *
   * @param joinCode The join code of the household to find.
   * @return The found Household object, or null if no household matches the given join code.
   * @throws HouseholdNotFoundException if the join code is not found.
   */
  Household findHouseholdByJoinCode(String joinCode) throws HouseholdNotFoundException;

  boolean updateHouseholdName(Household household);

  boolean updateHouseholdJoinCode(Household household);

  boolean refreshUsers(Household household);
}
