package ntnu.org.IDATG1005.grp3.dao.interfaces;

import java.util.Collection;
import java.util.List;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.AuthenticationFailedException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.ShoppingListIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.User;

/**
 * Interface for data access operations related to {@link User} entities.
 */
public interface UserDao {

  /**
   * Attempts to create a new user with the specified username and hashed password.
   * This method is responsible for ensuring the username is unique within the database.
   * If the username already exists, a {@link UsernameAlreadyExistsException} is thrown.
   *
   * @param username The username for the new user. Must be unique.
   * @param password The hashed password for the new user.
   * @return A {@link User} object representing the newly created user, including the generated user ID.
   * @throws UsernameAlreadyExistsException If the specified username already exists in the database.
   */
  User registerUser(String username, String password) throws UsernameAlreadyExistsException;

  /**
   * Finds a user by their username and hashed password.
   * This method is primarily used for authentication purposes.
   *
   * @param username The username of the user.
   * @param password The hashed password of the user.
   * @return A {@link User} object if a user with the specified username and password exists, null otherwise.
   */
  User loginUser(String username, String password) throws AuthenticationFailedException;

  /**
   * Updates the username for a given user.
   *
   * @param userId The ID of the user to update.
   * @param newUsername The new username to set.
   * @throws UsernameAlreadyExistsException If the new username already exists.
   */
  void updateUsername(Integer userId, String newUsername) throws UsernameAlreadyExistsException;

  /**
   * Updates the password for a given user.
   *
   * @param userId The ID of the user to update.
   * @param newPassword The new hashed password to set.
   */
  void updatePassword(Integer userId, String newPassword);

  /**
   * Persists changes to a user's inventory.
   *
   * @param userId The ID of the user whose inventory is to be saved.
   * @param ingredients The inventory to be persisted.
   */
  void saveUserInventory(Integer userId, Collection<InventoryIngredient> ingredients);

  /**
   * Persists household information for a user.
   *
   * @param userId The ID of the user to associate with a household.
   * @param householdId id of the household to connect the user to.
   */
  void saveUserHousehold(Integer userId, Integer householdId);

  void saveChosenRecipes(Integer userId, List<Recipe> recipeList);

  void deleteUser(Integer userId);
}
