package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import ntnu.org.IDATG1005.grp3.interfaces.EditBoxDisplayListener;
import ntnu.org.IDATG1005.grp3.interfaces.ItemRemovalListener;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;

public class ItemController {
    @FXML
    private Label amountLabel;

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private AnchorPane item;

    @FXML
    private Label starLabel;

    private ItemRemovalListener removalListener;
    private InventoryIngredient ingredient;
    private EditBoxDisplayListener editBoxDisplayListener;


    public void setData(InventoryIngredient inventoryIngredient) {
        this.ingredient = inventoryIngredient;
        nameLabel.setText(inventoryIngredient.getIngredient().getName());
        amountLabel.setText(inventoryIngredient.getQuantity().toString() + " " + inventoryIngredient.getUnit().getUnitName());
    }

    @FXML
    public void onStarClicked(MouseEvent event) {
        if (!ingredient.getFavoriteStatus()) {
            // Change only the border color, preserving other styles
            item.setStyle(item.getStyle() + "-fx-border-color: yellow;");
            starLabel.setTextFill(Color.YELLOW); // Change star color to yellow
        } else {
            // Revert only the border color, preserving other styles
            String style = item.getStyle().replace("-fx-border-color: yellow;", "-fx-border-color: #69A94F;");
            item.setStyle(style);
            starLabel.setTextFill(Color.WHITE); // Change star color back to white
        }
        ingredient.changeFavoriteStatus();
    }

    public void makeStarYellow() {
        item.setStyle(item.getStyle() + "-fx-border-color: yellow;");
        starLabel.setTextFill(Color.YELLOW);
    }

    @FXML
    public void plusAmount(MouseEvent mouseEvent) {
        double amount = ingredient.getQuantity();
        ingredient.setQuantity(amount + 1.0);
        amountLabel.setText(ingredient.getQuantity() + " " + amountLabel.getText().split(" ")[1]);

    }

    public void setRemovalListener(ItemRemovalListener removalListener) {
        this.removalListener = removalListener;
    }

    @FXML
    public void minusAmount(MouseEvent mouseEvent) {
        double amount = ingredient.getQuantity();
        if (amount > 0) {
            ingredient.setQuantity(amount - 1.0);
            amountLabel.setText(ingredient.getQuantity() + " " + amountLabel.getText().split(" ")[1]);
        }
        else{
            this.removalListener.onItemRemoved(ingredient);
        }
    }

    public void setEditBoxDisplayListener(EditBoxDisplayListener listener) {
        this.editBoxDisplayListener = listener;
    }
    @FXML
    void displayEditBox(MouseEvent event) {
        if (editBoxDisplayListener != null) {
            editBoxDisplayListener.onDisplayEditBox(ingredient.getIngredient());
        }
    }
}
