package ntnu.org.IDATG1005.grp3;

import java.security.Provider;
import java.security.Provider.Service;
import ntnu.org.IDATG1005.grp3.db.DatabaseConnection;
import ntnu.org.IDATG1005.grp3.model.Household;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");

    Household household = new Household("Hansens","HemmeligKode");
    System.out.println(household.getName() + " " + household.getJoinCode());

  }
}