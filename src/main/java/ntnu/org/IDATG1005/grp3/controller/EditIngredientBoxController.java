package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

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
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;

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

  private InventoryIngredient inventoryIngredient;

  private OnIngredientUpdateListener updateListener;


  @FXML
  public void initialize() {
    setupTextFieldValidation();
  }

  public void setupTextFieldValidation() {
    editTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      // Regular expression for matching whole numbers greater than 0
      String regex = "^[1-9]\\d*$";

      if (!newValue.matches(regex) && !newValue.isEmpty()) {
        // If new value doesn't match the pattern and is not empty, revert to the old value
        editTextField.setText(oldValue);
      }
    });
  }


  public void setData(Ingredient ingredient) {
    editBoxName.setText(ingredient.getName());
    unitText.setText(ingredient.getUnit().toString());

    boolean isInInventory = false;
    InventoryIngredient foundInventoryIngredient = null;

    try{
    for (InventoryIngredient inventoryIngredient : appUser.getInventory().getIngredients().values()) {
      if (inventoryIngredient.getIngredient().getName().equals(ingredient.getName())) {
        isInInventory = true;
        foundInventoryIngredient = inventoryIngredient;
        break; // Exit the loop as soon as you find the ingredient.
      }
    }
    }catch (NullPointerException e){
    }

    if (isInInventory && foundInventoryIngredient != null) {
      this.inventoryIngredient = foundInventoryIngredient;
    } else {
      this.inventoryIngredient = new InventoryIngredient(ingredient, 0.0);
      removeRemoveButton(); // Hide or disable the remove button as it's not applicable.
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
      double newQuantity = Math.max(inventoryIngredient.getQuantity() - amountToRemove, 0.0);
      appUser.getInventory().getIngredients().get(this.inventoryIngredient.getIngredient()).setQuantity(newQuantity);

      if (updateListener != null) {
        updateListener.onIngredientUpdate();
      }

      // Close the edit box after the operation.
      exitEditBox(event);
    } catch (NumberFormatException e) {
    }
  }

  @FXML
  void onAddButtonClicked(MouseEvent event) {
    try {
      // Parse the amount to add from the text field.
      int amountToAdd = Integer.parseInt(editTextField.getText());
      inventoryIngredient.setQuantity(inventoryIngredient.getQuantity() + amountToAdd);

        if (appUser.getInventory().getIngredients().get(inventoryIngredient.getIngredient())
            != null) {
          appUser.getInventory().getIngredients().get(inventoryIngredient.getIngredient())
              .setQuantity(inventoryIngredient.getQuantity());
        }
        else {
          appUser.getInventory().getIngredients().put(inventoryIngredient.getIngredient(),inventoryIngredient);
        }


      if (updateListener != null) {
        updateListener.onIngredientUpdate();
      }

      // Close the edit box after the operation.
      exitEditBox(event);
    } catch (NumberFormatException e) {
    }
  }



}
