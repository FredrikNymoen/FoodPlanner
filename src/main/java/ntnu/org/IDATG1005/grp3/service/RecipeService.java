package ntnu.org.IDATG1005.grp3.service;

import ntnu.org.IDATG1005.grp3.dao.interfaces.RecipeDao;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import java.util.List;

/**
 * Provides service operations for managing recipes.
 */
public class RecipeService {
  private final RecipeDao recipeDao;

  /**
   * Constructs a service with the specified RecipeDao.
   *
   * @param recipeDao The DAO to be used for recipe operations.
   */
  public RecipeService(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  /**
   * Retrieves a list of all recipes in the database.
   *
   * @return A List of Recipe objects.
   */
  public List<Recipe> findAllRecipes() {
    return recipeDao.findAllRecipes();
  }
}
