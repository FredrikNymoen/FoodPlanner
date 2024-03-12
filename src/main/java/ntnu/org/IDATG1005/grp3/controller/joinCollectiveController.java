package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class joinCollectiveController {

  private static joinCollectiveController instance;

  public joinCollectiveController() {

  }
  public  static  synchronized joinCollectiveController getInstance(){
    if(instance == null){
      instance = new joinCollectiveController();
    }
    return instance;
  }

  public void btnNextSite(javafx.event.ActionEvent actionEvent) {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/your_collective.fxml"));
      loader.setController(yourCollectiveController.getInstance());
      Parent root = loader.load();

      System.out.println("Button clicked");
      HBox c = (HBox) root.lookup("#profileContainer");
      editProfileController.getInstance().setProfileContainer(c);
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
