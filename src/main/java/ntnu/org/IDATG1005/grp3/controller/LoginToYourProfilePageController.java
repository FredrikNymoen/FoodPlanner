package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginToYourProfilePageController {

  private static LoginToYourProfilePageController instance;
  @FXML
  private PasswordField checkPassword;
  private Stage primaryStage;

  private LoginToYourProfilePageController() {

  }

  public static synchronized LoginToYourProfilePageController getInstance() {
    if (instance == null) {
      instance = new LoginToYourProfilePageController();
    }
    return instance;
  }

  public void loginToExistingUser(MouseEvent mouseEvent) {
    if (checkPassword.getText().isEmpty()) {
      System.out.println("Please fill in all fields");
    } else {
      System.out.println(checkPassword.getText());
      Stage LoginToProfile = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
      LoginToProfile.close();
      try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/views/recipeScreenPage.fxml"));

        //loader.setController(recipeScreenController.getInstance());
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public void exitUser(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  /*public void closeYourColletive(joinCollectiveController joinCollectiveController){
    ntnu.org.IDATG1005.grp3.controller.joinCollectiveController.getInstance().closeCurrentScene();

  }

   */
  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }
}
