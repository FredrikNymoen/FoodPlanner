package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.implementations.HouseholdDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.model.Household;

public class HouseholdService {
  private HouseholdDao householdDao = new HouseholdDaoImpl();

  public Household createHousehold(String name, String joinCode) {
    Household household = new Household(name, joinCode);
    return householdDao.createHousehold(household);
  }

  public Household findHouseholdByJoinCode(String joinCode) {
    return householdDao.findHouseholdByJoinCode(joinCode);
  }
}