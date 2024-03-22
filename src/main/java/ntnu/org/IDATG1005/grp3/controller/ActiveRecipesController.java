package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appRecipes;
import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

import java.net.URL;
import java.util.List;
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
import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions;
import ntnu.org.IDATG1005.grp3.interfaces.*;
import ntnu.org.IDATG1005.grp3.model.objects.*;

import java.io.IOException;
import java.util.ArrayList;

import ntnu.org.IDATG1005.grp3.service.UserService;

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

    private final UserService us = new UserService(new UserDaoImpl());


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //getData();
        System.out.println("ActiveRecipesController initialized");
        displayActiveRecipes();
    }

   /* private void getData() {
        appUser = new User(1, "test", "test");
        appUser.addChosenRecipe(appRecipes.get(0));
        appUser.addChosenRecipe(appRecipes.get(1));
    }*/

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
        /*boolean hasIngredients = checkIfUserHasIngredients(recipe);
        hasIngredients = false; // MÅ FJERNES

         */



        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/components/Popup_BuyShoppingList.fxml"));

            AnchorPane anchorPane = loader.load();
            Popup_buyShoppingListController controller = loader.getController();
            System.out.println("Recipe.getBeenBought(): " + recipe.getBeenBought());
            controller.setData(recipe, recipe.getBeenBought());
            controller.setActiveRecipePopupBuyMakeListener(this);
            popupHolder.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (recipe.getBeenBought()) {
            //Dette er samme kode som i popop_buyShoppinglistController
            List<String> inventoryNames = appUser.getInventory().getIngredients().keySet().stream().map(Ingredient::getName).toList();
            double newQuantity;
            for (RecipeIngredient recIngredient : recipe.getIngredients()) {
                if (inventoryNames.contains(recIngredient.getIngredient().getName())) {
                    newQuantity = appUser.getInventory().getIngredients().get(recIngredient.getIngredient()).getQuantity() - recIngredient.getAmount();
                    appUser.getInventory().getIngredients().get(recIngredient.getIngredient()).setQuantity(newQuantity);
                    System.out.println("Removed " + recIngredient.getIngredient().getName() + " from inventory");
                }
            }


            us.saveChosenRecipes(appUser);
            try {
                us.saveUserInventory(appUser);
            } catch (UserExceptions.FailedToLoadInventoryException e) {
                System.out.println("Failed to save inventory");
            }

            recipe.setBeenBought(false);
            appUser.getChosenRecipes().remove(recipe);
            us.saveChosenRecipes(appUser);
            displayActiveRecipes();
        } else {
            //open popup to buy ingredients
            //openPopup(recipe);
        }
    }

    /*private boolean checkIfUserHasIngredients(Recipe recipe) {
        //Iterer gjennom inventory og sjekk om brukeren har alle ingrediensene som trengs for å lage oppskriften
        boolean returnValue = false;
        try{
            //HER KAN DET VÆRE FEIL
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                if (appUser.getInventory().getIngredients().containsKey(recipe.getIngredients().get(i))) {
                    returnValue = true;
                } else {
                    returnValue = false;
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("User has no ingredients");
        }

        System.out.println("User has ingredients: " + returnValue);
        return returnValue;
    }*/

    @Override
    public void anActiveRecipeRemoved(Recipe recipe) {
        us.saveChosenRecipes(appUser);
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
