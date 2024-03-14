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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class yourCollectiveController {

  private static yourCollectiveController instance;

  public yourCollectiveController() {

  }

  public static synchronized yourCollectiveController getInstance() {
    if (instance == null) {
      instance = new yourCollectiveController();
    }
    return instance;
  }

  @FXML
  void loginToExistingUser(MouseEvent actionEvent) {
    AnchorPane profile;
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/loginToYourProfilePage.fxml"));
      loader.setController(loginToYourProfilePageController.getInstance());
      profile = loader.load();
      ((Text) profile.lookup("#profileUsername")).setText(
          createProfileController.getInstance().getUsername().getText());

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

      loader.setController(createProfileController.getInstance());
      Parent root = loader.load();
      createProfileController.getInstance().setUsername((TextField) root.lookup("#username"));
      createProfileController.getInstance().setPassword((PasswordField) root.lookup("#password"));

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
      loader.setController(joinCollectiveController.getInstance());
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
