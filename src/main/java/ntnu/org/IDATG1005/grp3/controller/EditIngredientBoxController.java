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
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;

public class EditIngredientBoxController {

  @FXML
  private AnchorPane editBox;

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


  public void setData(Ingredient ingredient, List<InventoryIngredient> inventoryIngredients) {
    editTextField.setText(ingredient.getName());
    for (InventoryIngredient inventoryIngredient : inventoryIngredients) {
      if (!inventoryIngredient.getIngredient().getName().equals(ingredient.getName())) {
        //removeRemoveButton();
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
      if (overlayPane != null) {
        // Assuming editBox is part of the overlayPane, which is part of the scene's rootPane
        ((Pane)overlayPane.getParent()).getChildren().remove(overlayPane);
      }
    });
  }




}
