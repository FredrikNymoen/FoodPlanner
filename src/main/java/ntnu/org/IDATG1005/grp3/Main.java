package ntnu.org.IDATG1005.grp3;

import static ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit.GRAM;
import static ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit.LITER;
import static ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit.STK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.EmailAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Tag;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.HouseholdService;
import ntnu.org.IDATG1005.grp3.service.UserService;

public class Main{
  public static void main(String[] args) {

    /*
    Some objects to use for example.
     */
    User user1 = new User(null, "user1", "user1@email.no", "pass");
    User user2 = new User(null, "user2", "user2@email.no", "pass");
    Household household = new Household(null, "Household1","zro4" );
    Inventory inventory = new Inventory(null, new HashMap<>());

    Ingredient tomato = new Ingredient(null, "Tomat", null);
    Ingredient apple = new Ingredient(null, "Eple", null);

    InventoryIngredient tomatoInInventory = new InventoryIngredient(null, tomato, STK, 2);
    InventoryIngredient appleInInventory = new InventoryIngredient(null, apple, STK, 4);

    /*
    Some object interactions
     */

    // add users to a household
    household.addUser(user1);
    household.addUser(user2);

    // remove user from a household
    household.removeUser(user2);

    // get user from household (index in array list, may change)
    User secondUser = household.getUsers().get(0);
    System.out.println("Remaining user in " + household.getName() + " is " + secondUser.getUsername());

    // give a user an inventory
    user1.setInventory(inventory);

    // add ingredients to an inventory
    user1.getInventory().getIngredients().put(tomato, tomatoInInventory);

    Inventory userOnesInventory = user1.getInventory();
    userOnesInventory.getIngredients().put(apple, appleInInventory);

    // modify amount of an ingredient in a user inventory
    user1.getInventory().getIngredients().get(tomato).setQuantity(3);
    userOnesInventory.getIngredients().get(apple).addQuantity(-1);

    /*
    Object logic
     */

    // print the inventory of user1
    System.out.println("Inventory of user " + user1.getUsername() + ":");
    int n = 0;
    for (InventoryIngredient ii : user1.getInventory().getIngredients().values()) {
      System.out.println(n + ": " + ii.getInventoryIngredientId());
    }

    // create a recipe (or retrieve from db using a future service class not implemented yet
    // creating a recipe is just show how we can do it manually, all fields will just be
    // retrieved by db automatically
    RecipeInfo recipeInfo = new RecipeInfo("Kokt pasta", null, 8, "Veldig vanskelig", 4.8f,
        """
        Pasta kokt i lett saltet vann. Popluært og godt.
        Må ikke forveksles med poteter.""");

    // if not clear yet, Ingredient class is a representation class, it does
    // not contain fields like amount. This way it will be easier in the future for more
    // features, like sorting, grouping and filtering (I hope)
    Ingredient pastaIngredient = new Ingredient(null, "Pasta", null);
    Ingredient waterIngredient = new Ingredient(null, "Pasta", null);

    RecipeIngredient pasta = new RecipeIngredient(pastaIngredient, GRAM, 500);
    RecipeIngredient water = new RecipeIngredient(waterIngredient, LITER, 500);

    List<RecipeIngredient> ingredientList = new ArrayList<>();
    ingredientList.add(pasta);
    ingredientList.add(water);

    // directions
    Direction direction1 = new Direction(1, "Kok alt vannet");
    Direction direction2 = new Direction(2, "Legg pasta ned i og kok i 5 sekunder");

    List<Direction> directionList = new ArrayList<>();
    directionList.add(direction1);
    directionList.add(direction2);

    // tags
    Tag tag1 = new Tag("Pasta Rett");

    List<Tag> tagList = new ArrayList<>();
    tagList.add(tag1);

    // create recipe
    Recipe pastaRecipe = new Recipe(recipeInfo, ingredientList, directionList, tagList, 0, 1);
    // in the future Recipe class will have to method adjustForAdults and adjustForChildren
    // which will adjust the quantities based on the initial

    // print a recipe \n is added for extra newlines
    System.out.println("\n\n");
    printRecipe(pastaRecipe);
    System.out.println("\n\n");

    /*
    Persistent logic

    All objects that are modified is stored in ram. Therefore, to persist changes
    we have service classes. If you are planning to persist an object be careful with the id.
    The id of the object will determine what object it persists in the database.
     */

    // creating users locally with explicit username is considered unsafe.
    User unsafeUser = new User(1, "unsafeUser", "user@user.no", "pass");
    System.out.println("Unsafe user will persist any user in db with id 1. ID: " + unsafeUser.getUserId());

    // getting users from the database is considered safe as it ensures the id is the same as in the db
    // all services classes should be used with try catch blocks, as they come with specific errors
    // should be handled accordingly.
    UserService userService = new UserService();
    User safeUser = null;

    // userService.login (to be created)
    try {
      safeUser = userService.createUser("safeUser", "user@user.no", "pass");
    } catch (UsernameAlreadyExistsException e) {
      System.out.println("You username already exists, please choose another one");
      // call a method
    } catch (EmailAlreadyExistsException e) {
      System.out.println("You email already exists, please choose another one or login");
      // call a method
    } catch (IllegalArgumentException e) {
      System.out.println("You username, email was not formatted correct or too long");
      // call a method
    }

    // find a household and add the user
    // find household
    HouseholdService householdService = new HouseholdService();
    Household householdTrust = null;
    try {
      householdTrust = householdService.findHouseholdByJoinCode("trust");
    } catch (HouseholdNotFoundException e) {
      System.out.println("Household does not exist");
      // call a method
    }

    // add a user
    householdTrust.getUsers().add(safeUser);

    // HOWEVER this user is now only added locally to persist any changes, as mentioned we need to use the service class
    try {
      householdService.updateHouseholdDetails(householdTrust);
    } catch (Exception e) {
      System.out.println("Failed");
      // call a method
    }
  }


  // example function
  public static void printRecipe(Recipe recipe) {
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
