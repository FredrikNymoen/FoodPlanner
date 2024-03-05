package ntnu.org.IDATG1005.grp3.application;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(
        Objects.requireNonNull(getClass().getResource(
            "/fxml/views/join_collective_pg.fxml")));
    primaryStage.setTitle("Application Title");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);}

}