package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Household;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HouseholdTest {

  private Household household;

  @BeforeEach
  void setUp() {
    household = new Household(1, "The o's", "JoinCode");
  }

  @Test
  void getHouseholdId() {
    assertEquals(Integer.valueOf(1), household.getHouseholdId());
  }

  @Test
  void getName() {
    assertEquals("The o's", household.getName());
  }

  @Test
  void getJoinCode() {
    assertEquals("JoinCode", household.getJoinCode());
  }

  @Test
  void getUsersInitiallyEmpty() {
    assertTrue(household.getUsers().isEmpty());
  }

  @Test
  void setName() {
    household.setName("The js");
    assertEquals("The js", household.getName());
  }

  @Test
  void setJoinCode() {
    household.setJoinCode("NewJoinCode456");
    assertEquals("NewJoinCode456", household.getJoinCode());
  }
}

