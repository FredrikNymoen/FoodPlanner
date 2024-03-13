package ntnu.org.IDATG1005.grp3.dao.interfaces;

import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import java.util.List;

public interface IngredientDao {
  Ingredient findIngredientById(Integer id);
  List<Ingredient> findAllIngredients();
}

