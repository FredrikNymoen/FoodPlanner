package ntnu.org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;

/**
 * Represents an ingredient in the inventory, including quantity and unit.
 */
public class InventoryIngredient {
  private final Ingredient ingredient;

  // unit here
  private Integer quantity;

  public InventoryIngredient(Integer inventoryIngredientId, Integer inventoryId, Integer ingredientId, Integer unitId, Double quantity) {
    this.unitId = unitId;
    this.quantity = quantity;
  }

  // Getters and setters...
}
