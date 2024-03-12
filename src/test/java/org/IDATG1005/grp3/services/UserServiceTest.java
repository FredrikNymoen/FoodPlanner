package org.IDATG1005.grp3.services;

import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

  private UserService userService;
  private UserDao userDaoMock;

  @BeforeEach
  void setUp() {
    userDaoMock = mock(UserDao.class);
    userService = new UserService(userDaoMock);
  }

  @Test
  void createUser_Success() throws Exception {
    String username = "testUser";
    String password = "password123";
    User expectedUser = new User(1, username, password);

    when(userDaoMock.createUser(anyString(), eq(password))).thenReturn(expectedUser);

    User resultUser = userService.createUser(username, password);

    //assertNotNull(resultUser, "Created user should not be null");
    assertEquals(expectedUser.getUsername(), resultUser.getUsername(), "Usernames should match");
    verify(userDaoMock, times(1)).createUser(username, password);
  }

  @Test
  void createUser_ThrowsUsernameAlreadyExistsException() throws Exception {
    String username = "existingUser";
    String password = "password123";

    when(userDaoMock.createUser(anyString(), anyString())).thenThrow(new UsernameAlreadyExistsException(username));

    assertThrows(UsernameAlreadyExistsException.class, () -> userService.createUser(username, password),
        "Expected UsernameAlreadyExistsException to be thrown");
  }

  @Test
  void updateUserDetails_Success() throws Exception {
    User userToUpdate = new User(1, "updatedUser", "newPassword123");
    when(userDaoMock.updateUser(any(User.class))).thenReturn(true);

    boolean result = userService.updateUserDetails(userToUpdate);

    assertTrue(result, "Update should be successful");
    verify(userDaoMock, times(1)).updateUser(userToUpdate);
  }
}

