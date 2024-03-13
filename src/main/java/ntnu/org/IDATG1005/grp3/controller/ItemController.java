package ntnu.org.IDATG1005.grp3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;

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

    private boolean isStarYellow = false; // Initially, the star is not yellow

    private Ingredient ingredient;


    public void setData(Ingredient ingredient) {
        this.ingredient = ingredient;
        nameLabel.setText(ingredient.getName());
        amountLabel.setText("0 stk");
        System.out.println(ingredient.getImageUrl());
        Image image = new Image(ingredient.getImageUrl().toString());
        System.out.println("image loaded");
        img.setImage(image);

    }

    @FXML
    void onStarClicked(MouseEvent event) {
        if (!isStarYellow) {
            // Change only the border color, preserving other styles
            item.setStyle(item.getStyle() + "-fx-border-color: yellow;");
            starLabel.setTextFill(Color.YELLOW); // Change star color to yellow
            isStarYellow = true; // Update the toggle state
        } else {
            // Revert only the border color, preserving other styles
            String style = item.getStyle().replace("-fx-border-color: yellow;", "-fx-border-color: #69A94F;");
            item.setStyle(style);
            starLabel.setTextFill(Color.WHITE); // Change star color back to white
            isStarYellow = false; // Update the toggle state
        }
    }
}
