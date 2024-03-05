package ntnu.org.IDATG1005.grp3.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class aSimpleTestController {

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
}
