package org.IDATG1005.grp3.db;

import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.service.UserService;
import ntnu.org.IDATG1005.grp3.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

  private UserService userService;
  private UserDao userDaoMock;

  @BeforeEach
  void setUp() {
    userDaoMock = Mockito.mock(UserDao.class);
    userService = new UserService(userDaoMock); // Assuming UserService can accept a UserDao as an argument for testing
  }

  @Test
  void createUser_ValidInput_Success() throws Exception {
    // Given
    String username = "testUser";
    String email = "test@example.com";
    String password = "password123";
    User mockUser = new User(1, username, email, password); // Adjust according to your User class constructor
    when(userDaoMock.createUser(any(String.class), any(String.class), any(String.class))).thenReturn(mockUser);

    // When
    User createdUser = userService.createUser(username, email, password);

    // Then
    assertNotNull(createdUser);
    assertEquals(username, createdUser.getUsername());
    assertEquals(email, createdUser.getEmail());
    // Additional assertions can be added to verify the hashed password, etc.
  }

  @Test
  void createUser_UsernameAlreadyExists_ThrowsException() {
    // Given
    String username = "existingUser";
    String email = "newemail@example.com";
    String password = "password123";
    when(userDaoMock.createUser(any(String.class), any(String.class), any(String.class)))
        .thenThrow(new UsernameAlreadyExistsException());

    // When & Then
    assertThrows(UsernameAlreadyExistsException.class, () -> userService.createUser(username, email, password));
  }

  @Test
  void createUser_InvalidEmail_ThrowsIllegalArgumentException() {
    // Given
    String invalidEmail = "invalid";

    // When & Then
    assertThrows(IllegalArgumentException.class, () -> userService.createUser("newUser", invalidEmail, "password123"));
  }

  // Additional tests can be added to cover update scenarios, email already exists exception,
  // and other invalid inputs (e.g., empty strings or null values).

}

