package ntnu.org.IDATG1005.grp3.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

  public static User appUser = null; // set in login / register controller
  public final static List<Ingredient> appIngredients = new IngredientService(new IngredientDaoImpl()).findAllIngredients();
  public static List<Recipe> appRecipes = new RecipeService(new RecipeDaoImpl()).findAllRecipes();

  @Override
  public void start(Stage primaryStage) throws Exception {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/views/activeRecipes.fxml"));

    Parent root = loader.load();

    primaryStage.setTitle("Application Title");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void maino(String[] args) {
    launch(args);}

}