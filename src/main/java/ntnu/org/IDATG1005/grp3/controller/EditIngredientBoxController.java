package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;

public class EditIngredientBoxController {
  @FXML
  private HBox addButton;

  @FXML
  private TextField editTextField;

  @FXML
  private Label exitButton;

  @FXML
  private HBox removeButton;

  @FXML
  private Text unitText;

  /*public void setData(Ingredient ingredient) {
    editTextField.setText(ingredient.getName());
    unitText.setText(ingredient.getUnit().getUnitName());
  }*/

}
