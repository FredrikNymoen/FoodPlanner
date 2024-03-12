package ntnu.org.IDATG1005.grp3.exception.db;

public class HouseholdExceptions {

  private HouseholdExceptions(){}

  public static class HouseholdNotFoundException extends Exception {
    public HouseholdNotFoundException(String joinCode) {
      super("No matching household with join code: " + joinCode);
    }
  }
}
