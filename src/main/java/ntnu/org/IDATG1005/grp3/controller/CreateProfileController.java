package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;
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
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.interfaces.NewProfileListener;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
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
      currentHousehold.addUser(newUser);
      hs.findHouseholdByJoinCode(currentHousehold.getJoinCode()).addUser(MainApp.appUser);
      us.saveUserHousehold(newUser);

      MainApp.appUser = newUser;
    }
    catch (UsernameAlreadyExistsException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Logg inn feil");
      alert.setContentText("Username already exists");
      alert.showAndWait();
    } catch (HouseholdNotFoundException e) {
      throw new RuntimeException(e);
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


  /*
  private static CreateProfileController instance;
  private TextField username;
  private PasswordField password;
  private HBox profileContainer;
  private ImageView login;

  private CreateProfileController() {

  }

  public static synchronized CreateProfileController getInstance() {
    if (instance == null) {
      instance = new CreateProfileController();
    }
    return instance;
  }

  public void createUser(MouseEvent mouseEvent) throws IOException {

    AnchorPane newUser;
    try {
      boolean isValid = validateUser();
      if (isValid) {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/components/profilePicture.fxml"));
        loader.setController(ProfilePictureController.getInstance());
        newUser = loader.load();

        login = (ImageView) newUser.lookup("#login");

        ((Text) newUser.lookup("#profilePictureName")).setText(username.getText());


        //addLogin();


        User user1 = new User(null, username.getText(), password.getText());
        //JoinCollectiveController.getInstance().getHousehold().addUser(user1);
        //System.out.println(JoinCollectiveController.getInstance().getHousehold().getUsers());
        // todo database
        profileContainer.getChildren().add(newUser);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  public void exitUser(MouseEvent mouseEvent) {
    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
    stage.close();
  }

  public void setProfileContainer(HBox profileContainer) {
    this.profileContainer = profileContainer;
  }

  public TextField getUsername() {
    return username;
  }

  public void setUsername(TextField username) {
    this.username = username;
  }

  public void setPassword(PasswordField password) {
    this.password = password;
  }

  /*public void addLogin() {
    login.setOnMouseClicked(
        event -> YourCollectiveController.getInstance().loginToExistingUser(event));
  }*/

  /*
  public boolean validateUser() {
    if (username.getText().isEmpty() || password.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Logg inn feil");
      alert.setContentText("Please fill in all fields");
      alert.showAndWait();
      return false;
    }
    return true;
  }

   */
}
