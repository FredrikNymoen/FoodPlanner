package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

  @Test
  void ingredientConstructorAndGetters() {
    Ingredient ingredient = new Ingredient(1, "Flour", "imageUrlFlour", MeasurementUnit.GRAM);

    assertEquals(Integer.valueOf(1), ingredient.getIngredientId());
    assertEquals("Flour", ingredient.getName());
    assertEquals("imageUrlFlour", ingredient.getImageUrl());
    assertEquals(MeasurementUnit.GRAM, ingredient.getUnit());
  }

  @Test
  void ingredientEquality() {
    Ingredient ingredient1 = new Ingredient(1, "Sugar", "imageUrlSugar", MeasurementUnit.GRAM);
    Ingredient ingredient2 = new Ingredient(1, "Sugar", "imageUrlSugar", MeasurementUnit.GRAM);
    Ingredient ingredient3 = new Ingredient(2, "Salt", "imageUrlSalt", MeasurementUnit.GRAM);

    assertEquals(ingredient1, ingredient2, "Ingredients with the same ID should be equal");
    assertNotEquals(ingredient1, ingredient3, "Ingredients with different IDs should not be equal");
  }

  @Test
  void ingredientHashCode() {
    Ingredient ingredient1 = new Ingredient(1, "Butter", "imageUrlButter", MeasurementUnit.GRAM);
    Ingredient ingredient2 = new Ingredient(1, "Butter", "imageUrlButter", MeasurementUnit.GRAM);

    assertEquals(ingredient1.hashCode(), ingredient2.hashCode(), "Hash codes of ingredients with the same ID should match");
  }
}
