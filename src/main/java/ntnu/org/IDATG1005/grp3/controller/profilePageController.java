package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class profilePageController {
  private static profilePageController instance;

  public profilePageController() {

  }
  public  static  synchronized profilePageController  getInstance(){
    if(instance == null){
      instance = new profilePageController();
    }
    return instance;
  }

  @FXML
  private PasswordField checkPassword;
  public void btnLoginProfile(MouseEvent mouseEvent){
    if(checkPassword.getText().isEmpty()){
      System.out.println("Please fill in all fields");
    }else {
      System.out.println(checkPassword.getText());
      Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
      stage.close();
    }

  }
  public void exitUser(MouseEvent mouseEvent){
    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

}
