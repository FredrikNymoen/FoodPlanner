package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.model.User;

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
  public void createUser(ActionEvent actionEvent) throws IOException {
      AnchorPane newUser  = null;
      try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/components/profile_picture.fxml"));
        loader.setController(profilePictureController.getInstance());
        newUser = loader.load();

        username = (TextField) newUser.lookup("#username");
        editProfileController.getInstance().setUsername(username);
        password = (PasswordField) newUser.lookup("#password");
        login = (ImageView) newUser.lookup("#login");
        profilePictureName = (Text) newUser.lookup("#profilePictureName");
        updateUsername();
        addLogin();

        System.out.println(profilePictureName);

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      profileContainer.getChildren().add(newUser);
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      stage.close();
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
  public void updateUsername(){
    Text name = editProfileController.getInstance().getUsername();
    username.setText(name.getText());
  }
  public Text getUsername(){
    return profilePictureName;
  }
  public void addLogin(){
    login.setOnMouseClicked(event -> yourCollectiveController.getInstance().btnExistingProfile(event));
  }
  User user1 = new User(null, "profilePictureName.getText()" ,"userEmail", "password.getText()");
}
