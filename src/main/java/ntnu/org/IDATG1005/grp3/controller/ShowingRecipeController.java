package ntnu.org.IDATG1005.grp3.controller;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShowingRecipeController {
    private static ShowingRecipeController instance;

    private ShowingRecipeController() {

    }

    public static synchronized ShowingRecipeController getInstance() {
        if (instance == null) {
        instance = new ShowingRecipeController();
        }
        return instance;
    }
    public void btnExitShowingRecipe(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
