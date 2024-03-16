package ntnu.org.IDATG1005.grp3.model.objects;

import java.util.Map;

/**
 * Represents an inventory associated, containing ingredients.
 */
public class Inventory {
  private final Map<Ingredient, InventoryIngredient> ingredients;

  /**
   * Constructs an Inventory with a specified identifier and a list of ingredients.
   *
   * @param ingredients The list of InventoryIngredient objects contained within this inventory.
   */
  public Inventory(Map<Ingredient, InventoryIngredient> ingredients) {
    this.ingredients = ingredients;
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
