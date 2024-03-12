package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class profilePictureController {
  @FXML
  private Text profilePictureName;
  @FXML
  private ImageView login;

  private static profilePictureController instance;

  private profilePictureController() {

  }
  public static  synchronized profilePictureController  getInstance(){
    if(instance == null){
      instance = new profilePictureController();
    }
    return instance;
  }

}
