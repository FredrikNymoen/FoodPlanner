package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
//import main.Main;
//import main.MyListener;
//import model.Fruit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;

public class MarketController implements Initializable {


    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Ingredient> ingredients = new ArrayList<>();
    private Image image;

    private List<Ingredient> getData() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient;
        for(int i=0; i<9; i++){
            URL url = getClass().getResource("/images/Kniv_Gaffel_ikon.png");
            ingredient = new Ingredient(0, "Tomat", url.toString());
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("MarketController");
        ingredients.addAll(getData());
        /*if (fruits.size() > 0) {
            setChosenFruit(fruits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Fruit ingredient) {
                    setChosenFruit(ingredient);
                }
            };
        }*/

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < ingredients.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                //URL locationen = getClass().getResource("/fxml/components/ingredient_box.fxml");
                fxmlLoader.setLocation(getClass().getResource("/fxml/components/ingredience_box.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                System.out.println("HEI");
                ItemController itemController = fxmlLoader.getController();
                System.out.println("hei" + itemController);
                itemController.setData(ingredients.get(i));
                System.out.println("HALLA");

                if (column == 4) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(5));
                //Add to grid pane
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
