package ntnu.org.IDATG1005.grp3.dao.interfaces;

import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.EmailAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.User;

/**
 * Interface for data access operations related to {@link User} entities.
 */
public interface UserDao {

  /**
   * Attempts to create a new user with the specified username, email, and password.
   *
   * @param username The username for the new user. Must be unique.
   * @param email The email address for the new user. Must be unique.
   * @param password The password for the new user.
   * @return A {@link User} or null.
   * @throws UsernameAlreadyExistsException If the specified username already exists in the database.
   * @throws EmailAlreadyExistsException If the specified email already exists in the database.
   */
  User createUser(String username, String email, String password) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

  /**
   * Updates an existing user's information in the database.
   *
   * @param user The user to update, with updated fields.
   * @return true if the update was successful, false otherwise.
   * @throws UsernameAlreadyExistsException If the specified username already exists in the database.
   * @throws EmailAlreadyExistsException If the specified email already exists in the database.
   */
  boolean updateUser(User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;
}
