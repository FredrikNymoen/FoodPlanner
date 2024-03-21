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
import ntnu.org.IDATG1005.grp3.dao.implementations.HouseholdDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.interfaces.LoginDisplayListener;
import ntnu.org.IDATG1005.grp3.interfaces.NewProfileListener;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.HouseholdService;
import ntnu.org.IDATG1005.grp3.service.UserService;


public class YourCollectiveController implements Initializable, LoginDisplayListener,
    NewProfileListener {

  @FXML
  private HBox profileContainer;

  @FXML
  private Label collectiveCode;

  @FXML
  private AnchorPane rootPane;

  private final UserService us = new UserService(new UserDaoImpl());
  private final HouseholdService hs = new HouseholdService(new HouseholdDaoImpl());

  @FXML
  void addUser(){
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/fxml/views/createProfilePage.fxml"));
      AnchorPane anchorPane = loader.load();
      CreateProfileController createProfileController = loader.getController();
      createProfileController.setNewProfileListener(this);
      rootPane.getChildren().add(anchorPane);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    System.out.println("YourCollectiveController initialized");
    collectiveCode.setText(appUser.getHousehold().getJoinCode());

    System.out.println(appUser.getHousehold().getUsers().size());
    try {
      System.out.println(hs.findHouseholdByJoinCode(appUser.getHousehold().getJoinCode()).getUsers().size());
    } catch (HouseholdNotFoundException e) {
      throw new RuntimeException(e);
    };

    //getData();

    displayUsers();
  }

  private void displayUsers() {
    System.out.println("HALLAHHALLAHALLAHALLA");

    profileContainer.getChildren().clear();
    try {
    for (User user : hs.findHouseholdByJoinCode(appUser.getHousehold().getJoinCode()).getUsers()) {

      System.out.println(user.getUsername() + " " + user.getPassword() +  " " + user.getInventory().getIngredients().size());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
            getClass().getResource("/fxml/components/profilePicture.fxml"));
        AnchorPane anchorPane = loader.load();
        ProfilePictureController profilePictureController = loader.getController();
        profilePictureController.setData(user);
        profilePictureController.setLoginDisplayListener(this);

        profileContainer.getChildren().add(anchorPane);
    }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (HouseholdNotFoundException e) {
      e.printStackTrace();
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
    Household h = null;
    try {
      h = hs.findHouseholdByJoinCode(appUser.getHousehold().getJoinCode());
      h.getUsers().remove(appUser);
    } catch (HouseholdNotFoundException e) {
      System.out.println("Household not found");
    }
    //appUser.getHousehold().removeUser(appUser);
    try {
      hs.refreshHouseholdUsers(h);
      us.saveUserHousehold(appUser);
    } catch (Exception e) {
      e.printStackTrace();
    }
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

  @Override
  public void onNewProfile() {
    displayUsers();
  }

}
