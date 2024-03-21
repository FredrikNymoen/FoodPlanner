package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Defines an ingredient's amount in a recipe.
 */
public class RecipeIngredient extends AbstractIngredientDetail {

  private Integer amount;

  /**
   * Initializes with ingredient and amount.
   *
   * @param ingredient The ingredient.
   * @param amount     Ingredient amount for the recipe.
   */
  public RecipeIngredient(Ingredient ingredient, Integer amount) {
    super(ingredient);
    this.amount = amount;
  }

  /**
   * Returns ingredient amount.
   *
   * @return Ingredient amount.
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * Updates ingredient amount.
   *
   * @param amount New ingredient amount.
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  /**
   * Returns ingredient's unit of measure.
   *
   * @return Measurement unit.
   */
  public MeasurementUnit getUnit() {
    return ingredient.getUnit();
  }
}