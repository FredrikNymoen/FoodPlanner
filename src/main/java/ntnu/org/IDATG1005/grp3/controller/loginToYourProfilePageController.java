package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class loginToYourProfilePageController {

  private static loginToYourProfilePageController instance;
  @FXML
  private PasswordField checkPassword;


  public loginToYourProfilePageController() {

  }

  public static synchronized loginToYourProfilePageController getInstance() {
    if (instance == null) {
      instance = new loginToYourProfilePageController();
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
          loader.setController(recipeScreenController.getInstance());
          Parent root = loader.load();

          VBox c = (VBox) root.lookup("#choseRecipeContainer");
          recipeScreenController.getInstance().setChoseRecipeContainer(c);

          Stage recipeScreenPage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

          Scene scene = new Scene(root);
          recipeScreenPage.setScene(scene);
          recipeScreenPage.show();
          closeYourColletive(joinCollectiveController.getInstance());
          recipeScreenController.getInstance().onLoadRecipeScreen(mouseEvent);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    }

  public void exitUser(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }
  public void closeYourColletive(joinCollectiveController joinCollectiveController){
    ntnu.org.IDATG1005.grp3.controller.joinCollectiveController.getInstance().closeCurrentScene();

  }

}
