package ntnu.org.IDATG1005.grp3.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

public class ShowingRecipeController implements Initializable {

    @FXML
    private Text recipeIngredientsText;
    @FXML
    private Text recipeDescriptionTest;
    @FXML
    private Label recipeTitle;
    private static ShowingRecipeController instance;

    public ShowingRecipeController() {

    }

    public static synchronized ShowingRecipeController getInstance() {
        if (instance == null) {
        instance = new ShowingRecipeController();
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

    }

    public void btnExitShowingRecipe(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setData(Recipe recipe){
        recipeTitle.setText(recipe.getRecipeInfo().getTitle());
        StringBuilder ingredients = new StringBuilder();
        for(int i = 0; i < recipe.getIngredients().size(); i++){
            ingredients.append(recipe.getIngredients().get(i).getIngredient().getName());
            ingredients.append(" ");
            ingredients.append(recipe.getIngredients().get(i).getAmount());
            ingredients.append("\n");
        }
        recipeIngredientsText.setText(ingredients.toString());
        StringBuilder description = new StringBuilder();
        for(int i = 0; i < recipe.getDirections().size(); i++){
            description.append(recipe.getDirections().get(i).getDirection());
            ingredients.append(" ");
            description.append("\n");
        }
        recipeDescriptionTest.setText(description.toString());
    }

}
