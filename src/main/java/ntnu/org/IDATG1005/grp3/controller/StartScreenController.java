package ntnu.org.IDATG1005.grp3.controller;

import java.io.IOException;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ntnu.org.IDATG1005.grp3.application.MainApp;
import ntnu.org.IDATG1005.grp3.dao.implementations.HouseholdDaoImpl;
import ntnu.org.IDATG1005.grp3.dao.implementations.UserDaoImpl;
import ntnu.org.IDATG1005.grp3.exception.db.HouseholdExceptions.HouseholdNotFoundException;
import ntnu.org.IDATG1005.grp3.exception.db.UserExceptions.UsernameAlreadyExistsException;
import ntnu.org.IDATG1005.grp3.model.objects.Household;
import ntnu.org.IDATG1005.grp3.model.objects.User;
import ntnu.org.IDATG1005.grp3.service.HouseholdService;
import ntnu.org.IDATG1005.grp3.service.UserService;

public class StartScreenController {
  @FXML
  private TextField collectiveCodeField;

  private final HouseholdService hs = new HouseholdService(new HouseholdDaoImpl());
  private final UserService us = new UserService(new UserDaoImpl());


  public void createTemporaryUser(){
    try{
      MainApp.appUser = us.registerUser(generateRandomString(8),generateRandomString(8));
    }
    catch (UsernameAlreadyExistsException e) {
      createTemporaryUser();
    }
  }

  @FXML
  void joinCollective(ActionEvent event) {
    if (collectiveCodeField.getText().isEmpty()) {
      collectiveCodeField.setStyle("-fx-border-color: red");
      return;
    }

    try{
      if (hs.findHouseholdByJoinCode(collectiveCodeField.getText()) != null) {
        createTemporaryUser();
        Household h = hs.findHouseholdByJoinCode(collectiveCodeField.getText());
        MainApp.appUser.setHousehold(h);
        us.saveUserHousehold(MainApp.appUser);

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/views/yourCollectivePage.fxml"));

        Parent root = loader.load();
        Stage yourCollective = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        yourCollective.setScene(scene);
        yourCollective.show();
      }
    }catch (HouseholdNotFoundException e) {
      collectiveCodeField.setStyle("-fx-border-color: red");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  /*public void updateUser() {
    try {
      try{
        MainApp.appUser = us.registerUser(generateRandomString(8),generateRandomString(8));
      }
      catch (UsernameAlreadyExistsException e) {
        updateUser();
      }

      Household h = hs.findHouseholdByJoinCode(collectiveCodeField.getText());

      h.addUser(MainApp.appUser);
      MainApp.appUser.setHousehold(h);

      us.saveUserHousehold(MainApp.appUser);

    } catch (HouseholdNotFoundException e) {
      e.printStackTrace();
    }
  }*/

  public String generateRandomString(int length) {
    String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
    }
    return sb.toString();
  }

  @FXML
  void register(ActionEvent event) {
    try {
      createTemporaryUser();
      registerCollective();

      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/fxml/views/yourCollectivePage.fxml"));

      Parent root = loader.load();
      Stage yourCollective = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      yourCollective.setScene(scene);
      yourCollective.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void registerCollective(){
    Household h = hs.createHousehold();
    h.addUser(MainApp.appUser);
    MainApp.appUser.setHousehold(h);
    h.setJoinCode(generateRandomString(8));
    hs.updateHouseholdDetails(h);
    us.saveUserHousehold(MainApp.appUser);
  }


}
