package ntnu.org.IDATG1005.grp3.model.objects;

import java.util.List;

/**
 * Represents an inventory associated, containing ingredients.
 */
public class Inventory {
  private final Integer inventoryId;
  private List<InventoryIngredient> ingredients;

  public Inventory(Integer inventoryId, List<InventoryIngredient> ingredients) {
    this.inventoryId = inventoryId;
    this.ingredients = ingredients;
  }

  public Integer getInventoryId() {
    return inventoryId;
  }

  public List<InventoryIngredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<InventoryIngredient> ingredients) {
    this.ingredients = ingredients;
  }
}
