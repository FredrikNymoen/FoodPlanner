package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import ntnu.org.IDATG1005.grp3.interfaces.RecipeChangedListener;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;

import java.io.IOException;
import ntnu.org.IDATG1005.grp3.model.objects.User;


public class recipeScreenController implements Initializable, RecipeChangedListener {
    private static recipeScreenController instance;
    @FXML
    private VBox choseRecipeContainer;
    private Button lookRecipe;
    private Button chose;
    private Label starLabel;
    private Text recipeName;
    private ImageView recipeImage;

    public recipeScreenController() {
        intializeRecipeList();
    }

    public static synchronized recipeScreenController getInstance() {
        if (instance == null) {
            instance = new recipeScreenController();
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        displayRecipes();
    }


    public void displayRecipes(){
        choseRecipeContainer.getChildren().clear();
        for(Recipe recp : MainApp.appRecipes) {
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(
                    getClass().getResource("/fxml/components/choseRecipe.fxml"));
                AnchorPane anchorPane = loader.load();
                choseRecipeController controller = loader.getController();
                controller.setData(recp);
                controller.setRecipeChangedListener(this);

                if(appUser.getChosenRecipes().contains(recp)){
                    controller.changeApperance();
                }

                //controller.changeFavoriteColor();
                choseRecipeContainer.getChildren().add(anchorPane);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //Stage choseRecipeComponent = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        //choseRecipeComponent.close();
    }

    public void intializeRecipeList(){
        appUser = new User(1, "test", "test");
    }

    @Override
    public void onRecipeChanged(Recipe recipe) {
        displayRecipes();
    }
}

