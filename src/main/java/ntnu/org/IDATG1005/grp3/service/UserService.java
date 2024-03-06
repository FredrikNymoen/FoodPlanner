package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.EmailAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.User;

/**
 * Provides service operations for managing users.
 */
public class UserService {
  private final UserDao userDao = new UserDaoImpl();

  /**
   * Creates a new user with the specified username, email, and password.
   *
   * @param username the username of the new user
   * @param email the email of the new user
   * @param password the password for the new user account
   * @return the newly created {@link User} or null if fails
   * @throws UsernameAlreadyExistsException if the provided username already exists in the database
   * @throws EmailAlreadyExistsException if the provided email already exists in the database
   */
  public User createUser(String username, String email, String password) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
    // hash password
    // email validation etc
    return userDao.createUser(username, email, password);
  }

  /**
   * Updates an existing user's details in the database.
   *
   * @param user The user to update, containing the updated information.
   * @return true if the update was successful, false otherwise.
   * @throws UsernameAlreadyExistsException if the provided username already exists in the database
   * @throws EmailAlreadyExistsException if the provided email already exists in the database
   */
  public boolean updateUserDetails(User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
    return userDao.updateUser(user);
  }
}