package ntnu.org.IDATG1005.grp3.application;

import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.dao.implementations.IngredientDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.RecipeDaoImpl;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.IngredientService;
import ntnu.org.IDATG1005.grp3.service.RecipeService;

public class MainApp extends Application {

  public static User appUser = null; // set in login / register controller
  public final static List<Ingredient> appIngredients = new IngredientService(new IngredientDaoImpl()).findAllIngredients();
  public static List<Recipe> appRecipes = new RecipeService(new RecipeDaoImpl()).findAllRecipes();

  public static Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    MainApp.primaryStage = primaryStage;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/joinCollectivePage.fxml"));

    Parent root = loader.load();

    primaryStage.setTitle("Mat planleggeren");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void maino(String[] args) {
    launch(args);}
}