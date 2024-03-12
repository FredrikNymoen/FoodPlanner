package ntnu.org.IDATG1005.grp3;

import java.util.List;
import ntnu.org.IDATG1005.grp3.dao.implementations.IngredientDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.RecipeDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.IngredientDao;
import ntnu.org.IDATG1005.grp3.dao.interfaces.RecipeDao;
import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Tag;
import ntnu.org.IDATG1005.grp3.service.IngredientService;
import ntnu.org.IDATG1005.grp3.service.RecipeService;

public class MainCheeky {

  public static void main(String[] args) {

    //retrieveAndPrintIngredients();

    retrieveAndPrintRecipes();
  }

  public static void retrieveAndPrintIngredients() {
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

  // example function for printing a recipe
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
