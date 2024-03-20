package ntnu.org.IDATG1005.grp3.controller;

import static ntnu.org.IDATG1005.grp3.application.MainApp.updateAppUser;

import java.io.IOException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.dao.implementations.HouseholdDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.FailedToLoadInventoryException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.interfaces.NewProfileListener;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.HouseholdService;
import ntnu.org.IDATG1005.grp3.service.UserService;

public class CreateProfileController {

  @FXML
  private PasswordField password;

  @FXML
  private TextField username;

  @FXML
  private AnchorPane rootPane;

  private NewProfileListener newProfileListener;

  private final HouseholdService hs = new HouseholdService(new HouseholdDaoImpl());
  private final UserService us = new UserService(new UserDaoImpl());


  @FXML
  void createUser(ActionEvent event) {
    if (username.getText().isEmpty() || password.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Logg inn feil");
      alert.setContentText("Please fill in all fields");
      alert.showAndWait();
      return;
    }

    try {
      Household currentHousehold = MainApp.appUser.getHousehold();
      User newUser = us.registerUser(username.getText(), password.getText());
      newUser.setHousehold(currentHousehold);
      Inventory inventory = new Inventory(new HashMap<>());
      Ingredient ingredient1 = MainApp.appIngredients.get(1);
      inventory.getIngredients().put(ingredient1, new InventoryIngredient(ingredient1, 5.0));
      newUser.setInventory(inventory);
      //currentHousehold.addUser(newUser);
      us.saveUserHousehold(newUser);
      us.saveUserInventory(newUser);
      hs.refreshHouseholdUsers(currentHousehold);

      System.out.println("NÆMEN");
      System.out.println(newUser.getInventory().getIngredients().size());

      updateAppUser(newUser);
      System.out.println(MainApp.appUser.getInventory().getIngredients().size());
    }
    catch (UsernameAlreadyExistsException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Logg inn feil");
      alert.setContentText("Username already exists");
      alert.showAndWait();
    } catch (FailedToLoadInventoryException e) {
      System.out.println("Failed to load inventory");
    }

    if (newProfileListener != null) {
      newProfileListener.onNewProfile();
    }

    rootPane.setVisible(false);
  }


  @FXML
  void exitCreateUser(MouseEvent event) {
    System.out.println("exitCreateUser");
    rootPane.setVisible(false);
  }

  public void setNewProfileListener(NewProfileListener newProfileListener) {
    this.newProfileListener = newProfileListener;
  }

}
