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

public class editProfileController {

  @FXML
  private TextField username;
  @FXML
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
    if(username.getText().isEmpty() || password.getText().isEmpty()){
      System.out.println("Please fill in all fields");
    }else {

      System.out.println(username.getText());
      System.out.println(password.getText());
      AnchorPane newUser  = null;

      try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/components/profile_picture.fxml"));
        loader.setController(profilePictureController.getInstance());
        newUser = loader.load();
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
  }
  public void exitUser(MouseEvent mouseEvent){
    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  public void setProfileContainer(HBox profileContainer){
    this.profileContainer = profileContainer;
  }
  public void updateUsername(){
    profilePictureName.setText(username.getText());
  }
  public Text getUsername(){
    return profilePictureName;
  }
  public void addLogin(){
    login.setOnMouseClicked(event -> yourCollectiveController.getInstance().btnExistingProfile(event));
  }
}
