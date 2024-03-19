package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.application.MainApp;

public class menyBarController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    System.out.println("MenyBarController initialized");
  }


  @FXML
  private void recipes() {
    System.out.println("Recipes");
    try {
      root = FXMLLoader.load(getClass().getResource("/fxml/views/recipeScreenPage.fxml"));
      stage = MainApp.primaryStage;
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void ingredients() {
    System.out.println("ingredients");
    try {
      root = FXMLLoader.load(getClass().getResource("/fxml/views/ingredience.fxml"));
      stage = MainApp.primaryStage;
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void shoppingList() {
    System.out.println("shoppingList");
    try {
      root = FXMLLoader.load(getClass().getResource("/fxml/views/shoppingList.fxml"));
      stage = MainApp.primaryStage;
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void activeRecipes() {
    System.out.println("activeRecipes");
    try {
      root = FXMLLoader.load(getClass().getResource("/fxml/views/activeRecipes.fxml"));
      stage = MainApp.primaryStage;
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
