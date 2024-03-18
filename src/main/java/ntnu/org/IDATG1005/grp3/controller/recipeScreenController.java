package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;

import java.io.IOException;
import ntnu.org.IDATG1005.grp3.model.objects.User;

public class recipeScreenController {
    private static recipeScreenController instance;
    @FXML
    private VBox choseRecipeContainer;
    private Button lookRecipe;
    private Button chose;
    private Label starLabel;
    private Text recipeName;
    private ImageView recipeImage;

    private recipeScreenController() {
        intializeRecipeList();
    }

    public static synchronized recipeScreenController getInstance() {
        if (instance == null) {
            instance = new recipeScreenController();
        }
        return instance;
    }

    @FXML
    public void initialiseRecipeScreen(MouseEvent mouseEvent) throws IOException {
        for(Recipe recp : MainApp.appRecipes) {
            AnchorPane recipe;
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/components/choseRecipe.fxml"));
            loader.setController(choseRecipeController.getInstance());
            recipe = loader.load();

             recipeName = (Text) recipe.lookup("#recipeName");
             recipeName.setText(recp.getRecipeInfo().getTitle());

             recipeImage = (ImageView) recipe.lookup("#recipeImage");
             recipeImage.setImage(new Image("/images/Kniv_Gaffel_ikon.png"));

            starLabel = (Label) recipe.lookup("#starLabel");
            chose = (Button) recipe.lookup("#chose");
            lookRecipe = (Button) recipe.lookup("#lookRecipe");
            choseRecipeContainer.getChildren().add(recipe);
            addChoseRecipeButton();
            addRecipeToFavorite();
            lookAtRecipe();
        }
        Stage choseRecipeComponent = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        choseRecipeComponent.close();

    }

    public void addChoseRecipeButton() {
        chose.setOnMouseClicked(
                event -> choseRecipeController.getInstance().choseDisplayedRecipe());
    }

    public void addRecipeToFavorite() {
        starLabel.setOnMouseClicked(
                event -> choseRecipeController.getInstance().toggleFavorite());
    }
    public void lookAtRecipe() {
        lookRecipe.setOnMouseClicked(
                event -> showingRecipe());
    }
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

    public void intializeRecipeList(){
        appUser = new User(1, "test", "test");
    }

}

