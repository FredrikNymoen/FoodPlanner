package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.model.objects.Household;

public class JoinCollectiveController {

  private static JoinCollectiveController instance;
  private Household household;
  private Stage primaryStage;

  public JoinCollectiveController() {

  }

  public static synchronized JoinCollectiveController getInstance() {
    if (instance == null) {
      instance = new JoinCollectiveController();
    }
    return instance;
  }

  public void registerNewCollective(javafx.event.ActionEvent actionEvent) {
    createHousehold();
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/yourCollectivePage.fxml"));
      //loader.setController(YourCollectiveController.getInstance());
      Parent root = loader.load();

      HBox c = (HBox) root.lookup("#profileContainer");
      CreateProfileController.getInstance().setProfileContainer(c);
      Stage yourCollective = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

      Scene scene = new Scene(root);

      yourCollective.setScene(scene);
      yourCollective.show();
      LoginToYourProfilePageController.getInstance().setPrimaryStage(yourCollective);
      System.out.println(yourCollective);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void joinExistingCollective(javafx.event.ActionEvent actionEvent) {
    createHousehold();
    try {
      FXMLLoader loader = new FXMLLoader(
              getClass().getResource("/fxml/views/yourCollectivePage.fxml"));
      loader.setController(YourCollectiveController.getInstance());
      Parent root = loader.load();

      HBox c = (HBox) root.lookup("#profileContainer");
      CreateProfileController.getInstance().setProfileContainer(c);
      Stage yourCollective = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

      Scene scene = new Scene(root);

      yourCollective.setScene(scene);
      yourCollective.show();
      LoginToYourProfilePageController.getInstance().setPrimaryStage(yourCollective);
      System.out.println(yourCollective);
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
  public void closeCurrentScene(){
    primaryStage.close();
  }

}
