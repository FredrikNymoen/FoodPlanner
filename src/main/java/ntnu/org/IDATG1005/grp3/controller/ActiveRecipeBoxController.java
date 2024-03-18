package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ntnu.org.IDATG1005.grp3.interfaces.ActiveRecipeRemovalListener;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

public class ActiveRecipeBoxController implements Initializable {
  @FXML
  private Pane activeRecipeBox;

  @FXML
  private Button fjernButton;

  @FXML
  private Button lagdFerdigButton;

  @FXML
  private ImageView recipeImage;

  @FXML
  private Text recipeName;

  private Recipe recipe;
  private ActiveRecipeRemovalListener listener;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    System.out.println("ActiveRecipeBoxController initialized");
  }

  public void setData(Recipe recipe) {
    this.recipe = recipe;
    System.out.println("KJØRER!!");
    recipeName.setText(recipe.getRecipeInfo().getTitle());
    recipeImage.setImage(new Image("/images/Kniv_Gaffel_ikon.png")); //Funker ikke fordi det ikke er lagt inn bilde i databasen
  }


  @FXML
  public void recipeMade() {
    System.out.println("Recipe made!");
  }

  @FXML
  public void removeRecipe() {
    System.out.println("Recipe removed!");
    appUser.getChosenRecipes().remove(recipe);
    //remove this recipe from the
    //ActiveRecipesController activeRecipesController = new ActiveRecipesController();
    //activeRecipesController.displayActiveRecipes();


    //System.out.println(recipe.getParent().getId());
    //recipeHolder.getChildren().remove(recipe.getParent());
    //activeRecipesList.remove(recipe.getParent());
    listener.anActiveRecipeRemoved(recipe);
  }

  public void setRemovalListener(ActiveRecipeRemovalListener listener) {
    this.listener = listener;
  }
}
