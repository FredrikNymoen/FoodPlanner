package ntnu.org.IDATG1005.grp3;

import ntnu.org.IDATG1005.grp3.model.Household;
import ntnu.org.IDATG1005.grp3.service.HouseholdService;

public class Main {

  public static void main(String[] args) {
    Household household = new Household("Amund", "A");
    HouseholdService householdService = new HouseholdService();
    householdService.createHousehold(household.getName(), household.getJoinCode());
    householdService.findHouseholdByJoinCode("A");
  }
}