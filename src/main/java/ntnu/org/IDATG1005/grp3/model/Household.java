package ntnu.org.IDATG1005.grp3.model;

public class Household {
  private String name;
  private String joinCode;

  public Household(String name, String joinCode) {
    this.name = name;
    this.joinCode = joinCode;
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getJoinCode() {
    return joinCode;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setJoinCode(String joinCode) {
    this.joinCode = joinCode;
  }
}