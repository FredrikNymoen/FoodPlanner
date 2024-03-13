package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.model.objects.Household;

public class joinCollectiveController {

  private static joinCollectiveController instance;
  private Household household;

  public joinCollectiveController() {

  }

  public static synchronized joinCollectiveController getInstance() {
    if (instance == null) {
      instance = new joinCollectiveController();
    }
    return instance;
  }

  public void btnNextSite(javafx.event.ActionEvent actionEvent) {
    createHousehold();
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/yourCollectivePage.fxml"));
      loader.setController(yourCollectiveController.getInstance());
      Parent root = loader.load();

      HBox c = (HBox) root.lookup("#profileContainer");
      createProfileController.getInstance().setProfileContainer(c);
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void createHousehold() {
    this.household = new Household(null, "kol", "k1");
  }

  public Household getHousehold() {
    return this.household;
  }

}
