package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents an ingredient used in a recipe, detailing the ingredient, its measurement unit,
 * and the amount used. Each recipe ingredient is uniquely identified by a recipeIngredientId.
 */
public class RecipeIngredient extends AbstractIngredientDetail {
  private Integer amount;

  /**
   * Constructs a RecipeIngredient with specified details including its unique ID, the ingredient
   * used, the unit of measurement, and the amount of the ingredient.
   *
   * @param ingredient         The ingredient object used in the recipe.
   * @param unit               The unit of measurement for the ingredient.
   * @param amount             The amount of the ingredient used in the recipe.
   */
  public RecipeIngredient(Ingredient ingredient, MeasurementUnit unit, Integer amount) {
    super(ingredient, unit);
    this.amount = amount;
  }

  /**
   * Gets the amount of the ingredient used in the recipe.
   *
   * @return The amount of the ingredient.
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * Sets the amount of the ingredient used in the recipe.
   *
   * @param amount The new amount of the ingredient to be used.
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}
