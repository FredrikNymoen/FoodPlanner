package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ntnu.org.IDATG1005.grp3.interfaces.EditBoxDisplayListener;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;

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

  private Ingredient ingredient;
  private Double amount;

  private EditBoxDisplayListener editBoxDisplayListener;


  public void setData(InventoryIngredient invIngredient) {
    this.ingredient = invIngredient.getIngredient();
    this.amount = invIngredient.getQuantity();
    alternativeName.setText(invIngredient.getIngredient().getName());

    if(invIngredient.getQuantity()==-99.0){
      alternativeAmount.setText("");
    }
    else{
      alternativeAmount.setText(invIngredient.getQuantity().toString() + " " + invIngredient.getUnit().getUnitName());
    }
    String url = getClass().getResource("/images/Kniv_Gaffel_ikon.png").toString();
    alternativeImage.setImage(new Image(url));
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


  public void setEditBoxDisplayListener(EditBoxDisplayListener listener) {
    this.editBoxDisplayListener = listener;
  }
  @FXML
  void displayEditBox(MouseEvent event) {
    System.out.println("Displaying edit box for " + ingredient.getName());
    if (editBoxDisplayListener != null) {
      editBoxDisplayListener.onDisplayEditBox(ingredient);
    }
  }



}
