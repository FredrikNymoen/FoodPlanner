package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ntnu.org.IDATG1005.grp3.interfaces.ActiveRecipePopupBuyMake;
import ntnu.org.IDATG1005.grp3.interfaces.ActiveRecipePopupChangeShoppingListListener;
import ntnu.org.IDATG1005.grp3.interfaces.CloseActiveRecipePopupListener;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;

import java.net.URL;
import java.util.ResourceBundle;

public class Popup_buyShoppingListController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text overskrift;

    @FXML
    private Text beskjed;

    private CloseActiveRecipePopupListener listener1;
    private ActiveRecipePopupBuyMake listener2;
    private ActiveRecipePopupChangeShoppingListListener listener3;

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
        listener2.buyAndMake();
    }

    @FXML
    public void changeShoppingList() {
        listener3.editShoppingList();
    }

    @FXML
    public void exit() {
        listener1.exitPopup();
    }

    public void setCloseActiveRecipePopupListener(CloseActiveRecipePopupListener listener) {
        this.listener1 = listener;
    }

    public void setActiveRecipePopupBuyMakeListener(ActiveRecipePopupBuyMake listener) {
        this.listener2 = listener;
    }

    public void setActiveRecipePopupChangeShoppingListListener(ActiveRecipePopupChangeShoppingListListener listener) {
        this.listener3 = listener;
    }
}
