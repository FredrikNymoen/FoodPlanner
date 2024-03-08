package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class yourCollectiveController {
  @FXML
  private HBox profileContainer;
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
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/components/profile_page.fxml"));
      loader.setController(profilePageController.getInstance());
      Parent root = loader.load();
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
  @FXML
  public void btnAddProfile(MouseEvent mouseEvent){
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/components/edit_profile.fxml"));
      loader.setController(editProfileController.getInstance());
      Parent root = loader.load();
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


}
