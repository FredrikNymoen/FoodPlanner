package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryIngredientTest {

  private InventoryIngredient inventoryIngredient;
  private Ingredient ingredient;

  @BeforeEach
  void setUp() {
    ingredient = new Ingredient(1, "Milk", "imageURL", MeasurementUnit.LITER);
    inventoryIngredient = new InventoryIngredient(ingredient, 2.0);
  }

  @Test
  void getAndSetQuantity() {
    assertEquals(2.0, inventoryIngredient.getQuantity());

    inventoryIngredient.setQuantity(3.0);
    assertEquals(3.0, inventoryIngredient.getQuantity());
  }

  @Test
  void addQuantity() {
    inventoryIngredient.addQuantity(1.0);
    assertEquals(3.0, inventoryIngredient.getQuantity());

    inventoryIngredient.addQuantity(-1.5);
    assertEquals(1.5, inventoryIngredient.getQuantity());
  }

  @Test
  void removeQuantity() {
    inventoryIngredient.removeQuantity(0.5);
    assertEquals(1.5, inventoryIngredient.getQuantity());
  }

  @Test
  void changeAndGetFavoriteStatus() {
    assertFalse(inventoryIngredient.getFavoriteStatus());

    inventoryIngredient.changeFavoriteStatus();
    assertTrue(inventoryIngredient.getFavoriteStatus());
  }

  @Test
  void getUnit() {
    assertEquals(MeasurementUnit.LITER, inventoryIngredient.getUnit());
  }
}
