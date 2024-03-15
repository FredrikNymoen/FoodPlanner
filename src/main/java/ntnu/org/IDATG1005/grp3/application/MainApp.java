package ntnu.org.IDATG1005.grp3.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.controller.joinCollectiveController;

public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/fxml/views/joinCollectivePage.fxml"));
    loader.setController(joinCollectiveController.getInstance());
    Parent root = loader.load();

    primaryStage.setTitle("Application Title");
    primaryStage.setScene(new Scene(root));

    primaryStage.show();
  }

  public static void maino(String[] args) {
    launch(args);}

}