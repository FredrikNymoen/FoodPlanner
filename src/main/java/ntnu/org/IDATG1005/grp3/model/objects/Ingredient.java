package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents an ingredient with ID, name, image URL, and measurement unit.
 */
public class Ingredient {
  private final Integer ingredientId;
  private final String name;
  private final String imageUrl;
  private final MeasurementUnit unit;

  /**
   * Initializes a new Ingredient.
   *
   * @param ingredientId Unique ID.
   * @param name Name of the ingredient.
   * @param imageUrl Image URL.
   * @param unit Measurement unit.
   */
  public Ingredient(Integer ingredientId, String name, String imageUrl, MeasurementUnit unit) {
    this.ingredientId = ingredientId;
    this.name = name;
    this.imageUrl = imageUrl;
    this.unit = unit;
  }

  /**
   * Returns the ingredient ID.
   *
   * @return Ingredient ID.
   */
  public Integer getIngredientId() {
    return ingredientId;
  }

  /**
   * Returns the ingredient name.
   *
   * @return Name of the ingredient.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the image URL.
   *
   * @return Image URL of the ingredient.
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Returns the measurement unit.
   *
   * @return Unit of the ingredient.
   */
  public MeasurementUnit getUnit() {
    return unit;
  }

  /**
   * Checks equality based on ingredient ID.
   *
   * @param obj The object to compare.
   * @return True if the same ID, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Ingredient that = (Ingredient) obj;
    return ingredientId.equals(that.ingredientId);
  }

  /**
   * Generates hash code based on ingredient ID.
   *
   * @return Hash code.
   */
  @Override
  public int hashCode() {
    return ingredientId.hashCode();
  }
}
