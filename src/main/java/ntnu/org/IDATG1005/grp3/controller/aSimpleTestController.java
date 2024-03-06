package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class aSimpleTestController {
  @FXML
  private void btnProfile(MouseEvent actionEvent) {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/components/profile_page.fxml"));
      Parent root = loader.load();

      Stage profileStage = new Stage();
      profileStage.setTitle("Profile");
      Scene scene = new Scene(root);

      profileStage.setScene(scene);

      profileStage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void exitUser(MouseEvent mouseEvent){
    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }


  public void btnNextSite(javafx.event.ActionEvent actionEvent) {

    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/your_collective_pg.fxml"));
      Parent root = loader.load();

      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void btnLoginProfile(MouseEvent mouseEvent){
    try {
      exitUser(mouseEvent);
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/components/edit_profile.fxml"));
      Stage profileStage = new Stage();
      profileStage.setTitle("Profile");
      Parent root = loader.load();
      Scene scene = new Scene(root);

      profileStage.setScene(scene);

      profileStage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
