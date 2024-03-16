package ntnu.org.IDATG1005.grp3;
import java.util.List;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.dao.implementations.HouseholdDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.IngredientDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.RecipeDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.dao.interfaces.IngredientDao;
import ntnu.org.IDATG1005.grp3.dao.interfaces.RecipeDao;
import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.AuthenticationFailedException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.FailedToLoadHouseholdException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.FailedToLoadInventoryException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Tag;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.HouseholdService;
import ntnu.org.IDATG1005.grp3.service.IngredientService;
import ntnu.org.IDATG1005.grp3.service.RecipeService;
import ntnu.org.IDATG1005.grp3.service.UserService;

public class MainCheeky {

  public static void main(String[] args) {
    testUserOperations();
    testHouseholdOperations();
    testIngredientOperations();
    testRecipeOperations();
  }

  private static void testUserOperations() {
    System.out.println("=== User Operations Test ===");
    UserDao userDao = new UserDaoImpl();
    UserService userService = new UserService(userDao);

    // register a new user
    User newUser = null;
    try {
      newUser = userService.registerUser("testUser11", "testPass");
      System.out.println("User registered: " + newUser.getUsername());
    } catch (IllegalArgumentException e) {
      System.out.println("Username or password might have been to long...");
    } catch (UsernameAlreadyExistsException e) {
      System.out.println("Username already exists");
    }

    // login to an existing user
    User persitentUser = null;
    try {
      persitentUser = userService.authenticateUser("testUserNew1", "newTestPass");
      System.out.println("Logged into: " + persitentUser.getUsername());
    } catch (AuthenticationFailedException e) {
      System.out.println("Username or password is simply wrong");
    }

    // save user inventory (example)
    Ingredient selectedIngredient = MainApp.appIngredients.get(0);

    // remove ingredient if exists
    persitentUser.removeIngredient(selectedIngredient);

    // add five of it
    persitentUser.changeQuantityOfIngredient(selectedIngredient, 5);

    // decrease by one, now 4 in total
    persitentUser.changeQuantityOfIngredient(selectedIngredient, -1);

    // persist the changes to db
    try {
      userService.saveUserInventory(persitentUser);
      System.out.println("User inventory saved.");
    } catch (FailedToLoadInventoryException e) {
      System.out.println("Inventory saving failed because failed to load inventory");
    }


    // save user household (example)

    // create a houseHold with db
    HouseholdService hs = new HouseholdService(new HouseholdDaoImpl());
    Household household = hs.createHousehold();

    System.out.println("Created household: " + household.getName());
    System.out.println("With the join code: " + household.getJoinCode());

    // set the household offline
    persitentUser.setHousehold(household);

    // persist the changes to db
    try {
      userService.saveUserHousehold(persitentUser);
      System.out.println("User household saved.");
    } catch (Exception e) {
      System.out.println("User household saving failed");
    }

    // change user username
    try {
      userService.changeUserUsername(persitentUser, "testUserNew1");
      System.out.println("User username updated: " + persitentUser.getUsername());
    } catch (UsernameAlreadyExistsException e) {
      System.out.println("Username change failed due to already existing in db");
    } catch (IllegalArgumentException e) {
      System.out.println("Username is too long or illegal");
    }


    // change user password
    userService.changeUserPassword(persitentUser, "newTestPass");
    System.out.println("User password updated.");
  }



  private static void testHouseholdOperations() {
    System.out.println("=== Household Operations Test ===");
    HouseholdDao householdDao = new HouseholdDaoImpl();
    HouseholdService householdService = new HouseholdService(householdDao);

    try {
      Household newHousehold = householdService.createHousehold();
      System.out.println("Household created: " + newHousehold.getName());
      Household foundHousehold = householdService.findHouseholdByJoinCode(newHousehold.getJoinCode());
      System.out.println("Household found: " + foundHousehold.getName());
    } catch (HouseholdNotFoundException e) {
      System.out.println("Household not found");
    }
  }

  private static void testIngredientOperations() {
    System.out.println("=== Ingredient Operations Test ===");
    IngredientDao ingredientDao = new IngredientDaoImpl();
    IngredientService ingredientService = new IngredientService(ingredientDao);

    List<Ingredient> ingredients = ingredientService.findAllIngredients();
    System.out.println("All ingredients:");
    ingredients.forEach(ingredient -> System.out.println("- " + ingredient.getName()));

    Ingredient newIngredient = ingredientService.findIngredientById(1); // is assuming an ingredient with ID 1 exists
    System.out.println("Ingredient found: " + newIngredient.getName());
  }

  private static void testRecipeOperations() {
    System.out.println("=== Recipe Operations Test ===");
    RecipeDao recipeDao = new RecipeDaoImpl();
    RecipeService recipeService = new RecipeService(recipeDao);

    List<Recipe> recipes = recipeService.findAllRecipes();
    System.out.println("All recipes:");
    recipes.forEach(recipe -> System.out.println("- " + recipe.getRecipeInfo().getTitle()));

    // assuming a recipe with ID 1 exists
    Recipe recipe = recipes.get(1);
    printRecipe(recipe);
  }

  // example function for printing a recipe
  private static void printRecipe(Recipe recipe) {
    System.out.println("Recipe: " + recipe.getRecipeInfo().getTitle());
    System.out.println("Description: " + recipe.getRecipeInfo().getDescription());
    System.out.println("Cook Time: " + recipe.getRecipeInfo().getCookTime() + " minutes");
    System.out.println("Difficulty: " + recipe.getRecipeInfo().getDifficulty());
    System.out.println("Rating: " + recipe.getRecipeInfo().getRating() + " stars");
    System.out.println("Serves Adults: " + recipe.getPersonsAdults());
    System.out.println("Serves Children: " + recipe.getPersonsChildren());
    System.out.println("\nIngredients:");
    for (RecipeIngredient ingredient : recipe.getIngredients()) {
      System.out.println("- " + ingredient.getIngredient().getName() + ": " + ingredient.getAmount() + " " + ingredient.getUnit().getUnitName());
    }
    System.out.println("\nDirections:");
    for (Direction direction : recipe.getDirections()) {
      System.out.println(direction.getDirectionStep() + ". " + direction.getDirection());
    }
    System.out.println("\nTags:");
    for (Tag tag : recipe.getTags()) {
      System.out.println("- " + tag.getTitle());
    }
  }
}
