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
import ntnu.org.IDATG1005.grp3.model.objects.User;

public class LoginToYourProfilePageController {

  @FXML
  private PasswordField checkPassword;
  //private Stage primaryStage;

  @FXML
  private Text profileUsername;

  @FXML
  private AnchorPane rootPane;
  private User user;

  public void setData(User user){
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
    if (user.getPassword().equals(checkPassword.getText())){
      try{
        MainApp.appUser = user;
        checkPassword.setStyle("-fx-border-color: green");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/recipeScreenPage.fxml"));
        Parent root = loader.load();
        // Create a new scene with the loaded content
        Scene scene = new Scene(root);
        // Get the current stage and set the new scene
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

      }
      catch (IOException e){
        e.printStackTrace();
      }


    }
    else{
      checkPassword.setStyle("-fx-border-color: red");
    }
  }


  /*public static synchronized LoginToYourProfilePageController getInstance() {
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

  /*
  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }
   */

}
