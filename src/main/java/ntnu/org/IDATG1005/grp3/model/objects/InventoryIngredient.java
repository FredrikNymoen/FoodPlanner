package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents an ingredient in the inventory, including quantity and unit.
 */
public class InventoryIngredient {
  private final Ingredient ingredient;
  private final Unit unit;
  private Integer quantity;

  public InventoryIngredient(Ingredient ingredient, Unit unit, Integer quantity) {
    this.ingredient = ingredient;
    this.unit = unit;
    this.quantity = quantity;
  }

  /**
   * Gets the ingredient.
   *
   * @return The ingredient.
   */
  public Ingredient getIngredient() {
    return ingredient;
  }

  /**
   * Gets the unit of measurement for the ingredient.
   *
   * @return The unit.
   */
  public Unit getUnit() {
    return unit;
  }

  /**
   * Gets the quantity of the ingredient.
   *
   * @return The quantity.
   */
  public Integer getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the ingredient.
   *
   * @param quantity The new quantity.
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
