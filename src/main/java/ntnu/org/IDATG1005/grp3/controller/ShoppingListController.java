package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.ShoppingListIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.User;

public class ShoppingListController implements Initializable{

  @FXML
  private HBox buyButton;

  @FXML
  private GridPane chosenRecipesGrid;

  /*@FXML
  private ScrollPane scroll;

  @FXML
  private ScrollPane scroll1;*/

  @FXML
  private GridPane shoppingListGrid;

  private Map<ShoppingListIngredient, AnchorPane> ingredientUIMap = new HashMap<>();



  @Override
  public void initialize(URL location, ResourceBundle resources){
    appUser = new User(1, "test", "test");

    Map<Ingredient, InventoryIngredient> ingredients = appUser.getInventory().getIngredients();


    appUser.addChosenRecipe(MainApp.appRecipes.get(0));


    for(RecipeIngredient recIngredient : appUser.getChosenRecipes().get(0).getIngredients()){
      if(appUser.getInventory().getIngredients()){
        appUser.addShoppingListIngredient(new ShoppingListIngredient(recIngredient.getIngredient(), recIngredient.getAmount()));
      }
      for(InventoryIngredient invIngredient : appUser.getInventory().getIngredients()){
        if(invIngredient.getIngredient().getName().equals(recIngredient.getIngredient().getName())){
          double shoppingListAmount = recIngredient.getAmount() - invIngredient.getQuantity();
          if(shoppingListAmount > 0){
            appUser.addShoppingListIngredient(new ShoppingListIngredient(recIngredient.getIngredient(), shoppingListAmount));
          }
        }
      }
      appUser.addShoppingListIngredient(new ShoppingListIngredient(ingredient, 10));
    }

    appUser.addShoppingListIngredient(new ShoppingListIngredient(new Ingredient(0, "Tomat", "", MeasurementUnit.STK), 5));
    appUser.addShoppingListIngredient(new ShoppingListIngredient(new Ingredient(0, "Erter", "", MeasurementUnit.GRAM), 30));

    displayShoppingList();
  }

  public void displayShoppingList(){
    List<ShoppingListIngredient> shoppingList = appUser.getShoppingList();
    shoppingListGrid.getChildren().clear(); // Clear existing items from the grid
    int column = 0;
    int row = 1;
    try {
      for (int i = 0; i < shoppingList.size(); i++) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(
            getClass().getResource("/fxml/components/shoppingListItem.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ShoppingListItemController itemController = fxmlLoader.getController();
        itemController.setData(shoppingList.get(i));// 'this' refers to an instance of IngredienceController

        if (column == 4) {
          column = 0;
          row++;
        }

        shoppingListGrid.add(anchorPane, column++, row); //(child,column,row)
        ingredientUIMap.put(shoppingList.get(i), anchorPane);
        shoppingListGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
        shoppingListGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        shoppingListGrid.setMaxWidth(Region.USE_PREF_SIZE);
        shoppingListGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
        shoppingListGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        shoppingListGrid.setMaxHeight(Region.USE_PREF_SIZE);
        GridPane.setMargin(anchorPane, new Insets(5));
        //Add to grid pane
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



}
