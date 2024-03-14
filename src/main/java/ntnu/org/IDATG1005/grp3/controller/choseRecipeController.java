package ntnu.org.IDATG1005.grp3.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class choseRecipeController {
  private static choseRecipeController instance;
  @FXML
  private Label starLabel;
  @FXML
  private AnchorPane choseRecipeBorder;

  private List<AnchorPane> favoriteRecipes;
  public static synchronized choseRecipeController getInstance() {
    if (instance == null) {
      instance = new choseRecipeController();
    }
    return instance;
  }
  public void choseDisplayedRecipe(){
    System.out.println("Recipe chosen");
  }
  public void addRecipeToFavorite(){
    choseRecipeBorder.setStyle(choseRecipeBorder.getStyle() + "-fx-border-color: yellow;");
    starLabel.setTextFill(Color.YELLOW);
    }

  }
