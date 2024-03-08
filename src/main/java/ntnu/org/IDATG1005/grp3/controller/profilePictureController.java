package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class profilePictureController {
  @FXML
  private Text text;
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
