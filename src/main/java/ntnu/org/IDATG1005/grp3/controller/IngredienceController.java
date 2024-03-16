package ntnu.org.IDATG1005.grp3.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ntnu.org.IDATG1005.grp3.dao.implementations.IngredientDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.IngredientDao;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import ntnu.org.IDATG1005.grp3.service.IngredientService;

public class IngredienceController implements Initializable{
    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private CheckBox favoriteCheckBox;

    @FXML
    private GridPane ingredienceSearchGrid;

    @FXML
    private TextField searchField;

    @FXML
    private AnchorPane rootPane;

    private List<InventoryIngredient> ingredients = new ArrayList<>();
    private Map<InventoryIngredient, AnchorPane> ingredientUIMap = new HashMap<>();

    private final IngredientDao ingredientDao = new IngredientDaoImpl();
    private IngredientService ingredientService = new IngredientService(ingredientDao);

    private List<InventoryIngredient> getData() {
        List<InventoryIngredient> inventoryIngredients = new ArrayList<>();
        String url = getClass().getResource("/images/Kniv_Gaffel_ikon.png").toString();

        InventoryIngredient tomat = new InventoryIngredient(new Ingredient(0, "Tomat", url,
            MeasurementUnit.STK), 2.0);
        InventoryIngredient eple = new InventoryIngredient(new Ingredient(0, "Eple", url,
            MeasurementUnit.STK), 4.0);
        InventoryIngredient melk = new InventoryIngredient(new Ingredient(0, "Melk", url,
            MeasurementUnit.LITER), 1.0);
        InventoryIngredient sukker = new InventoryIngredient(new Ingredient(0, "Sukker", url,
            MeasurementUnit.GRAM), 500.0);

        inventoryIngredients.add(tomat);
        inventoryIngredients.add(eple);
        inventoryIngredients.add(melk);
        inventoryIngredients.add(sukker);

        return inventoryIngredients;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("MarketController");
        setupGlobalClickListener();
        initializeSearchBar();

        ingredients.addAll(getData());
        displayIngredients(ingredients);
    }

    private void initializeSearchBar() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    List<Ingredient> searchItems = filterIngredients(newValue);
                    displaySearchAlternatives(searchItems);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ingredienceSearchGrid.getChildren().clear(); // Clear rectangles if no text
            }
        });
    }

    private List<Ingredient> filterIngredients(String newValue) {
        int sublistEndIndex;
        List<Ingredient> searchItems = new ArrayList<>();
        for (Ingredient ingredient : ingredientService.findAllIngredients()) {
            if (ingredient.getName().toLowerCase().startsWith(newValue.toLowerCase())) {
                searchItems.add(ingredient);
            }
        }

        if (searchItems.size()>=3) {
            sublistEndIndex = Math.min(searchItems.size(), 3);
        }else{
            sublistEndIndex = searchItems.size();
        }

        return searchItems.subList(0,sublistEndIndex); // Return the first 3 search results
    }

    private void displaySearchAlternatives(List<Ingredient> searchItems) throws IOException {
        ingredienceSearchGrid.getChildren().clear();
        String url = getClass().getResource("/images/Kniv_Gaffel_ikon.png").toString();

        /*for (Ingredient ingredient : ingredientService.findAllIngredients()) {
            System.out.println(ingredient.getIngredientId() + ": " + ingredient.getName() + " - " + ingredient.getImageUrl());
        }*/

        // Assume you have a list of ingredient alternatives to display
        for (int i = 0; i < searchItems.size(); i++) { // adding 3 alternatives
            String amount = "";
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/components/ingrediens-search-alternative.fxml"));
            HBox hbox = fxmlLoader.load();
            IngredienceSearchAlternativeController alternativeController = fxmlLoader.getController();

            Ingredient ingredient = searchItems.get(i);
            if (isIngredientInInventory(ingredient)!=null){
                amount = isIngredientInInventory(ingredient).getQuantity().toString() +
                    " " + isIngredientInInventory(ingredient).getUnit().getUnitName();
            } else{
                //alternativeController.hideRemoveButton();
            }

            // Example data, replace with actual data for each alternative
            //alternativeController.setData(ingredient.getName(), amount, url);

            ingredienceSearchGrid.add(hbox, 0, i); // Add to column 0, appropriate row
        }
    }

    private InventoryIngredient isIngredientInInventory(Ingredient ingredient) {
        InventoryIngredient invIngredient = null;
        for (InventoryIngredient inventoryIngredient : ingredients) {
            if (inventoryIngredient.getIngredient().getName().equals(ingredient.getName())) {
                System.out.println("Ingredient is in inventory");
                invIngredient = inventoryIngredient;
            }
        }

        return invIngredient;
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


    /**
     * Setup a global click listener to close the search results when clicking outside the search field
     */
    private void setupGlobalClickListener() {
        rootPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Node target = (Node) event.getTarget(); // Get the target node of the click event

            // Check if the target is not the searchField and not one of the rectangles
            if (!isNodeOrDescendantOf(target, searchField) && !isNodeOrDescendantOf(target, ingredienceSearchGrid)) {
                ingredienceSearchGrid.getChildren().clear(); // Clear the rectangles
                // Request focus on the rootPane or another focusable component
                if (!rootPane.isFocusTraversable()) {
                    rootPane.setFocusTraversable(true); // Temporarily make it focusable
                    rootPane.requestFocus(); // Request focus on it
                    rootPane.setFocusTraversable(false); // Optionally, revert it to be non-focusable
                } else {
                    rootPane.requestFocus(); // If it's already focusable, just request focus
                }
            }
        });
    }

    /**
     * Check if a node is the same as another node or a descendant of another node
     * @param node the node to check
     * @param potentialAncestor the potential ancestor node
     * @return true if the node is the same as the potential ancestor or a descendant of the potential ancestor
     */
    private boolean isNodeOrDescendantOf(Node node, Node potentialAncestor) {
        while (node != null) {
            if (node == potentialAncestor) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

}
