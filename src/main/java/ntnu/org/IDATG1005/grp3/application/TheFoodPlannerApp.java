package ntnu.org.IDATG1005.grp3.application;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TheFoodPlannerApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource("/fxmls/aSimpleTest.fxml")));
    primaryStage.setTitle("Application Title");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}