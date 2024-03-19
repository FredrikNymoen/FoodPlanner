package ntnu.org.IDATG1005.grp3.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    private ActiveRecipePopupBuyMake listener1;

    private Recipe recipe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Popup_buyShoppingListController initialized");
    }


    public void setData(Recipe recipe) {
        this.recipe = recipe;
        overskrift.setText(recipe.getRecipeInfo().getTitle());
        beskjed.setText("For å lage " + recipe.getRecipeInfo().getTitle() + ", må du ha kjøpt handlelista først");
    }

    @FXML
    public void buyAndMake() {
        appUser.getShoppingCartRecipes().remove(recipe);
        appUser.getChosenRecipes().remove(recipe);
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
