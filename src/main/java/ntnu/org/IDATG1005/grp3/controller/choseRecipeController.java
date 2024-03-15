package ntnu.org.IDATG1005.grp3.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

public class choseRecipeController {
  private static choseRecipeController instance;
  @FXML
  private Label starLabel;
  @FXML
  private AnchorPane choseRecipeBorder;
  private Boolean isFavorite = false;

  public static synchronized choseRecipeController getInstance() {
    if (instance == null) {
      instance = new choseRecipeController();
    }
    return instance;
  }

  public void choseDisplayedRecipe() {
    System.out.println("Recipe chosen");
  }
  public void toggleFavorite(){
    if (!isFavorite()) {
      addRecipeToFavorite();
    } else {
      removeRecipeFromFavorite();
    }
  }

  public void addRecipeToFavorite() {
    choseRecipeBorder.setStyle("-fx-border-color: yellow;");
    starLabel.setTextFill(Color.YELLOW);
    isFavorite = true;
  }
  public void removeRecipeFromFavorite() {
    choseRecipeBorder.setStyle("-fx-border-color: black;");
    starLabel.setTextFill(Color.BLACK);
    isFavorite = false;
  }

  public boolean isFavorite() {
    return isFavorite;
  }
}
