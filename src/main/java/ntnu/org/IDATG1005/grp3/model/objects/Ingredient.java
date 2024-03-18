package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents a base ingredient with a unique ID, name, and image URL.
 */
public class Ingredient {
  private final Integer ingredientId;
  private final String name;
  private final String imageUrl;
  private final MeasurementUnit unit;

  /**
   * Constructs an Ingredient with specified ID, name, and image URL.
   *
   * @param ingredientId The unique identifier for the ingredient.
   * @param name The name of the ingredient.
   * @param imageUrl The URL of the image associated with the ingredient.
   */
  public Ingredient(Integer ingredientId, String name, String imageUrl, MeasurementUnit unit) {
    this.ingredientId = ingredientId;
    this.name = name;
    this.imageUrl = imageUrl;
    this.unit = unit;
  }

  /**
   * Gets the ingredient's unique identifier.
   *
   * @return The ingredient's ID.
   */
  public Integer getIngredientId() {
    return ingredientId;
  }

  /**
   * Gets the ingredient's name.
   *
   * @return The name of the ingredient.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the URL of the ingredient's image.
   *
   * @return The image URL associated with the ingredient.
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Gets the unit of the ingredient.
   *
   * @return The measurement of the ingredient.
   */
  public MeasurementUnit getUnit() {
    return unit;
  }

  /*
  Compares an ingredient with an ingredient on db id
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Ingredient that = (Ingredient) obj;
    return ingredientId.equals(that.ingredientId);
  }

  @Override
  public int hashCode() {
    return ingredientId.hashCode();
  }
}
