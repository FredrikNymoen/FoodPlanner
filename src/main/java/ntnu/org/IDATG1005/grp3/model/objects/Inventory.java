package ntnu.org.IDATG1005.grp3.model.objects;

import java.util.List;
import java.util.Map;

/**
 * Represents an inventory associated, containing ingredients.
 */
public class Inventory {
  private final Integer inventoryId; // needed to update the right tuple
  private final Map<Ingredient, InventoryIngredient> ingredients;

  /**
   * Constructs an Inventory with a specified identifier and a list of ingredients.
   *
   * @param inventoryId The unique identifier for the inventory.
   * @param ingredients The list of InventoryIngredient objects contained within this inventory.
   */
  public Inventory(Integer inventoryId, Map<Ingredient, InventoryIngredient> ingredients) {
    this.inventoryId = inventoryId;
    this.ingredients = ingredients;
  }

  /**
   * Gets the inventory's unique identifier.
   *
   * @return The inventory's ID.
   */
  public Integer getInventoryId() {
    return inventoryId;
  }

  /**
   * Gets the list of ingredients contained within this inventory.
   *
   * @return The list of InventoryIngredient objects.
   */
  public Map<Ingredient, InventoryIngredient> getIngredients() {
    return ingredients;
  }
}
