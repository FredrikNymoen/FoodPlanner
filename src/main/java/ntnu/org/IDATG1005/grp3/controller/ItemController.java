package ntnu.org.IDATG1005.grp3.controller;

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
        System.out.println(inventoryIngredient.getIngredient().getImageUrl().toString());
        Image image = new Image(inventoryIngredient.getIngredient().getImageUrl().toString());
        System.out.println("image loaded");
        img.setImage(image);
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
        int amount = Integer.parseInt(amountLabel.getText().split(" ")[0]);
        amount++;
        amountLabel.setText(amount + " " + amountLabel.getText().split(" ")[1]);
    }

    public void setRemovalListener(ItemRemovalListener removalListener) {
        this.removalListener = removalListener;
    }

    @FXML
    public void minusAmount(MouseEvent mouseEvent) {
        int amount = Integer.parseInt(amountLabel.getText().split(" ")[0]);
        if (amount > 0) {
            amount--;
            amountLabel.setText(amount + " " + amountLabel.getText().split(" ")[1]);
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
