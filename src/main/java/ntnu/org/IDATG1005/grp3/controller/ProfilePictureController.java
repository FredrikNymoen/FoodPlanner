package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ntnu.org.IDATG1005.grp3.interfaces.LoginDisplayListener;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.User;

public class ProfilePictureController {

  @FXML
  private Text profilePictureName;
  @FXML
  private ImageView login;

  private LoginDisplayListener loginDisplayListener;

  private User user;

  public void setData(User user) {
    this.user = user;
    profilePictureName.setText(user.getUsername());
  }


  public void setLoginDisplayListener(LoginDisplayListener loginDisplayListener) {
    this.loginDisplayListener = loginDisplayListener;
  }

  @FXML
  void displayLoginScreen(MouseEvent event) {
    if (loginDisplayListener != null){
      loginDisplayListener.onLoginDisplay(this.user);
    }
  }

}
