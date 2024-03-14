package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
import java.util.Stack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.model.objects.Household;

public class joinCollectiveController {

  private static joinCollectiveController instance;
  private Household household;

  private Stack<Scene> previousScenes = new Stack<>();
  private Stage primaryStage;

  public joinCollectiveController() {

  }

  public static synchronized joinCollectiveController getInstance() {
    if (instance == null) {
      instance = new joinCollectiveController();
    }
    return instance;
  }
  public void start(Stage primaryStage){
    this.primaryStage = primaryStage;
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
      Stage yourCollective = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

      Scene scene = new Scene(root);

      previousScenes.push(yourCollective.getScene());

      yourCollective.setScene(scene);
      yourCollective.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void closeYourColletive(MouseEvent mouseEvent){
    Stage yourCollective = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    yourCollective.close();
  }

  public void createHousehold() {
    this.household = new Household(null, "kol", "k1");
  }

  public Household getHousehold() {
    return this.household;
  }
  public void closeCurrentScene(){
    primaryStage.close();
  }

}
