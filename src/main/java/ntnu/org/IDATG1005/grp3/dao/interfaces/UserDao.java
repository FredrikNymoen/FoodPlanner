package ntnu.org.IDATG1005.grp3.dao.interfaces;

import ntnu.org.IDATG1005.grp3.model.User;

public interface UserDao {
  User createUser(String username, String email, String password);
  boolean updateUser(User user);
}
