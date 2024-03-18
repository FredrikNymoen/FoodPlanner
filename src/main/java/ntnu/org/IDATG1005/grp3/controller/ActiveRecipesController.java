package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appRecipes;
import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.Main;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.interfaces.ActiveRecipeRemovalListener;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import ntnu.org.IDATG1005.grp3.model.objects.User;

//Controller for the activeRecipesBox.fxml file
public class ActiveRecipesController implements Initializable, ActiveRecipeRemovalListener {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox recipeHolder;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<Node> activeRecipesList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        System.out.println("ActiveRecipesController initialized");
        displayActiveRecipes();

        //registerMouseClick();
    }

    private void getData() {
        appUser = new User(1, "test", "test");
        appUser.addChosenRecipe(appRecipes.get(0));
        appUser.addChosenRecipe(appRecipes.get(1));
    }

    public void displayActiveRecipes(){
        /*for (int i = 0; i < recipeHolder.getChildren().size(); i++) {
            activeRecipesList.add(recipeHolder.getChildren().get(i));
        }*/
        //remove all recipes from the recipeHolder
        recipeHolder.getChildren().clear();

        System.out.println(appUser.getChosenRecipes().size());
        for (int i = 0; i < appUser.getChosenRecipes().size(); i++) {
            addActiveRecipe(appUser.getChosenRecipes().get(i));
        }
    }

    /*public void registerMouseClick() {

        rootPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Node clickedTarget = (Node) event.getTarget();
            //System.out.println("stor Box: Target: " + clickedTarget.getId() + " Type: " + clickedTarget.getClass().getName());
            //System.out.println(clickedTarget.getId());

            if (clickedTarget.getId().equals("addRecipeButton")) {
                addActiveRecipe(MainApp.appRecipes.get(0));
            }
            if (clickedTarget.getId().equals("lagdFerdigButton")) {
                //recipeMade();
            }
            if (clickedTarget.getId().equals("fjernButton")) {
                //removeRecipe(clickedTarget);
            }
        });
    }*/

    public void addActiveRecipe(Recipe recipe) {
        //Pane recipePane = null;
            try { //Legg også til at den endrer bilde og tekst ut ifra retten
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/components/activeRecipesBox.fxml"));

                Pane pane = loader.load();
                ActiveRecipeBoxController controller = loader.getController();
                controller.setData(recipe);
                controller.setRemovalListener(this);

                recipeHolder.getChildren().add(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        //System.out.println(recipeHolder.getChildren());
    }

    @Override
    public void anActiveRecipeRemoved(Recipe recipe) {
        displayActiveRecipes();
    }

    //Iterer gjennom inventory og fjern ingredienser som er brukt i oppskriften





}
