package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.interfaces.IngredientDao;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import java.util.List;

/**
 * Provides service operations for managing ingredients.
 * This includes finding an ingredient by ID and retrieving all ingredients.
 */
public class IngredientService {
  private final IngredientDao ingredientDao;

  /**
   * Constructs a service with the specified IngredientDao.
   *
   * @param ingredientDao The DAO to be used for ingredient operations.
   */
  public IngredientService(IngredientDao ingredientDao) {
    this.ingredientDao = ingredientDao;
  }

  /**
   * Finds an ingredient by its unique identifier.
   *
   * @param id The unique identifier of the ingredient to be found.
   * @return The Ingredient object if found, or null if not found.
   */
  public Ingredient findIngredientById(Integer id) {
    return ingredientDao.findIngredientById(id);
  }

  /**
   * Retrieves a list of all ingredients in the database.
   *
   * @return A List of Ingredient objects.
   */
  public List<Ingredient> findAllIngredients() {
    return ingredientDao.findAllIngredients();
  }
}

