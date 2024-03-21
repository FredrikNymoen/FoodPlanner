package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import ntnu.org.IDATG1005.grp3.model.objects.RecipeIngredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeIngredientTest {

  private RecipeIngredient recipeIngredient;
  private Ingredient ingredient;

  @BeforeEach
  void setUp() {
    ingredient = new Ingredient(1, "Flour", "imageURL", MeasurementUnit.GRAM);
    recipeIngredient = new RecipeIngredient(ingredient, 500.0);
  }

  @Test
  void getAmount() {
    // testing initial amount set in setUp
    assertEquals(Double.valueOf(500), recipeIngredient.getAmount());
  }

  @Test
  void setAmount() {
    // testing setAmount
    recipeIngredient.setAmount(250.0);
    assertEquals(Double.valueOf(250), recipeIngredient.getAmount());
  }

  @Test
  void getUnit() {
    // testing getUnit, ensures it matches the ingredient's unit
    assertEquals(MeasurementUnit.GRAM, recipeIngredient.getUnit());
  }
}

