package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
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

  @FXML
  void leaveCollective(MouseEvent event) {
    System.out.println("leaveCollective");
  }

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


  //private static YourCollectiveController instance;

  /*public YourCollectiveController() {

  }*/

  /*public static synchronized YourCollectiveController getInstance() {
    if (instance == null) {
      instance = new YourCollectiveController();
    }
    return instance;
  }*/

  /*@FXML
  void loginToExistingUser(MouseEvent actionEvent) {
    AnchorPane profile;
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/loginToYourProfilePage.fxml"));


      //loader.setController(LoginToYourProfilePageController.getInstance());


      profile = loader.load();
      ((Text) profile.lookup("#profileUsername")).setText(
          CreateProfileController.getInstance().getUsername().getText());

      Stage profileStage = new Stage();
      profileStage.setTitle("Profile");
      Scene scene = new Scene(profile);
      profileStage.setScene(scene);
      //Blocks the main stage until the profile stage is closed
      profileStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
      profileStage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }*/

  /*@FXML
  public void addUserToCollective(MouseEvent mouseEvent) {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/createProfilePage.fxml"));

      loader.setController(CreateProfileController.getInstance());
      Parent root = loader.load();
      CreateProfileController.getInstance().setUsername((TextField) root.lookup("#username"));
      CreateProfileController.getInstance().setPassword((PasswordField) root.lookup("#password"));

      Stage addUser = new Stage();
      addUser.setTitle("Profile");
      Scene scene = new Scene(root);
      addUser.setScene(scene);
      //Blocks the main stage until the profile stage is closed
      addUser.initModality(javafx.stage.Modality.APPLICATION_MODAL);
      addUser.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }*/

  /*public void leaveCollective(MouseEvent mouseEvent) {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/startScreen.fxml"));
      loader.setController(JoinCollectiveController.getInstance());
      Parent root = loader.load();
      Stage joinCollective = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      joinCollective.setScene(scene);
      joinCollective.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }*/

}
