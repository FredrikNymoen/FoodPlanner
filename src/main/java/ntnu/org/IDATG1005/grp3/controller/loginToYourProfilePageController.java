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
      Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
      stage.close();
        try {
          FXMLLoader loader = new FXMLLoader(
                  getClass().getResource("/fxml/views/recipeScreenPage.fxml"));
          loader.setController(recipeScreenController.getInstance());
          Parent root = loader.load();

          VBox c = (VBox) root.lookup("#choseRecipeContainer");
          recipeScreenController.getInstance().setChoseRecipeContainer(c);

          Stage stage1 = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

          Scene scene = new Scene(root);
          stage1.setScene(scene);
          stage1.show();
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

}
