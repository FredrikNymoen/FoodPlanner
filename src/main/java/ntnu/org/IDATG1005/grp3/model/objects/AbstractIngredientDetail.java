package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Abstract class representing common details of ingredient usage,
 * such as in recipes or inventory, including the ingredient, its unit, and quantity management.
 */
public abstract class AbstractIngredientDetail {

  protected final Ingredient ingredient;
  protected MeasurementUnit unit;

  protected AbstractIngredientDetail(Ingredient ingredient, MeasurementUnit unit) {
    this.ingredient = ingredient;
    this.unit = unit;
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
  public MeasurementUnit getUnit() {
    return unit;
  }

  /**
   * Sets the unit of measurement for the ingredient.
   *
   * @param unit The new unit of the ingredient.
   */
  public void setUnit(MeasurementUnit unit) {
    this.unit = unit;
  }
}

