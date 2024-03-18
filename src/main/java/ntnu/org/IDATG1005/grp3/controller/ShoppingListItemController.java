package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ntnu.org.IDATG1005.grp3.model.objects.ShoppingListIngredient;

public class ShoppingListItemController {
  @FXML
  private Label amountLabel;

  @FXML
  private ImageView img;

  @FXML
  private AnchorPane item;

  @FXML
  private Label nameLabel;

  public void setData(ShoppingListIngredient shoppingListIngredient) {
    nameLabel.setText(shoppingListIngredient.getIngredient().getName());
    amountLabel.setText(shoppingListIngredient.getQuantity() + " " + shoppingListIngredient.getIngredient().getUnit().getUnitName());
    //Image image = new Image(inventoryIngredient.getIngredient().getImageUrl().toString());
    //System.out.println("image loaded");
    //img.setImage(image);
  }


}
