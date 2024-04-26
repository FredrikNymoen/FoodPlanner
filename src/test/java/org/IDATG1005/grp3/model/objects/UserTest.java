package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Tag;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class UserTest {
  private User user;
  private Ingredient ingredient;
  private Recipe recipe;

  @BeforeEach
  void setUp() {
    user = new User(1, "testUser", "password");
    ingredient = new Ingredient(1, "Tomato", "imageURL", MeasurementUnit.STK);
    HashMap<Ingredient, InventoryIngredient> ingredients = new HashMap<>();
    ingredients.put(ingredient, new InventoryIngredient(ingredient, 1.0));
    user.setInventory(new Inventory(ingredients));
    List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    List<Direction> directions = new ArrayList<>();
    List<Tag> tags = new ArrayList<>();
    RecipeInfo recipeInfo = new RecipeInfo("Pasta", "imageURL", 30, "Easy", 4.5f, "Delicious Pasta");
    recipe = new Recipe(recipeInfo, recipeIngredients, directions, tags, 2, 0, 1);
  }

  @Test
  void getUsername() {
    // testing getUsername
    assertEquals("testUser", user.getUsername());
  }

  @Test
  void getPassword() {
    // testing getPassword
    assertEquals("password", user.getPassword());
  }

  @Test
  void setUsername() {
    // testing setUsername
    user.setUsername("newUsername");
    assertEquals("newUsername", user.getUsername());
  }

  @Test
  void setPassword() {
    // testing setPassword
    user.setPassword("newPassword");
    assertEquals("newPassword", user.getPassword());
  }

  @Test
  void getInventory() {
    // testing getInventory
    assertNotNull(user.getInventory());
  }

  @Test
  void setInventory() {
    // testing setInventory
    Inventory newInventory = new Inventory(new HashMap<>());
    user.setInventory(newInventory);
    assertEquals(newInventory, user.getInventory());
  }

  @Test
  void getHousehold() {
    // testing getHousehold before setting
    assertNull(user.getHousehold());

    // after setting
    Household household = new Household(1, "HouseholdName", "JoinCode");
    user.setHousehold(household);
    assertNotNull(user.getHousehold());
    assertEquals(household, user.getHousehold());
  }

  @Test
  void isAssociatedWithHousehold() {
    // testing isAssociatedWithHousehold
    assertFalse(user.isAssociatedWithHousehold());

    Household household = new Household(1, "HouseholdName", "JoinCode");
    user.setHousehold(household);
    assertTrue(user.isAssociatedWithHousehold());
  }

  @Test
  void removeIngredient() {
    // testing removeIngredient
    user.removeIngredient(ingredient);
    assertFalse(user.getInventory().getIngredients().containsKey(ingredient));
  }

  @Test
  void changeQuantityOfIngredient() {
    // testing changeQuantityOfIngredient
    user.changeQuantityOfIngredient(ingredient, -0.5);
    assertEquals(0.5, user.getInventory().getIngredients().get(ingredient).getQuantity());
  }

  @Test
  void getSetChosenRecipes() {
    // testing getChosenRecipes and setChosenRecipes
    List<Recipe> recipes = new ArrayList<>();
    recipes.add(recipe);
    user.setChosenRecipes(recipes);
    assertNotNull(user.getChosenRecipes());
    assertEquals(recipes, user.getChosenRecipes());
  }
}
