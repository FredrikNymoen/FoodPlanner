package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import ntnu.org.IDATG1005.grp3.model.objects.Recipe;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeInfo;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class RecipeTest {

  private Recipe recipe;
  private RecipeInfo recipeInfo;

  @BeforeEach
  void setUp() {
    recipeInfo = new RecipeInfo("Spaghetti Carbonara", "imageUrl", 30, "Easy", 4.5f, "A classic Italian dish.");
    RecipeIngredient ingredient = new RecipeIngredient(new Ingredient(1, "Spaghetti", "imageUrl", MeasurementUnit.GRAM), 500.0);
    Direction direction = new Direction(1, "Boil water and cook spaghetti.");
    Tag tag = new Tag("Italian");

    recipe = new Recipe(recipeInfo, Arrays.asList(ingredient), Arrays.asList(direction), Arrays.asList(tag), 2, 1, 1);
  }

  @Test
  void getRecipeInfo() {
    assertEquals(recipeInfo, recipe.getRecipeInfo());
  }

  @Test
  void getIngredients() {
    assertFalse(recipe.getIngredients().isEmpty());
    assertEquals(1, recipe.getIngredients().size());
  }

  @Test
  void getDirections() {
    assertFalse(recipe.getDirections().isEmpty());
    assertEquals(1, recipe.getDirections().size());
  }

  @Test
  void getTags() {
    assertFalse(recipe.getTags().isEmpty());
    assertEquals(1, recipe.getTags().size());
  }

  @Test
  void getAndSetPersonsAdults() {
    assertEquals(2, recipe.getPersonsAdults());
    recipe.setPersonsAdults(3);
    assertEquals(3, recipe.getPersonsAdults());
  }

  @Test
  void getAndSetPersonsChildren() {
    assertEquals(1, recipe.getPersonsChildren());
    recipe.setPersonsChildren(2);
    assertEquals(2, recipe.getPersonsChildren());
  }

  @Test
  void changeAndGetFavoriteStatus() {
    assertFalse(recipe.getFavoriteStatus());
    recipe.changeFavoriteStatus();
    assertTrue(recipe.getFavoriteStatus());
  }

  @Test
  void getRecipeId() {
    assertEquals(1, recipe.getRecipeId());
  }

  @Test
  void setAndGetBeenBought() {
    assertFalse(recipe.getBeenBought());
    recipe.setBeenBought(true);
    assertTrue(recipe.getBeenBought());
  }
}

