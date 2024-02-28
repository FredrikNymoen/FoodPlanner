package ntnu.org.IDATG1005.grp3.exception.db;

public class UserExceptions {

  private UserExceptions(){}

  public static class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String username) {
      super("Username already exists: " + username);
    }
  }

  public static class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String email) {
      super("Email already exists: " + email);
    }
  }
}