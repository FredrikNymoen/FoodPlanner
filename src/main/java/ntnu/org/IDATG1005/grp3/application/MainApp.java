package ntnu.org.IDATG1005.grp3.application;

import java.util.Objects;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ntnu.org.IDATG1005.grp3.controller.ItemController;
import ntnu.org.IDATG1005.grp3.controller.MarketController;

public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/fxml/views/ingredience.fxml"));
    loader.setController(MarketController.getInstance());
    Parent root = loader.load();

    primaryStage.setTitle("Application Title");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void maino(String[] args) {
    launch(args);}

}