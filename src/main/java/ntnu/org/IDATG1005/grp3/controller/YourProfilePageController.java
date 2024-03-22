package ntnu.org.IDATG1005.grp3.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.User;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static ntnu.org.IDATG1005.grp3.application.MainApp.appUser;

public class YourProfilePageController implements Initializable {

    @FXML
    public TextField newUsername;
    @FXML
    private PasswordField newPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void saveChanges() {
        if (newUsername.getText().trim().isEmpty() || newPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Logg inn feil");
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
        } else {
            appUser.setUsername(newUsername.getText());
            appUser.setPassword(newPassword.getText());
            Household household = appUser.getHousehold();
            if (household != null) {
                household.addUser(appUser);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Changes saved");
            alert.setContentText("Your changes have been saved");
            alert.showAndWait();

        }
    }
    }
