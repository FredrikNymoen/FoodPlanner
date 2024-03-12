package ntnu.org.IDATG1005.grp3;

import ntnu.org.IDATG1005.grp3.dao.implementations.IngredientDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.IngredientDao;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.service.IngredientService;

public class MainCheeky {

  public static void main(String[] args) {

    retrieveAndPrintIngredients();

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
}
