package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.FailedToLoadInventoryException;
import ntnu.org.IDATG1005.grp3.interfaces.ShoppingListRecipeRemovalListener;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.ShoppingListIngredient;
import ntnu.org.IDATG1005.grp3.service.UserService;

public class ShoppingListController implements Initializable, ShoppingListRecipeRemovalListener {


  @FXML
  private GridPane chosenRecipesGrid;

  @FXML
  private GridPane shoppingListGrid;

  private Map<ShoppingListIngredient, AnchorPane> ingredientUIMap = new HashMap<>();
  private Map<Recipe, HBox> chosenRecipeUIMap = new HashMap<>();

  private final UserService us = new UserService(new UserDaoImpl());



  /*public void getData(){
    appUser = new User(1, "test", "test");
    Ingredient ingredient1 = new Ingredient(1, "Tomat", "", MeasurementUnit.STK);

    Inventory inventory = new Inventory(new HashMap<>());
    inventory.getIngredients().put(ingredient1, new InventoryIngredient(ingredient1, 5.0));
    appUser.setInventory(inventory);
  }*/

  public void fillShoppingList(){
    appUser.getShoppingList().clear();

    Collection<InventoryIngredient> ingredients = appUser.getInventory().getIngredients().values();
    for (Recipe recipe : appUser.getShoppingCartRecipes()) {
      for(RecipeIngredient recIngredient : recipe.getIngredients()){
        boolean recIngredientIsInInventory = false;
        double shoppingListAmount = recIngredient.getAmount();
        for(InventoryIngredient invIngredient : ingredients){
          if(invIngredient.getIngredient().getName().equals(recIngredient.getIngredient().getName())){
            shoppingListAmount = recIngredient.getAmount() - invIngredient.getQuantity();
            if(shoppingListAmount > 0.0){

              recIngredientIsInInventory = true;
            }
          }
        }

        if(recIngredientIsInInventory){
          appUser.addShoppingListIngredient(new ShoppingListIngredient(recIngredient.getIngredient(), shoppingListAmount));
        }
        else {
          appUser.addShoppingListIngredient(new ShoppingListIngredient(recIngredient.getIngredient(), recIngredient.getAmount()));
        }
      }
    }

  }

  @Override
  public void initialize(URL location, ResourceBundle resources){
    //getData();
    //appUser.addChosenRecipe(MainApp.appRecipes.get(0));
    //appUser.addChosenRecipe(MainApp.appRecipes.get(1));
    //appUser.addChosenRecipe(MainApp.appRecipes.get(2));

    displayShoppingList();
    displayChosenRecipes();
  }

  public void displayChosenRecipes(){
    List<Recipe> shoppingCartRecipes = appUser.getShoppingCartRecipes();
    chosenRecipesGrid.getChildren().clear(); // Clear existing items from the grid
    int column = 0;
    int row = 1;
    try {
      for (int i = 0; i < shoppingCartRecipes.size(); i++) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(
            getClass().getResource("/fxml/components/shoppingListChosenRecipe.fxml"));
        HBox hBox = fxmlLoader.load();
        ShoppingListChosenRecipeController chosenRecipeController = fxmlLoader.getController();
        chosenRecipeController.setData(shoppingCartRecipes.get(i));// 'this' refers to an instance of IngredienceController
        chosenRecipeController.setShoppingListRecipeRemovalListener(this);


        chosenRecipesGrid.add(hBox, column++, row); //(child,column,row)
        chosenRecipeUIMap.put(appUser.getChosenRecipes().get(i), hBox);
        GridPane.setMargin(hBox, new Insets(5));
        //Add to grid pane
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void displayShoppingList(){

    fillShoppingList();

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
        GridPane.setMargin(anchorPane, new Insets(5));
        //Add to grid pane
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onRecipeRemoved(Recipe recipe) {
    appUser.getChosenRecipes().remove(recipe);
    chosenRecipesGrid.getChildren().remove(chosenRecipeUIMap.get(recipe));
    us.saveChosenRecipes(appUser);
    chosenRecipeUIMap.remove(recipe);
    displayChosenRecipes();
    displayShoppingList();
  }

  @FXML
  void buyShoppingList(MouseEvent event) {
    for (ShoppingListIngredient ingredient : appUser.getShoppingList()) {
      appUser.getInventory().addIngredient(ingredient);
    }
    for (Recipe recipe : appUser.getShoppingCartRecipes()) {
      recipe.setBeenBought(true);
    }
    try {
      us.saveUserInventory(appUser);
    } catch (FailedToLoadInventoryException e) {
      throw new RuntimeException(e);
    }
    us.saveChosenRecipes(appUser);

    displayChosenRecipes();
    displayShoppingList();
  }

}
