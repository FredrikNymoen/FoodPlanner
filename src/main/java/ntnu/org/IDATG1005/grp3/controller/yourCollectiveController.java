package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
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
import ntnu.org.IDATG1005.grp3.model.User;


public class yourCollectiveController {
  @FXML
  private HBox profileContainer;

  private Text profileUsername;
  private static yourCollectiveController instance;

  public yourCollectiveController() {

  }
  public  static  synchronized yourCollectiveController  getInstance(){
    if(instance == null){
      instance = new yourCollectiveController();
    }
    return instance;
  }
  @FXML
  void btnExistingProfile(MouseEvent actionEvent) {
    AnchorPane profile = null;
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/components/profile_page.fxml"));
      loader.setController(profilePageController.getInstance());
      profile = loader.load();

      profileUsername = (Text) profile.lookup("#profileUsername");
      updateProfileUsername();

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
  public void btnAddProfile(MouseEvent mouseEvent){
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/components/edit_profile.fxml"));

      loader.setController(editProfileController.getInstance());
      Parent root = loader.load();
      editProfileController.getInstance().setUsername((TextField) root.lookup("#username"));
      editProfileController.getInstance().setPassword((PasswordField) root.lookup("#password"));
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
  public void leaveCollective(javafx.event.ActionEvent actionEvent){
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/join_collective_pg.fxml"));
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
  public void updateProfileUsername(){
    Text name = editProfileController.getInstance().getUsername();
    profileUsername.setText(name.getText());
  }
}
