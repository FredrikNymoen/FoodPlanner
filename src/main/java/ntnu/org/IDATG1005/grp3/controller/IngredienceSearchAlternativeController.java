package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class IngredienceSearchAlternativeController {

  @FXML
  private HBox rootBox;

  @FXML
  private HBox addButton;

  @FXML
  private Text alternativeAmount;

  @FXML
  private ImageView alternativeImage;

  @FXML
  private Text alternativeName;

  @FXML
  private HBox removeButton;

  public void setData(String name, String amount, String imageUrl) {
    alternativeName.setText(name);
    alternativeAmount.setText(amount);
    alternativeImage.setImage(new Image(imageUrl));
  }

  public void hideRemoveButton() {
    removeButton.setVisible(false); // Hides the remove button
  }

}
