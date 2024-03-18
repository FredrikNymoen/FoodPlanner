package org.IDATG1005.grp3.services;

import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    when(userDaoMock.registerUser(anyString(), eq(password))).thenReturn(expectedUser);

    User resultUser = userService.registerUser(username, password);

    //assertNotNull(resultUser, "Created user should not be null");
    assertEquals(expectedUser.getUsername(), resultUser.getUsername(), "Usernames should match");
    verify(userDaoMock, times(1)).registerUser(username, password);
  }

  @Test
  void createUser_ThrowsUsernameAlreadyExistsException() throws Exception {
    String username = "existingUser";
    String password = "password123";

    when(userDaoMock.registerUser(anyString(), anyString())).thenThrow(new UsernameAlreadyExistsException(username));

    assertThrows(UsernameAlreadyExistsException.class, () -> userService.registerUser(username, password),
        "Expected UsernameAlreadyExistsException to be thrown");
  }
}

