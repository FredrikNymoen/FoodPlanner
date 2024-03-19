package ntnu.org.IDATG1005.grp3.interfaces;

import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

public interface RecipeChangedListener {
  void onRecipeChanged(Recipe recipe);

}
