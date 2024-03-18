package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.interfaces.RecipeChangedListener;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

public class choseRecipeController {
  private Recipe recipe;
  private static choseRecipeController instance;
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


  public static synchronized choseRecipeController getInstance() {
    if (instance == null) {
      instance = new choseRecipeController();
    }
    return instance;
  }
  public void setData(Recipe recipe){
    recipeName.setText(recipe.getRecipeInfo().getTitle());
    recipeImage.setImage(new Image("/images/Kniv_Gaffel_ikon.png"));
    recipeDescription.setText(recipe.getRecipeInfo().getDescription());
    this.recipe = recipe;
  }
  @FXML
  public void chooseRecipe(){
    if(appUser.getChosenRecipes().contains(recipe)){
      appUser.removeChosenRecipe(recipe);
      chose.setStyle("-fx-background-color: #00b400cc");
      chose.setText("Choose");
    }else {
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
      loader.setController(showingRecipeController.getInstance());
      Parent root = loader.load();

      Stage showingRecipe = new Stage();

      Scene scene = new Scene(root);

      showingRecipe.setScene(scene);
      showingRecipe.show();
      loginToYourProfilePageController.getInstance().setPrimaryStage(showingRecipe);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @FXML
  public void favoriteClick(){
    recipe.changeFavoriteStatus();
    System.out.println("added to fave");
  }

  public void setRecipeChangedListener(RecipeChangedListener recipeChangedListener){
    this.recipeChangedListener = recipeChangedListener;
  }

  /*public void changeFavoriteColor(){
    if(recipe.getFavoriteStatus()){
      starLabel.setText("★");
      choseRecipeBorder.setStyle("-fx-border-color: yellow");
      choseRecipeBorder.setStyle("-fx-border-width: 5");
      starLabel.setTextFill(Color.YELLOW);
    }else{
      starLabel.setText("☆");
      choseRecipeBorder.setStyle("-fx-border-color: black");
      starLabel.setTextFill(Color.BLACK);
    }
  }*/
  public void changeApperance(){
    if(recipe.getFavoriteStatus()) {
      starLabel.setStyle("-fx-text-fill: yellow");
      choseRecipeBorder.setStyle("-fx-border-color: yellow");
      choseRecipeBorder.setStyle("-fx-border-width: 5");
    }
    if(appUser.getChosenRecipes().contains(recipe)){
        appUser.addChosenRecipe(recipe);
        System.out.println(appUser.getChosenRecipes().size());
        chose.setStyle("-fx-background-color: #f00");
        chose.setText("Remove");
    }
  }
}
