package ntnu.org.IDATG1005.grp3.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
//import main.Main;
//import main.MyListener;
//import model.Fruit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import ntnu.org.IDATG1005.grp3.interfaces.ItemRemovalListener;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;

public class MarketController implements Initializable{
    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private CheckBox favoriteCheckBox;

    private List<InventoryIngredient> ingredients = new ArrayList<>();
    private Map<InventoryIngredient, AnchorPane> ingredientUIMap = new HashMap<>();
    private ItemRemovalListener removalListener; // The listener interface



    private List<InventoryIngredient> getData() {
        List<InventoryIngredient> inventoryIngredients = new ArrayList<>();
        String url = getClass().getResource("/images/Kniv_Gaffel_ikon.png").toString();

        InventoryIngredient tomat = new InventoryIngredient(0, new Ingredient(0, "Tomat", url),
            MeasurementUnit.STK, 2);
        InventoryIngredient eple = new InventoryIngredient(0, new Ingredient(0, "Eple", url),
            MeasurementUnit.STK, 4);
        InventoryIngredient melk = new InventoryIngredient(0, new Ingredient(0, "Melk", url),
            MeasurementUnit.LITER, 1);
        InventoryIngredient sukker = new InventoryIngredient(0, new Ingredient(0, "Sukker", url),
            MeasurementUnit.GRAM, 500);

        inventoryIngredients.add(tomat);
        inventoryIngredients.add(eple);
        inventoryIngredients.add(melk);
        inventoryIngredients.add(sukker);

        return inventoryIngredients;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("MarketController");
        ingredients.addAll(getData());
        displayIngredients(ingredients);
    }

    // In removeItemFromGrid:
    public void removeItemFromGrid(InventoryIngredient ingredientToRemove) {
        AnchorPane paneToRemove = ingredientUIMap.get(ingredientToRemove);
        if (paneToRemove != null) {
            // Remove from GridPane
            grid.getChildren().remove(paneToRemove);
            // Optionally, remove from the ingredient list and update UI accordingly
            ingredients.remove(ingredientToRemove);
        }
        displayIngredients(ingredients);
    }

    @FXML
    private void filterFavorites() {
        List<InventoryIngredient> favoriteIngredients;

        if (favoriteCheckBox.isSelected()) {
            favoriteIngredients = new ArrayList<>();
            for (InventoryIngredient ingredient : ingredients) {
                if (ingredient.getFavoriteStatus()) {
                    favoriteIngredients.add(ingredient);
                }
            }
            displayIngredients(favoriteIngredients); // Implement this method to display ingredients in the grid
        } else {
            displayIngredients(ingredients); // Implement this method to display ingredients in the grid
        }
    }


    private void displayIngredients(List<InventoryIngredient> ingredients) {
        grid.getChildren().clear(); // Clear existing items from the grid
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < ingredients.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/components/ingredience_box.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                System.out.println("HEI");
                ItemController itemController = fxmlLoader.getController();
                System.out.println("hei" + itemController);
                itemController.setData(ingredients.get(i));
                itemController.setRemovalListener(ingredient -> removeItemFromGrid(ingredient));
                System.out.println("HALLA");

                if (column == 4) {
                    column = 0;
                    row++;
                }

                if(ingredients.get(i).getFavoriteStatus()) {
                    itemController.makeStarYellow();
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                ingredientUIMap.put(ingredients.get(i), anchorPane);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(5));
                //Add to grid pane
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
