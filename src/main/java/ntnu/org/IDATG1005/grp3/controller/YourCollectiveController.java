package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.interfaces.LoginDisplayListener;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.User;


public class YourCollectiveController implements Initializable, LoginDisplayListener {

  @FXML
  private HBox profileContainer;

  @FXML
  private Label collectiveCode;

  @FXML
  private AnchorPane rootPane;

  @FXML
  void addUserToCollective(MouseEvent event) {
    System.out.println("addUserToCollective");
  }

  /*@FXML
  void leaveCollective(MouseEvent event) {
    System.out.println("leaveCollective");
  }*/

  public void getData(){
    appUser = new User(1, "test", "test");

    Household household = new Household(1, "test", "Oisann");
    household.addUser(appUser);
    household.addUser(new User(2, "test2", "test2"));


    appUser.setHousehold(household);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    System.out.println("YourCollectiveController initialized");
    getData();

    displayUsers();
  }

  private void displayUsers() {
    profileContainer.getChildren().clear();

    for (User user : appUser.getHousehold().getUsers()) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
            getClass().getResource("/fxml/components/profilePicture.fxml"));
        AnchorPane anchorPane = loader.load();
        ProfilePictureController profilePictureController = loader.getController();
        profilePictureController.setData(user);
        profilePictureController.setLoginDisplayListener(this);


        profileContainer.getChildren().add(anchorPane);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void onLoginDisplay(User user) {
    System.out.println("HALLAHALLA");
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/fxml/components/loginToYourProfilePage.fxml"));
      AnchorPane anchorPane = loader.load();
      LoginToYourProfilePageController loginProfileController = loader.getController();
      loginProfileController.setData(user);

      rootPane.getChildren().add(anchorPane);

      anchorPane.setLayoutX((rootPane.getWidth() - anchorPane.getPrefWidth()) / 2);
      anchorPane.setLayoutY((rootPane.getHeight() - anchorPane.getPrefHeight()) / 2);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void leaveCollective(ActionEvent event) {
    System.out.println("leaveCollective");
    appUser.getHousehold().removeUser(appUser);
    appUser.setHousehold(null);
    System.out.println("halla");

    try{
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/startScreen.fxml"));
      Parent root = loader.load();
      // Create a new scene with the loaded content
      Scene scene = new Scene(root);
      // Get the current stage and set the new scene
      Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

}
