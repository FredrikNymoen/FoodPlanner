package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Encapsulates meta-information about a recipe, including its title, image URL, cook time, difficulty level, rating, and description.
 * This class serves as a comprehensive detail holder for recipes, ensuring all essential information is neatly packaged and accessible.
 */
public class RecipeInfo {

  private final String title;
  private final String imageUrl;
  private final Integer cookTime;
  private final String difficulty;
  private final Float rating;
  private final String description;

  /**
   * Constructs RecipeInfo with specified details.
   *
   * @param title       Title of the recipe.
   * @param imageUrl    URL for the recipe image.
   * @param cookTime    Cooking time required.
   * @param difficulty  Cooking difficulty level.
   * @param rating      Recipe rating.
   * @param description Recipe description.
   */
  public RecipeInfo(String title, String imageUrl, Integer cookTime,
      String difficulty, Float rating, String description) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.cookTime = cookTime;
    this.difficulty = difficulty;
    this.rating = rating;
    this.description = description;
  }


  /**
   * Gets the title of the recipe.
   *
   * @return The recipe title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Gets the image URL of the recipe.
   *
   * @return The image URL.
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Gets the cooking time required for the recipe.
   *
   * @return The cook time as an Integer.
   */
  public Integer getCookTime() {
    return cookTime;
  }

  /**
   * Gets the difficulty level of the recipe.
   *
   * @return The difficulty level.
   */
  public String getDifficulty() {
    return difficulty;
  }

  /**
   * Gets the rating of the recipe.
   *
   * @return The recipe rating as a Float.
   */
  public Float getRating() {
    return rating;
  }

  /**
   * Gets the description of the recipe.
   *
   * @return The description.
   */
  public String getDescription() {
    return description;
  }
}
