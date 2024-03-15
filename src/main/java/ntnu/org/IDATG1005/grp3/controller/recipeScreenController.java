package ntnu.org.IDATG1005.grp3.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

import java.io.IOException;

public class recipeScreenController {
    private List<Recipe> recipes;
    private static recipeScreenController instance;
    @FXML
    private VBox choseRecipeContainer;
    private Button chose;
    private Label starLabel;
    private recipeScreenController() {

    }
    public static synchronized recipeScreenController getInstance() {
        if (instance == null) {
        instance = new recipeScreenController();
        }
        return instance;
    }
    @FXML
    public void initialiseRecipeScreen(MouseEvent mouseEvent) throws IOException {
            AnchorPane recipe;
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/components/choseRecipe.fxml"));
            loader.setController(choseRecipeController.getInstance());
            recipe = loader.load();
            starLabel = (Label) recipe.lookup("#starLabel");
            chose = (Button) recipe.lookup("#chose");
            choseRecipeContainer.getChildren().add(recipe);
            addChoseRecipeButton();
            addRecipeToFavorite();
        Stage choseRecipeComponent = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        choseRecipeComponent.close();

    }
    public void addChoseRecipeButton(){
        chose.setOnMouseClicked(
            event -> choseRecipeController.getInstance().choseDisplayedRecipe());
    }
    public void addRecipeToFavorite(){
        starLabel.setOnMouseClicked(
            event -> choseRecipeController.getInstance().toggleFavorite());
    }

}
