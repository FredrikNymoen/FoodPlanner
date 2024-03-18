package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProfilePictureController {

  private static ProfilePictureController instance;
  @FXML
  private Text profilePictureName;
  @FXML
  private ImageView login;

  private ProfilePictureController() {

  }

  public static synchronized ProfilePictureController getInstance() {
    if (instance == null) {
      instance = new ProfilePictureController();
    }
    return instance;
  }

}
