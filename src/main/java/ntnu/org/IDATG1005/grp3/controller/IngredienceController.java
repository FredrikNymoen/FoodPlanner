package ntnu.org.IDATG1005.grp3.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ntnu.org.IDATG1005.grp3.dao.implementations.IngredientDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.interfaces.IngredientDao;
import ntnu.org.IDATG1005.grp3.interfaces.EditBoxDisplayListener;
import ntnu.org.IDATG1005.grp3.interfaces.ItemRemovalListener;
import ntnu.org.IDATG1005.grp3.interfaces.OnIngredientUpdateListener;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import ntnu.org.IDATG1005.grp3.service.IngredientService;

public class IngredienceController implements Initializable, EditBoxDisplayListener,
    OnIngredientUpdateListener {

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

    private PauseTransition pauseTransition = new PauseTransition(
        Duration.millis(300)); // 300ms debounce

    private List<InventoryIngredient> ingredients = new ArrayList<>();
    private Map<InventoryIngredient, AnchorPane> ingredientUIMap = new HashMap<>();

    private final IngredientDao ingredientDao = new IngredientDaoImpl();
    private IngredientService ingredientService = new IngredientService(ingredientDao);


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
        inventoryIngredients.add(sukker);
        inventoryIngredients.add(sukker);
        inventoryIngredients.add(sukker);
        inventoryIngredients.add(sukker);
        inventoryIngredients.add(sukker);
        inventoryIngredients.add(sukker);
        inventoryIngredients.add(sukker);
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

    /*private void initializeSearchBar() {
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
    }*/

    private void initializeSearchBar() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            pauseTransition.setOnFinished(event -> performSearch(newValue));
            pauseTransition.playFromStart(); // Reset the timer every time text changes
        });
    }

    private void performSearch(String query) {
        if (!query.isEmpty()) {
            try {
                List<Ingredient> searchItems = filterIngredients(query);
                displaySearchAlternatives(searchItems);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ingredienceSearchGrid.getChildren().clear();
        }
    }

    private List<Ingredient> filterIngredients(String newValue) {
        int sublistEndIndex;
        List<Ingredient> searchItems = new ArrayList<>();
        for (Ingredient ingredient : ingredientService.findAllIngredients()) {
            System.out.println(ingredient.getName());
            if (ingredient.getName().toLowerCase().startsWith(newValue.toLowerCase())) {
                searchItems.add(ingredient);
            }
        }

        if (searchItems.size() >= 3) {
            sublistEndIndex = Math.min(searchItems.size(), 3);
        } else {
            sublistEndIndex = searchItems.size();
        }

        return searchItems.subList(0, sublistEndIndex); // Return the first 3 search results
    }

    private void displaySearchAlternatives(List<Ingredient> searchItems) throws IOException {
        ingredienceSearchGrid.getChildren().clear();
        String url = getClass().getResource("/images/Kniv_Gaffel_ikon.png").toString();

        // Assume you have a list of ingredient alternatives to display
        for (int i = 0; i < searchItems.size(); i++) { // adding 3 alternatives
            String amount = "";
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(
                getClass().getResource("/fxml/components/ingrediens-search-alternative.fxml"));
            HBox hbox = fxmlLoader.load();
            hbox.setId("searchAlternative");
            IngredienceSearchAlternativeController alternativeController = fxmlLoader.getController();

            Ingredient ingredient = searchItems.get(i);
            if (isIngredientInInventory(ingredient) != null) {
                amount = isIngredientInInventory(ingredient).getQuantity().toString() +
                    " " + isIngredientInInventory(ingredient).getUnit().getUnitName();
                alternativeController.showEditButton();
            } else{
                alternativeController.showAddButton();
            }

            // Example data, replace with actual data for each alternative
            alternativeController.setData(ingredient.getName(), amount, url);

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
            displayIngredients(
                favoriteIngredients); // Implement this method to display ingredients in the grid
        } else {
            displayIngredients(
                ingredients); // Implement this method to display ingredients in the grid
        }
    }

    @Override
    public void onDisplayEditBox(Ingredient ingredient) {
        // Overlay pane with opacity
        AnchorPane overlay = new AnchorPane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // 50% opacity
        overlay.setLayoutX(0);
        overlay.setLayoutY(0);
        overlay.setPrefSize(rootPane.getWidth(), rootPane.getHeight());

        // Ensure the overlay resizes with the root pane
        AnchorPane.setTopAnchor(overlay, 0.0);
        AnchorPane.setBottomAnchor(overlay, 0.0);
        AnchorPane.setLeftAnchor(overlay, 0.0);
        AnchorPane.setRightAnchor(overlay, 0.0);

        try {
            // Load the FXML file for the edit ingredient box
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/components/ingrediens.fxml"));
            AnchorPane editBox = loader.load();

            overlay.getChildren().add(editBox);
            // Get the controller and set the data
            EditIngredientBoxController editBoxController = loader.getController();
            editBoxController.setOverlayPane(overlay);
            editBoxController.setData(ingredient, ingredients);
            editBoxController.setOnIngredientUpdateListener(this);

            // Position the edit box in the middle of the overlay
            editBox.setLayoutX((rootPane.getWidth() - editBox.getPrefWidth()) / 2);
            editBox.setLayoutY((rootPane.getHeight() - editBox.getPrefHeight()) / 2);

            // Display logic remains the same
            Platform.runLater(() -> rootPane.getChildren().add(overlay));

            System.out.println("Display edit box");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void displayIngredients(List<InventoryIngredient> ingredients) {
        grid.getChildren().clear(); // Clear existing items from the grid
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < ingredients.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(
                    getClass().getResource("/fxml/components/ingredience_box.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                System.out.println("HEI");
                ItemController itemController = fxmlLoader.getController();
                System.out.println("hei" + itemController);
                itemController.setData(ingredients.get(i));
                itemController.setRemovalListener(ingredient -> removeItemFromGrid(ingredient));
                itemController.setEditBoxDisplayListener(
                    this); // 'this' refers to an instance of IngredienceController
                System.out.println("HALLA");

                if (column == 4) {
                    column = 0;
                    row++;
                }

                if (ingredients.get(i).getFavoriteStatus()) {
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

    @Override
    public void onIngredientUpdate() {
        // Call your method to update the display
        displayIngredients(ingredients);
    }

    /**
     * Setup a global click listener to close the search results when clicking outside the search field
     */
    private void setupGlobalClickListener() {
        rootPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Node target = (Node) event.getTarget();
            target=target.getParent();
            // Check if the clicked node or any of its parents is a TextField or ImageView.
            // This allows interaction with all text fields and image views without triggering the click away logic.
            System.out.println(target.getClass().getName());
            if (isNodeOrParentInstanceOf(target, TextField.class) || isDescendantOrSelfWithId(target, "searchAlternative")){
            } else {
                // Handle clicks outside of TextFields and ImageViews.
                // Example: clearing a search grid or setting focus elsewhere.
                ingredienceSearchGrid.getChildren().clear();
                if (!rootPane.isFocusTraversable()) {
                    rootPane.setFocusTraversable(true);
                    rootPane.requestFocus();
                    rootPane.setFocusTraversable(false);
                } else {
                    rootPane.requestFocus();
                }
            }
        });
    }

    private boolean isNodeOrParentInstanceOf(Node node, Class<?> clazz) {
        while (node != null) {
            if (clazz.isInstance(node)) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    private boolean isDescendantOrSelfWithId(Node node, String id) {
        while (node != null) {
            System.out.println(node.getId() + " " + node.getClass().getName());
            if (node.getId() != null && node.getId().equals(id)) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

}