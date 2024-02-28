package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.implementations.HouseholdDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.model.Household;

public class HouseholdService {
  private final HouseholdDao householdDao = new HouseholdDaoImpl();

  public Household createHousehold() {
    return householdDao.createHousehold();
  }

  public Household findHouseholdByJoinCode(String joinCode) {
    return householdDao.findHouseholdByJoinCode(joinCode);
  }

  public boolean updateHouseholdName(int householdId, String newName) {
    return householdDao.updateName(householdId, newName);
  }

  public boolean updateHouseholdJoinCode(int householdId, String newJoinCode) {
    return householdDao.updateJoinCode(householdId, newJoinCode);
  }
}