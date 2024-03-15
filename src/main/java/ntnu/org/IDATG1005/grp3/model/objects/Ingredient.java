package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents a base ingredient with a unique ID, name, and image URL.
 */
public class Ingredient {
  private final Integer ingredientId;
  private final String name;
  private String imageUrl;

  /**
   * Constructs an Ingredient with specified ID, name, and image URL.
   *
   * @param ingredientId The unique identifier for the ingredient.
   * @param name The name of the ingredient.
   * @param imageUrl The URL of the image associated with the ingredient.
   */
  public Ingredient(Integer ingredientId, String name, String imageUrl) {
    this.ingredientId = ingredientId;
    this.name = name;
    this.imageUrl = imageUrl;
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

  public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
}
