package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class YourCollectiveController {

  private static YourCollectiveController instance;

  public YourCollectiveController() {

  }

  public static synchronized YourCollectiveController getInstance() {
    if (instance == null) {
      instance = new YourCollectiveController();
    }
    return instance;
  }

  @FXML
  void loginToExistingUser(MouseEvent actionEvent) {
    AnchorPane profile;
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/loginToYourProfilePage.fxml"));
      loader.setController(LoginToYourProfilePageController.getInstance());
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
  }

  @FXML
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
  }

  public void leaveCollective(MouseEvent mouseEvent) {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/joinCollectivePage.fxml"));
      loader.setController(JoinCollectiveController.getInstance());
      Parent root = loader.load();
      Stage joinCollective = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      joinCollective.setScene(scene);
      joinCollective.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
