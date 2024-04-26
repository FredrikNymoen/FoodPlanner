package ntnu.org.IDATG1005.grp3.exception.db;

public class UserExceptions {

  private UserExceptions() {}

  public static class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String username) {
      super("Username already exists: " + username);
    }
  }

  public static class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException() {
      super("Authentication failed: Incorrect username or password.");
    }
  }

  public static class FailedToLoadInventoryException extends Exception {
    public FailedToLoadInventoryException() {
      super("Failed to load user inventory.");
    }
  }

  public static class FailedToLoadHouseholdException extends Exception {
    public FailedToLoadHouseholdException() {
      super("Failed to load user household.");
    }
  }

  public static class InvalidUserOperationException extends Exception {
    public InvalidUserOperationException(String message) {
      super(message);
    }
  }
}
