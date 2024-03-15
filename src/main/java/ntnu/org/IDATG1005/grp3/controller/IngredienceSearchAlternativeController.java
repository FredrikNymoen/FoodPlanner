package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
  private HBox editButton;


  public void setData(String name, String amount, String imageUrl) {
    alternativeName.setText(name);
    alternativeAmount.setText(amount);
    alternativeImage.setImage(new Image(imageUrl));
  }


  // Method to show the add button and hide the edit button
  public void showAddButton() {
    addButton.setVisible(true);
    addButton.setManaged(true); // Included in layout calculations
    editButton.setVisible(false);
    editButton.setManaged(false); // Excluded from layout calculations
  }

  // Method to show the edit button and hide the add button
  public void showEditButton() {
    addButton.setVisible(false);
    addButton.setManaged(false);
    editButton.setVisible(true);
    editButton.setManaged(true);
  }


}
