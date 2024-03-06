package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents an ingredient used in a recipe. Contains ingredient object, its measurement unit, and the quantity used.
 */
public class RecipeIngredient {

  private final Integer recipeIngredientId;
  private final Ingredient ingredient;
  private final Unit unit;
  private Integer amount;

  /**
   * Constructs a RecipeIngredient with specified details.
   *
   * @param recipeIngredientId The unique ID for this recipe ingredient.
   * @param ingredient The ingredient used.
   * @param unit The unit of measurement for the ingredient.
   * @param amount The amount of the ingredient used.
   */
  public RecipeIngredient(Integer recipeIngredientId, Ingredient ingredient, Unit unit, Integer amount) {
    this.recipeIngredientId = recipeIngredientId;
    this.ingredient = ingredient;
    this.unit = unit;
    this.amount = amount;
  }

  // Getter for recipeIngredientId
  public Integer getRecipeIngredientId() {
    return recipeIngredientId;
  }

  // Getter for ingredient
  public Ingredient getIngredient() {
    return ingredient;
  }

  // Getter for unit
  public Unit getUnit() {
    return unit;
  }

  // Getter for amount
  public Integer getAmount() {
    return amount;
  }

  // Setter for amount
  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}
