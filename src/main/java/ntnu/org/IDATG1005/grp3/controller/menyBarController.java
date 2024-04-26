package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.application.MainApp;

public class menyBarController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }


  @FXML
  private void recipes() {
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

  @FXML
  private void yourCollective() {
    try {
      root = FXMLLoader.load(getClass().getResource("/fxml/views/yourCollectivePage.fxml"));
      stage = MainApp.primaryStage;
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void yourProfile() {
    try {
      root = FXMLLoader.load(getClass().getResource("/fxml/views/yourProfilePage.fxml"));
      stage = MainApp.primaryStage;
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
