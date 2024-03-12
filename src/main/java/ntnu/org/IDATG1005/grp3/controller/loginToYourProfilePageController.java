package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginToYourProfilePageController {

  private static loginToYourProfilePageController instance;
  @FXML
  private Text profileUsername;
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

    }

  }

  public void exitUser(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  public void setProfileUsername(Text text) {
    profileUsername = text;
  }

}
