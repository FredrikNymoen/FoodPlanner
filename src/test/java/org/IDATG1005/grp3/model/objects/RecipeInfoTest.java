package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeInfoTest {
  private RecipeInfo recipeInfo;

  @BeforeEach
  void setUp() {
    recipeInfo = new RecipeInfo("Spaghetti Carbonara", "imageUrl", 30, "Easy", 4.5f, "A classic Italian dish.");
  }

  @Test
  void getTitle() {
    // testing getTitle
    assertEquals("Spaghetti Carbonara", recipeInfo.getTitle());
  }

  @Test
  void getImageUrl() {
    // testing getImageUrl
    assertEquals("imageUrl", recipeInfo.getImageUrl());
  }

  @Test
  void getCookTime() {
    // testing getCookTime
    assertEquals(Integer.valueOf(30), recipeInfo.getCookTime());
  }

  @Test
  void getDifficulty() {
    // testing getDifficulty
    assertEquals("Easy", recipeInfo.getDifficulty());
  }

  @Test
  void getRating() {
    // testing getRating
    assertEquals(Float.valueOf(4.5f), recipeInfo.getRating());
  }

  @Test
  void getDescription() {
    // testing getDescription
    assertEquals("A classic Italian dish.", recipeInfo.getDescription());
  }
}

