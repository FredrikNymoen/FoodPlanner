package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.model.objects.User;

public class editProfileController {

  private TextField username;
  private PasswordField password;
  private  HBox profileContainer;
  private Text profilePictureName;
  private ImageView login;
  private static editProfileController instance;

  private editProfileController() {

  }
  public  static  synchronized editProfileController  getInstance(){
    if(instance == null){
      instance = new editProfileController();
    }
    return instance;
  }
  public void createUser(MouseEvent mouseEvent) throws IOException {
      AnchorPane newUser;
      try {
        boolean isValid =  validateUser();
        if(isValid) {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/components/profile_picture.fxml"));
        loader.setController(profilePictureController.getInstance());
        newUser = loader.load();

        login = (ImageView) newUser.lookup("#login");

          ((Text) newUser.lookup("#profilePictureName")).setText(username.getText());
          addLogin();
          User user1 = new User(null, username.getText(), password.getText());
          // todo database
          System.out.println(user1);
          profileContainer.getChildren().add(newUser);
          Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
          stage.close();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

  }
  public void exitUser(MouseEvent mouseEvent){
    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  public void setProfileContainer(HBox profileContainer){
    this.profileContainer = profileContainer;
  }
  public void setUsername(TextField username){
    this.username = username;
  }
  public Text getUsername(){
    return profilePictureName;
  }
  public void setPassword(PasswordField password){
    this.password = password;
  }
  public void addLogin(){
    login.setOnMouseClicked(event -> yourCollectiveController.getInstance().btnExistingProfile(event));
  }
  public boolean validateUser(){
    if(username.getText().isEmpty() || password.getText().isEmpty()){
    Alert alert = new Alert (Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Logg inn feil");
    alert.setContentText("Please fill in all fields");
    alert.showAndWait();
    return false;
    }
    return true;
  }
}
