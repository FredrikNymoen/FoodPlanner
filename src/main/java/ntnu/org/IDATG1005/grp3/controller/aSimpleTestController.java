package ntnu.org.IDATG1005.grp3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class aSimpleTestController {

  @FXML
  private Button exButton; // ID matches fx:id in  FXML

  @FXML
  private Text someText;   // ID matches fx:id in FXML

  // handler method(s)?? for button action
  @FXML
  private void handleButtonAction(ActionEvent event) {
    // Toggle the visibility of the text
    someText.setVisible(!someText.isVisible());

    System.out.println(event.getEventType());
  }

  @FXML
  private void testButtonAction(ActionEvent event) {
    System.out.println("reeeee");
  }
}
