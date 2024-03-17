package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

//Controller for the activeRecipes.fxml file
public class ActiveRecipesController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox recipeHolder;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<Node> activeRecipesList = new ArrayList<>();


    public void initialize() {
        System.out.println("ActiveRecipesController initialized");

        for (int i = 0; i < recipeHolder.getChildren().size(); i++) {
            activeRecipesList.add(recipeHolder.getChildren().get(i));
        }

        registerMouseClick();
    }

    public void registerMouseClick() {

        rootPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Node clickedTarget = (Node) event.getTarget();
            System.out.println("stor Box: Target: " + clickedTarget.getId() + " Type: " + clickedTarget.getClass().getName());
            System.out.println(clickedTarget.getId());

            if (clickedTarget.getId().equals("addRecipeButton")) {
                addActiveRecipe();
            }
            if (clickedTarget.getId().equals("lagdFerdigButton")) {
                recipeMade();
            }
            if (clickedTarget.getId().equals("fjernButton")) {
                removeRecipe(clickedTarget);
            }
        });
    }

    public void addActiveRecipe() {
        Pane recipePane = null;
        try {
            recipePane = FXMLLoader.load(getClass().getResource("/fxml/components/active_recipes_display.fxml"));
            int recipeIndex = activeRecipesList.size() + 1;
            recipePane.setId("recipe" + recipeIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        activeRecipesList.add(recipePane);
        recipeHolder.getChildren().add(recipePane);
        System.out.println(recipeHolder.getChildren());
    }

    public void recipeMade() {
        System.out.println("Recipe made!");
    }

    public void removeRecipe(Node recipe) {
        System.out.println("Recipe removed!");
        System.out.println(recipe.getParent().getId());
        recipeHolder.getChildren().remove(recipe.getParent());
        activeRecipesList.remove(recipe.getParent());
    }
}
