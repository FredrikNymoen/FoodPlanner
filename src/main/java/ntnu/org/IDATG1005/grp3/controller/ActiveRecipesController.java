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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.interfaces.*;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import ntnu.org.IDATG1005.grp3.model.objects.User;

//Controller for the activeRecipesBox.fxml file
public class ActiveRecipesController implements Initializable, ActiveRecipeRemovalListener, ActiveRecipeMadeListener, ActiveRecipePopupBuyMake {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox recipeHolder;
    @FXML
    private Pane popupHolder;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<Node> activeRecipesList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        System.out.println("ActiveRecipesController initialized");
        displayActiveRecipes();
    }

    private void getData() {
        appUser = new User(1, "test", "test");
        appUser.addChosenRecipe(appRecipes.get(0));
        appUser.addChosenRecipe(appRecipes.get(1));
    }

    public void displayActiveRecipes(){
        recipeHolder.getChildren().clear();

        for (int i = 0; i < appUser.getChosenRecipes().size(); i++) {
            addActiveRecipe(appUser.getChosenRecipes().get(i));
        }
    }

    public void addActiveRecipe(Recipe recipe) {
        //Pane recipePane = null;
            try { //Legg også til at den endrer bilde og tekst ut ifra retten
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/components/activeRecipesBox.fxml"));

                Pane pane = loader.load();
                ActiveRecipeBoxController controller = loader.getController();
                controller.setData(recipe);
                controller.setRemovalListener(this);
                controller.setMadeListener(this);

                recipeHolder.getChildren().add(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private void madeRecipe(Recipe recipe) {
        //check if user has the nessaary ingredients
        //if run different methods based on if user has the ingredients or not

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/components/Popup_BuyShoppingList.fxml"));

            AnchorPane anchorPane = loader.load();
            Popup_buyShoppingListController controller = loader.getController();
            controller.setData(recipe);
            //controller.setCloseActiveRecipePopupListener(this);
            controller.setActiveRecipePopupBuyMakeListener(this);
            //controller.setActiveRecipePopupChangeShoppingListListener(this);
            popupHolder.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void anActiveRecipeRemoved(Recipe recipe) {
        displayActiveRecipes();
    }


    @Override
    public void anActiveRecipeMade(Recipe recipe) {
        madeRecipe(recipe);
    }

    @Override
    public void buyAndMake(Recipe recipe) {
        popupHolder.getChildren().clear();
        displayActiveRecipes();
        System.out.println("Pressed buy and make button");
    }

    /*@Override
    public void exitPopup() {
        popupHolder.getChildren().clear();
        System.out.println("Popup closed");
    }



    @Override
    public void editShoppingList() {
        popupHolder.getChildren().clear();
        System.out.println("Pressed edit shopping list button");
    }*/


    //Iterer gjennom inventory og fjern ingredienser som er brukt i oppskriften

}
