package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import ntnu.org.IDATG1005.grp3.interfaces.ShoppingListRecipeRemovalListener;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

public class ShoppingListChosenRecipeController {

  @FXML
  private Label recipeName;

  @FXML
  private Label removeButton;

  private Recipe recipe;
  private ShoppingListRecipeRemovalListener removalListener;

  public void setData(Recipe recipe) {
    this.recipe = recipe;
    recipeName.setText(recipe.getRecipeInfo().getTitle());
  }


  @FXML
  void removeRecipe(MouseEvent event) {
    appUser.getChosenRecipes().remove(recipe);
    appUser.getShoppingCartRecipes().remove(recipe);
    removalListener.onRecipeRemoved(recipe);
  }

  public void setShoppingListRecipeRemovalListener(ShoppingListRecipeRemovalListener listener) {
    this.removalListener = listener;
  }

}
