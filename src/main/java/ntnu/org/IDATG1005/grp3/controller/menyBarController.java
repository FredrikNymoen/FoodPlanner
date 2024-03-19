package ntnu.org.IDATG1005.grp3.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class menyBarController implements Initializable {


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    System.out.println("MenyBarController initialized");
  }


  @FXML
  private void recipes() {
    System.out.println("Recipes");
    //load the screen for recipes

  }

  @FXML
  private void ingredients() {
    System.out.println("ingredients");
  }

  @FXML
  private void shoppingList() {
    System.out.println("shoppingList");
  }

  @FXML
  private void activeRecipes() {
    System.out.println("activeRecipes");
  }
}
