package ntnu.org.IDATG1005.grp3.model.objects;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user with a unique ID, username, email address, and password.
 */
public class User {
  private final Integer userId;
  private String username;
  private Inventory inventory;
  private String password; // hashed at service layer, so stored in plain at ram
  private Household household;
  private List<ShoppingListIngredient> shoppingList;
  private List<Recipe> chosenRecipes;

  /**
   * Constructs a new User with the specified ID, username, email, and password.
   *
   * @param userId The unique identifier for the user.
   * @param username The username of the user.
   * @param password The password for the user account.
   */
  public User(Integer userId, String username, String password) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.shoppingList = new ArrayList<>();
    this.chosenRecipes = new ArrayList<>();
  }

  /**
   * Gets the user's ID.
   *
   * @return The unique identifier of the user.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Gets the user's username.
   *
   * @return The username of the user.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the user's password.
   * Caution: Returning passwords in plain text is a security risk.
   *
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the user's username.
   *
   * @param username The new username for the user.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Sets the user's password.
   * Again not the best security practice.
   *
   * @param password The new password for the user.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Return the household the user is part of.
   *
   * @return household that the user is part of, null if not part of any.
   */
  public Household getHousehold() {
    return household;
  }

  public void setHousehold(Household household) {
    this.household = household;
  }

  public boolean isAssociatedWithHousehold () {
    return household != null;
  }

  /**
   * Removes the specified ingredient from the inventory.
   * If the ingredient does not exist in the inventory, this method performs no action.
   *
   * @param ingredient The ingredient to be removed from the inventory.
   */
  public void removeIngredient(Ingredient ingredient) {
    inventory.getIngredients().remove(ingredient);
  }

  /**
   * Can both increase and decrease the value based on +-
   *
   * @param ingredient
   * @param changeValue
   */
  public void changeQuantityOfIngredient(Ingredient ingredient, double changeValue) {
    InventoryIngredient inventoryIngredient = inventory.getIngredients().get(ingredient);
    if (inventoryIngredient != null) {
      double newQuantity = inventoryIngredient.getQuantity() + changeValue;
      inventoryIngredient.setQuantity(newQuantity);
      // removes the ingredient if the new quantity is less than or equal to 0
      if (newQuantity <= 0) {
        removeIngredient(ingredient);
      }
    } else {
      // adds the ingredient with changeValue as its initial quantity if changeValue is positive.
      if (changeValue > 0) {
        inventory.getIngredients().put(ingredient, new InventoryIngredient(ingredient, changeValue));
      } else {
        // handle case for negative or zero changeValue for non-existing ingredient
        System.out.println("Cannot add ingredient with non-positive quantity.");
      }
    }
  }

  public void addShoppingListIngredient(ShoppingListIngredient ingredient) {
    shoppingList.add(ingredient);
  }

  public void removeShoppingListIngredient(ShoppingListIngredient ingredient) {
    shoppingList.remove(ingredient);
  }

  public List<ShoppingListIngredient> getShoppingList() {
    return shoppingList;
  }

  public void addChosenRecipe(Recipe recipe) {
    chosenRecipes.add(recipe);
  }

  public void removeChosenRecipe(Recipe recipe) {
    chosenRecipes.remove(recipe);
  }

  public List<Recipe> getChosenRecipes() {
    return chosenRecipes;
  }
}