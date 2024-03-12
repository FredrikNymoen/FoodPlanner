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
  private HBox profileContainer;
  private Text profileUsername;

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
      //todo: add user to database
      Stage profileStage = new Stage();
      profileStage.setTitle("Profile");
      Scene scene = new Scene(root);
      profileStage.setScene(scene);
      //Blocks the main stage until the profile stage is closed
      profileStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
      profileStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void leaveCollective(javafx.event.ActionEvent actionEvent) {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/joinCollectivePage.fxml"));
      loader.setController(joinCollectiveController.getInstance());
      Parent root = loader.load();
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  /*public void updateProfileUsername(){
    Text name = editProfileController.getInstance().getUsername();
    profileUsername.setText(name.getText());
  }*/
}
