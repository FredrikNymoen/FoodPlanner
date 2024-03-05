
package ntnu.org.IDATG1005.grp3;

import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.EmailAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.User;
import ntnu.org.IDATG1005.grp3.service.UserService;


public class Main{
  public static void main(String[] args) {

    createUserSim();
  }

  // some reference code
  private static void createUserSim() {
    // simulate how to create a user without database
    User user = new User(null, "usernameNull", "emailNull", "passNull");
    System.out.println("\nLocal user with null id: ");
    System.out.println("Username: " + user.getUsername());

    // simulate how to create a user with database.
    UserService userService = new UserService();
    // as always a objectService class is used.

    System.out.println("\nMaking the db create the user for us:");
    try {
      User newUser = userService.createUser("kjosern", "kjos@kjos.no", "ok");
      System.out.println("User created successfully: " + newUser.getUsername());
    } catch (UsernameAlreadyExistsException e) {
      System.out.println("Failed to create user: Username already exists.");
    } catch (EmailAlreadyExistsException e) {
      System.out.println("Failed to create user: Email already exists.");
    } catch (Exception e) {
      System.out.println("An unexpected error occurred: " + e.getMessage());
    }

    // creating a user locally, is still fine! The main difference is how the userId is set.
    // When we create a user locally, this user will interact with the user in the database with the same id.
    // When we create a user using userService the database automatically assigns the id.
    System.out.println("\nUpdating user: ");
    User localUser = new User(1, "kjosern", "email", "pass");

    // This code will therefore update the user in the database with the id = 1
    // userId is a constant, so it cannot be changed after initialisation.
    try {
      userService.updateUserDetails(localUser);
      System.out.println("User updated!");
    } catch (UsernameAlreadyExistsException e) {
      System.out.println("Failed to update user: Username already exists.");
    } catch (EmailAlreadyExistsException e) {
      System.out.println("Failed to update user: Email already exists.");
    } catch (Exception e) {
      System.out.println("An unexpected error occurred: " + e.getMessage());
    }

  }
}

