package ntnu.org.IDATG1005.grp3;
import java.util.List;
import ntnu.org.IDATG1005.grp3.dao.implementations.HouseholdDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.IngredientDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.RecipeDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.HouseholdDao;
import ntnu.org.IDATG1005.grp3.dao.interfaces.IngredientDao;
import ntnu.org.IDATG1005.grp3.dao.interfaces.RecipeDao;
import ntnu.org.IDATG1005.grp3.dao.interfaces.UserDao;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
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

    createUserSim();

    updateUserSim();

    createHouseholdSim();

    findHouseholdById();

    retrieveAndAvailablePrintIngredients();

    retrieveAndPrintRecipes();

    /*
    Persistent logic:

    All objects that are modified is stored in ram. Therefore, to persist changes
    we have service classes. If you are planning to persist an object be careful with the id.
    The id of the object will determine what object it persists in the database. To solve for this
    only persist objects created or retrieved by service classes in the first place.
     */
  }

  public static void retrieveAndAvailablePrintIngredients() {
    // initialize DAO and initialize the service with the DAO
    IngredientDao ingredientDao = new IngredientDaoImpl();
    IngredientService ingredientService = new IngredientService(ingredientDao);

    // fetch and display all ingredients
    System.out.println("All Ingredients:");
    for (Ingredient ingredient : ingredientService.findAllIngredients()) {
      System.out.println(ingredient.getIngredientId() + ": " + ingredient.getName() + " - " + ingredient.getImageUrl());
    }

    // look for an ingredient with ID 1
    Integer ingredientIdToFind = 1;
    Ingredient foundIngredient = ingredientService.findIngredientById(ingredientIdToFind);
    if (foundIngredient != null) {
      System.out.println("\nIngredient Found:");
      System.out.println(foundIngredient.getIngredientId() + ": " + foundIngredient.getName() + " - " + foundIngredient.getImageUrl());
    } else {
      System.out.println("\nIngredient with ID " + ingredientIdToFind + " not found.");
    }
  }

  public static void retrieveAndPrintRecipes() {
    // initialize DAO and initialize the service with the DAO
    RecipeDao recipeDao = new RecipeDaoImpl();
    RecipeService recipeService = new RecipeService(recipeDao);

    // fetch and display all recipes by title
    System.out.println("All recipes:");

    // TODO try catch
    List<Recipe> recipes = recipeService.findAllRecipes();
    for (int i = 0; i < recipes.size(); i++) {
      System.out.println((i + 1) + ": " + recipes.get(i).getRecipeInfo().getTitle());
    }

    // print the recipe in id 1
    System.out.println("\n\n");
    printRecipe(recipes.get(0));
  }

  private static void createUserSim() {
    // simulate how to create a user without database
    User user = new User(null, "usernameNull", "passNull");
    System.out.println("\nLocal user with null id: ");
    System.out.println("Username: " + user.getUsername());

    // simulate how to create a user with database.
    UserDao userDao = new UserDaoImpl();
    UserService userService = new UserService(userDao);

    User userPersistent;

    try {
      userPersistent = userService.createUser("usernamePersistent", "passPersistent");
    } catch (UsernameAlreadyExistsException e) {
      System.out.println("Username already exists.");
    } catch (IllegalArgumentException e) {
      System.out.println("Username is too long.");
    }
  }

  private static void updateUserSim() {

    UserDao userDao = new UserDaoImpl();
    UserService userService = new UserService(userDao);

    // updates are based on the id, in practice you do not know the id
    User user = new User(1, "newUsername", "newPass");
    try {
      userService.updateUserDetails(user);
      System.out.println("User updated!");
    } catch (UsernameAlreadyExistsException e) {
      System.out.println("Failed to update user: Username already exists.");
    } catch (Exception e) {
      System.out.println("An unexpected error occurred: " + e.getMessage());
    }
  }

  private static void createHouseholdSim() {
    // simulate how to create a household without database
    Household household = new Household(null, "householdNull", "joinCodeNull");
    System.out.println("\nLocal household with null id: ");
    System.out.println("Username: " + household.getName());

    // simulate how to create a household with database.
    HouseholdDao householdDao = new HouseholdDaoImpl();
    HouseholdService householdService = new HouseholdService(householdDao);

    Household householdPersistent = null;

    try {
      householdPersistent = householdService.createHousehold();
    } catch (Exception e) {
      System.out.println("Something failed");
    }

    System.out.println("Household:");
    System.out.println("Id: " + householdPersistent.getHouseholdId());
    System.out.println("Name: " + householdPersistent.getName());
    System.out.println("JoinCode: " + householdPersistent.getJoinCode());
  }

  private static void findHouseholdById() {

    // start the service
    HouseholdDao householdDao = new HouseholdDaoImpl();
    HouseholdService householdService = new HouseholdService(householdDao);
    Household household = null;

    try {
      household = householdService.findHouseholdByJoinCode("trust");
    } catch (HouseholdNotFoundException e) {
      System.out.println("Household not found");
    }

    System.out.println("Household:");
    System.out.println("Id: " + household.getHouseholdId());
    System.out.println("Name " + household.getName());
    System.out.println("JoinCode" + household.getJoinCode());
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


