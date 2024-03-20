package ntnu.org.IDATG1005.grp3.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ntnu.org.IDATG1005.grp3.interfaces.ActiveRecipeMadeListener;
import ntnu.org.IDATG1005.grp3.interfaces.ActiveRecipePopupBuyMake;
import ntnu.org.IDATG1005.grp3.interfaces.ActiveRecipePopupChangeShoppingListListener;
import ntnu.org.IDATG1005.grp3.interfaces.CloseActiveRecipePopupListener;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

import java.net.URL;
import java.util.ResourceBundle;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

public class Popup_buyShoppingListController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text overskrift;

    @FXML
    private Text beskjed;

    @FXML
    private HBox buyAndMakeButton;

    private ActiveRecipePopupBuyMake listener1;

    private Recipe recipe;

    private boolean hasIngredients;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Popup_buyShoppingListController initialized");
    }


    public void setData(Recipe recipe, boolean hasIngredients) {
        this.recipe = recipe;
        this.hasIngredients = hasIngredients;
        overskrift.setText(recipe.getRecipeInfo().getTitle());

        if(hasIngredients) {
            //set text color to green
            beskjed.setFill(javafx.scene.paint.Color.GREEN);
            beskjed.setText("Ingrediensen har blitt lagd! \nIngrediensene som brukes i retten er blitt fjernet fra 'Ingredienser', og retten har blitt fjernet fra 'Aktive oppskrifter' ");
            buyAndMakeButton.setVisible(false);
        } else {
            beskjed.setFill(javafx.scene.paint.Color.RED);
            beskjed.setText("For å lage " + recipe.getRecipeInfo().getTitle() + ", må du ha kjøpt handlelista først");
            buyAndMakeButton.setVisible(true);
        }
    }

    @FXML
    public void buyAndMake() {

        if(!hasIngredients) {
            //Iterer gjennom hvilke ingredienser brukeren har, legg til de ingrediensene som mangler, også fjern alle ingrediensene som brukes i retten

            //HER KAN DET VÆRE FEIL
            System.out.println("Shopping cart: " + appUser.getShoppingList());
            for(int i = 0; i < recipe.getIngredients().size(); i++) {
                if(!appUser.getShoppingList().contains(recipe.getIngredients().get(i))) {
                    appUser.getShoppingList().remove(recipe.getIngredients().get(i));
                }
            }
            appUser.getShoppingCartRecipes().remove(recipe);
            appUser.getChosenRecipes().remove(recipe);
        }
        listener1.buyAndMake(recipe);
    }

    @FXML
    public void changeShoppingList() {
        System.out.println("Change shopping list");
    }

    @FXML
    public void exit() {
        rootPane.setVisible(false);
    }



    public void setActiveRecipePopupBuyMakeListener(ActiveRecipePopupBuyMake listener) {
        this.listener1 = listener;
    }

}
