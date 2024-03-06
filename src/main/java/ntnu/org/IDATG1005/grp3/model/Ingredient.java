package ntnu.org.IDATG1005.grp3.model;

/**
 * Represents an ingredient with a unique ID, name, and image URL.
 */
public class Ingredient {
  private final Integer ingredientId;
  private String name;
  private String imageUrl;

  public Ingredient(Integer ingredientId, String name, String imageUrl) {
    this.ingredientId = ingredientId;
    this.name = name;
    this.imageUrl = imageUrl;
  }

  public Integer getIngredientId() {
    return ingredientId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
