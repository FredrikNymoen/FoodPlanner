package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents an ingredient in the inventory, detailing the ingredient used, its measurement unit,
 * and the quantity available.
 */
public class InventoryIngredient extends AbstractIngredientDetail {

  private final Integer inventoryIngredientId;
  private Double quantity;
  private boolean favorite;

  /**
   * Constructs an instance of InventoryIngredient with specified ingredient, unit, and quantity.
   *
   * @param inventoryIngredientId The unique ID for this recipe ingredient.
   * @param ingredient            The ingredient object.
   * @param quantity              The quantity of the ingredient in inventory.
   */
  public InventoryIngredient(Integer inventoryIngredientId, Ingredient ingredient,
      Double quantity) {
    super(ingredient);
    this.inventoryIngredientId = inventoryIngredientId;
    this.quantity = quantity;
    this.favorite = false;
  }

  /**
   * Gets the unique ID for this inventory ingredient.
   *
   * @return The unique ID of the inventory ingredient.
   */
  public Integer getInventoryIngredientId() {
    return inventoryIngredientId;
  }

  /**
   * Gets the quantity of the ingredient in the inventory.
   *
   * @return The quantity of the ingredient.
   */
  public Double getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the ingredient in the inventory.
   *
   * @param quantity The new quantity of the ingredient.
   */
  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  /**
   * Updates the quantity of the ingredient in the inventory by adding the specified amount. This
   * can be used to increase or decrease inventory levels.
   *
   * @param quantity The quantity to add to the current inventory level. This value can be negative
   *                 to reduce inventory.
   */
  public void addQuantity(Double quantity) {
    this.quantity += quantity;
  }

  public MeasurementUnit getUnit() {
    return ingredient.getUnit();
  }

  /**
   * Updates the quantity of the ingredient in the inventory by removing the specified amount. This
   * can be used to decrease inventory levels.
   *
   * @param quantity The quantity to remove from the current inventory level.
   */
  public void removeQuantity(Double quantity) {
    this.quantity -= quantity;
  }

  /**
   * Changes the favorite status of the ingredient.
   *
   */
  public void changeFavoriteStatus() {
    this.favorite = !this.favorite;
  }

  /**
   * Gets the favorite status of the ingredient.
   *
   * @return The favorite status of the ingredient.
   */
  public boolean getFavoriteStatus() {
    return favorite;
  }
}
