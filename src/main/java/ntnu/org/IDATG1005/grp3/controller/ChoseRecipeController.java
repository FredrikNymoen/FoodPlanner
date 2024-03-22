package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import ntnu.org.IDATG1005.grp3.interfaces.RecipeChangedListener;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.User;

public class ChoseRecipeController implements Initializable {

  private static ChoseRecipeController instance;
  private Recipe recipe;
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

  public static synchronized ChoseRecipeController getInstance() {
    if (instance == null) {
      instance = new ChoseRecipeController();
    }
    return instance;
  }

  @Override
  public void initialize(URL location, ResourceBundle resourceBundle) {

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
      chose.setStyle("-fx-background-color: #f00");
      chose.setText("Remove");
    }
  }
  public void showingRecipe() {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/components/showingRecipe.fxml"));
      Parent root = loader.load();
      ShowingRecipeController controller = loader.getController();
      controller.setData(recipe);

      Stage showingRecipe = new Stage();

      Scene scene = new Scene(root);

      showingRecipe.setScene(scene);
      showingRecipe.show();
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
  }

  public void setRecipeChangedListener(RecipeChangedListener recipeChangedListener) {
    this.recipeChangedListener = recipeChangedListener;
  }

  public void changeAppearance() {
    if (recipe.getFavoriteStatus()) {
      starLabel.setText("★");
      starLabel.setStyle("-fx-text-fill: yellow;");
      choseRecipeBorder.setStyle(choseRecipeBorder.getStyle() + "-fx-border-color: yellow;");
    }
    if (appUser.getChosenRecipes().contains(recipe)) {
      appUser.addChosenRecipe(recipe);
      chose.setStyle("-fx-background-color: #f00");
      chose.setText("Remove");
    }
  }

  public void checkIngredients() {
    Integer matchedIngredients = 0;
    List<Ingredient> missingIngredientsList = new ArrayList<>();
    int totalRecipeIngredients = recipe.getIngredients().size();
    if (appUser != null && appUser.getInventory() != null) {
      for (int i = 0; i < recipe.getIngredients().size(); i++) {
        Ingredient recIngredient = recipe.getIngredients().get(i).getIngredient();
        // Check if the user's inventory contains this ingredient
        if (appUser.getInventory().getIngredients().get(recIngredient) != null) {
          if (appUser.getInventory().getIngredients().get(recIngredient).getQuantity()
              >= recipe.getIngredients().get(i).getAmount()) {
            matchedIngredients++;
          }
        } else {
          missingIngredientsList.add(recIngredient); // Ingredient is missing
        }
      }
    } else {
      System.out.println("User or inventory is null");
    }
    boughtIngredients.setText(matchedIngredients.toString() /* + " of " + totalRecipeIngredients*/);
    int missingCount =
        totalRecipeIngredients - matchedIngredients;
    this.missingIngredients.setText(String.valueOf(missingCount));
  }
}
