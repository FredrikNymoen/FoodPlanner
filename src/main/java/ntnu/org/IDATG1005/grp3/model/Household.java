package ntnu.org.IDATG1005.grp3.model;

import java.util.ArrayList;

public class Household {

  private final Integer householdId;
  private String name;
  private String joinCode;
  private ArrayList<User> users;

  public Household(String name, String joinCode, int householdId) {
    this.householdId = householdId;
    this.name = name;
    this.joinCode = joinCode;
    users = new ArrayList<>();
  }

  // Getters
  public Integer getHouseholdId() {
    return householdId;
  }

  public String getName() {
    return name;
  }

  public String getJoinCode() {
    return joinCode;
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setJoinCode(String joinCode) {
    this.joinCode = joinCode;
  }

  public void addUser(User u) {
    users.add(u);
  }

  public void removeUser(User u) {
    users.remove(u);
  }
}