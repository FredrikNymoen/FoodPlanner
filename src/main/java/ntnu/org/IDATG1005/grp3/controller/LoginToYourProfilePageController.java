package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.AuthenticationFailedException;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.UserService;

public class LoginToYourProfilePageController {

  @FXML
  private PasswordField checkPassword;

  @FXML
  private Text profileUsername;

  @FXML
  private AnchorPane rootPane;
  private User user;
  private final UserService us = new UserService(new UserDaoImpl());

  public void setData(User user) {
    this.user = user;
    profileUsername.setText(user.getUsername());
  }

  @FXML
  void exitLoginPage(MouseEvent event) {
    // Assuming rootPane's parent is a type of Pane
    Pane parent = (Pane) rootPane.getParent();
    parent.getChildren().remove(rootPane);
  }

  @FXML
  void loggInn(MouseEvent event) {
    //if (user.getPassword().equals(checkPassword.getText()))
    try {
      if (us.authenticateUser(user.getUsername(), checkPassword.getText()) != null){
        checkPassword.setStyle("-fx-border-color: green");
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/views/recipeScreenPage.fxml"));
        Parent root = loader.load();
        // Create a new scene with the loaded content
        Scene scene = new Scene(root);
        // Get the current stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
      }
      else{
        checkPassword.setStyle("-fx-border-color: red");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (AuthenticationFailedException e) {
      checkPassword.setStyle("-fx-border-color: red");
    }


  }

}
