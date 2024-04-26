package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.Inventory;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;
import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class InventoryTest {

  private Inventory inventory;
  private Ingredient ingredient;
  private InventoryIngredient inventoryIngredient;

  @BeforeEach
  void setUp() {
    ingredient = new Ingredient(1, "Olive Oil", "imageURL", MeasurementUnit.ML);
    inventoryIngredient = new InventoryIngredient(ingredient, 500.0);
    Map<Ingredient, InventoryIngredient> ingredients = new HashMap<>();
    ingredients.put(ingredient, inventoryIngredient);

    inventory = new Inventory(ingredients);
  }

  @Test
  void getIngredients() {
    Map<Ingredient, InventoryIngredient> ingredients = inventory.getIngredients();
    assertNotNull(ingredients);
    assertFalse(ingredients.isEmpty());
    assertTrue(ingredients.containsKey(ingredient));
    assertEquals(inventoryIngredient, ingredients.get(ingredient));
  }
}

