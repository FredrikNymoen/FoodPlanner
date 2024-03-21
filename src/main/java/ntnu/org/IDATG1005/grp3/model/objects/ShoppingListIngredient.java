package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents an item in a shopping list, encapsulating an ingredient along with its required quantity.
 * Inherits common properties from AbstractIngredientDetail, such as ingredient details.
 */
public class ShoppingListIngredient extends AbstractIngredientDetail {

  private Double quantity;

  /**
   * Constructs a ShoppingListIngredient instance with a specified ingredient and its required quantity.
   * This class is used to manage items in a shopping list, including tracking how much of each ingredient is needed.
   *
   * @param ingredient The Ingredient object this shopping list item refers to.
   * @param quantity The quantity of the ingredient needed, expressed in the ingredient's measurement unit.
   */
  public ShoppingListIngredient(Ingredient ingredient, double quantity) {
    super(ingredient);
    this.quantity = quantity;
  }

  /**
   * Retrieves the required quantity of the ingredient for the shopping list.
   *
   * @return The quantity of the ingredient needed.
   */
  public Double getQuantity() {
    return quantity;
  }

  /**
   * Sets the required quantity of the ingredient for the shopping list.
   *
   * @param quantity The new quantity of the ingredient needed.
   */
  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  /**
   * Adjusts the required quantity of the ingredient for the shopping list by a specified amount.
   * This method allows for both incrementing and decrementing the quantity as needed.
   *
   * @param quantity The amount to adjust the ingredient's quantity by. This value can be negative to indicate a reduction.
   */
  public void addQuantity(Double quantity) {
    this.quantity += quantity;
  }

  /**
   * Retrieves the measurement unit of the ingredient, derived from the associated Ingredient object.
   *
   * @return The measurement unit of the ingredient.
   */
  public MeasurementUnit getUnit() {
    return ingredient.getUnit();
  }
}
