package ntnu.org.IDATG1005.grp3.dao.interfaces;

import ntnu.org.IDATG1005.grp3.model.Household;

public interface HouseholdDao {
  Household createHousehold(Household household);
  Household findHouseholdByJoinCode(String joinCode);
}