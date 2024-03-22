package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.ShoppingListIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingListIngredientTest {

  private ShoppingListIngredient shoppingListIngredient;
  private Ingredient ingredient;

  @BeforeEach
  void setUp() {
    ingredient = new Ingredient(1, "Carrot", "imageURL", MeasurementUnit.STK);
    shoppingListIngredient = new ShoppingListIngredient(ingredient, 1.0);
  }

  @Test
  void getQuantity() {
    // testing initial quantity set in setUp
    assertEquals(1.0, shoppingListIngredient.getQuantity());
  }

  @Test
  void setQuantity() {
    // testing setQuantity
    shoppingListIngredient.setQuantity(2.0);
    assertEquals(2.0, shoppingListIngredient.getQuantity());
  }

  @Test
  void addQuantity() {
    // testing addQuantity with positive value
    shoppingListIngredient.addQuantity(1.0);
    assertEquals(2.0, shoppingListIngredient.getQuantity());

    // testing addQuantity with negative value
    shoppingListIngredient.addQuantity(-0.5);
    assertEquals(1.5, shoppingListIngredient.getQuantity());
  }

  @Test
  void getUnit() {
    // testing getUnit, ensures it matches the ingredient's unit
    assertEquals(MeasurementUnit.STK, shoppingListIngredient.getUnit());
  }
}

