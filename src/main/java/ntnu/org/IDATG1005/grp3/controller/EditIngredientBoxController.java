package ntnu.org.IDATG1005.grp3.controller;

import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ntnu.org.IDATG1005.grp3.interfaces.OnIngredientUpdateListener;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;

public class EditIngredientBoxController {
  @FXML
  private AnchorPane editBox;

  @FXML
  private Text editBoxName;

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
  private Pane overlayPane;

  private Ingredient ingredient;

  private InventoryIngredient inventoryIngredient;

  private OnIngredientUpdateListener updateListener;



  public void setData(Ingredient ingredient, List<InventoryIngredient> inventoryIngredients) {
    this.ingredient = ingredient;
    editBoxName.setText(ingredient.getName());
    for (InventoryIngredient inventoryIngredient : inventoryIngredients) {
      if (!inventoryIngredient.getIngredient().getName().equals(ingredient.getName())) {
        //removeRemoveButton();
      }
      else {
        this.inventoryIngredient = inventoryIngredient;
        unitText.setText(inventoryIngredient.getUnit().toString());
      }
    }
  }

  public void removeRemoveButton() {
    removeButton.setVisible(false);
  }

  public void setOverlayPane(Pane overlayPane) {
    this.overlayPane = overlayPane;
  }

  @FXML
  void exitEditBox(MouseEvent event) {
    Platform.runLater(() -> {
      if (overlayPane != null && overlayPane.getParent() != null) {
        ((Pane)overlayPane.getParent()).getChildren().remove(overlayPane);
      }
    });
  }

  public void setOnIngredientUpdateListener(OnIngredientUpdateListener listener) {
    this.updateListener = listener;
  }

  @FXML
  void onRemoveButtonClicked(MouseEvent event) {
    try {
      System.out.println("HALLAALALA");
      // Parse the amount to remove from the text field.
      int amountToRemove = Integer.parseInt(editTextField.getText());

      // Check if the inventory has enough quantity, if not handle the error or set to zero.
      int newQuantity = Math.max(inventoryIngredient.getQuantity() - amountToRemove, 0);
      inventoryIngredient.setQuantity(newQuantity); // Update the model.

      // Update the UI accordingly, e.g., refresh the list or update the display text.
      // ...
      if (updateListener != null) {
        updateListener.onIngredientUpdate();
      }
      // If you have a data persistence layer, update the inventory ingredient there too.
      // ...

      // Close the edit box after the operation.
      exitEditBox(event);
    } catch (NumberFormatException e) {
    }
  }



}
