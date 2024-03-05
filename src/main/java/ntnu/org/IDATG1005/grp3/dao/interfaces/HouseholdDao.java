package ntnu.org.IDATG1005.grp3.dao.interfaces;

import ntnu.org.IDATG1005.grp3.model.Household;

public interface HouseholdDao {
  Household createHousehold();
  boolean updateName(int householdId, String newName);
  boolean updateJoinCode(int householdId, String newJoinCode);
  Household findHouseholdByJoinCode(String joinCode);
}