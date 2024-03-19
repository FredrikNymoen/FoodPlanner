package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.interfaces.RecipeChangedListener;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.User;

public class ChoseRecipeController implements Initializable {

  private Recipe recipe;
  private static ChoseRecipeController instance;
  @FXML
  private Label starLabel;
  @FXML
  private AnchorPane choseRecipeBorder;

  @FXML
  private Text boughtIngredients;

  @FXML
  private Button chose;

  @FXML
  private Button lookRecipe;

  @FXML
  private Text missingIngredients;

  @FXML
  private Text recipeDescription;

  @FXML
  private ImageView recipeImage;

  @FXML
  private Text recipeName;
  private RecipeChangedListener recipeChangedListener;

  public ChoseRecipeController() {

  }
  @Override
  public void initialize(URL location, ResourceBundle resourceBundle) {
    intializeUser();
  }


  public static synchronized ChoseRecipeController getInstance() {
    if (instance == null) {
      instance = new ChoseRecipeController();
    }
    return instance;
  }

  public void setData(Recipe recipe) {
    recipeName.setText(recipe.getRecipeInfo().getTitle());
    recipeImage.setImage(new Image("/images/Kniv_Gaffel_ikon.png"));
    recipeDescription.setText(recipe.getRecipeInfo().getDescription());
    this.recipe = recipe;
    checkIngredients();
  }

  @FXML
  public void chooseRecipe() {
    if (appUser.getChosenRecipes().contains(recipe)) {
      appUser.removeChosenRecipe(recipe);
      chose.setStyle("-fx-background-color: #00b400cc");
      chose.setText("Choose");
    } else {
      appUser.addChosenRecipe(recipe);
      System.out.println(appUser.getChosenRecipes().size());
      chose.setStyle("-fx-background-color: #f00");
      chose.setText("Remove");
    }
  }

  @FXML
  public void showingRecipe() {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/components/showingRecipe.fxml"));
      loader.setController(ShowingRecipeController.getInstance());
      Parent root = loader.load();

      Stage showingRecipe = new Stage();

      Scene scene = new Scene(root);

      showingRecipe.setScene(scene);
      showingRecipe.show();
      LoginToYourProfilePageController.getInstance().setPrimaryStage(showingRecipe);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void favoriteClick() {
    recipe.changeFavoriteStatus();
    if (recipeChangedListener != null) {
      recipeChangedListener.onRecipeChanged(recipe);
    }
    System.out.println("added");
  }

  public void setRecipeChangedListener(RecipeChangedListener recipeChangedListener) {
    this.recipeChangedListener = recipeChangedListener;
  }

  public void changeAppearance() {
    System.out.println(recipe.getFavoriteStatus());
    if (recipe.getFavoriteStatus()) {
      starLabel.setText("★");
      System.out.println("added to fave");
      starLabel.setStyle("-fx-text-fill: yellow;");
      choseRecipeBorder.setStyle("-fx-border-color: yellow; -fx-border-width: 5;");
    }
    if (appUser.getChosenRecipes().contains(recipe)) {
      appUser.addChosenRecipe(recipe);
      System.out.println(appUser.getChosenRecipes().size());
      chose.setStyle("-fx-background-color: #f00");
      chose.setText("Remove");
    }
  }

  public void checkIngredients() {
    Integer matchedIngredients = 0;
    List<String> missingIngredientsList = new ArrayList<>(); // List to hold names of missing ingredients
    int totalRecipeIngredients = recipe.getIngredients().size();
    System.out.println("Checking ingredients for recipe: " + recipe.getRecipeInfo().getTitle());
    // Check each ingredient in the recipe
    if (appUser != null && appUser.getInventory() != null) {
      for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
        String ingredientName = recipeIngredient.getIngredient().getName();
        // Check if the user's inventory contains this ingredient
        if (appUser.getInventory().getIngredients()
            .containsKey(ingredientName)) {
          matchedIngredients++; // Ingredient is in inventory
        } else {
          missingIngredientsList.add(ingredientName); // Ingredient is missing
        }
      }
    } else {
      System.out.println("User or inventory is null");
    }

    // Update the UI for matched ingredients
    boughtIngredients.setText(matchedIngredients.toString() /* + " of " + totalRecipeIngredients*/);

    int missingCount =
        totalRecipeIngredients - matchedIngredients; // Calculate the number of missing ingredients
    this.missingIngredients.setText(String.valueOf(missingCount));
  }
  public void intializeUser(){
    appUser = new User(1, "test", "test");
    Ingredient ingredient1 = MainApp.appIngredients.get(0);
    Ingredient ingredient2 = MainApp.appIngredients.get(1);
    /*System.out.println("HEISANN");
    for (Ingredient ingredient : MainApp.appIngredients) {
      System.out.println(ingredient.getName());
    }*/

    Inventory inventory = new Inventory(new HashMap<>());
    inventory.getIngredients().put(ingredient1, new InventoryIngredient(ingredient1, 5.0));
    inventory.getIngredients().put(ingredient2, new InventoryIngredient(ingredient2, 5.0));
    appUser.setInventory(inventory);
  }
}
