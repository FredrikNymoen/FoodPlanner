package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Abstract class representing common details of ingredient usage,
 * such as in recipes or inventory, including the ingredient, its unit, and quantity management.
 */
public abstract class AbstractIngredientDetail {

  protected final Ingredient ingredient;

  protected AbstractIngredientDetail(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  /**
   * Gets the ingredient.
   *
   * @return The ingredient.
   */
  public Ingredient getIngredient() {
    return ingredient;
  }
}
