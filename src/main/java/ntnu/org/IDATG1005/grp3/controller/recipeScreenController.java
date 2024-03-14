package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class recipeScreenController {
    private static recipeScreenController instance;
    private VBox choseRecipeContainer;
    private recipeScreenController() {

    }
    public static synchronized recipeScreenController getInstance() {
        if (instance == null) {
        instance = new recipeScreenController();
        }
        return instance;
    }
    public void onLoadRecipeScreen(MouseEvent mouseEvent) throws IOException {
        AnchorPane recipe;
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/components/choseRecipe.fxml"));
        loader.setController(recipeScreenController.getInstance());
        recipe = loader.load();
        choseRecipeContainer.getChildren().add(recipe);

        Stage choseRecipeComponent = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        choseRecipeComponent.close();

    }
    public void setChoseRecipeContainer(VBox choseRecipeContainer) {
        this.choseRecipeContainer = choseRecipeContainer;
    }

}
