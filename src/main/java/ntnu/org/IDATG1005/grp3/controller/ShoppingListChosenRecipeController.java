package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

public class ShoppingListChosenRecipeController {

  @FXML
  private Label recipeName;

  @FXML
  private Label removeButton;

  public void setData(Recipe recipe) {
    recipeName.setText(recipe.getRecipeInfo().getTitle());
  }


  @FXML
  void removeRecipe(MouseEvent event) {

  }

}
