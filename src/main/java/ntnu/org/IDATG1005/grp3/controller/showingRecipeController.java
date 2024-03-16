package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class showingRecipeController {
    private static showingRecipeController instance;

    private showingRecipeController() {

    }

    public static synchronized showingRecipeController getInstance() {
        if (instance == null) {
        instance = new showingRecipeController();
        }
        return instance;
    }
    public void btnExitShowingRecipe(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
