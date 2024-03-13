package ntnu.org.IDATG1005.grp3.dao.interfaces;

import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import java.util.List;

public interface RecipeDao {
  List<Recipe> findAllRecipes();
}
