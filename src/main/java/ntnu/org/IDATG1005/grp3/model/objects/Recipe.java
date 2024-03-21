package ntnu.org.IDATG1005.grp3.model.objects;

import java.util.List;

/**
 * Represent a recipe including meta-information, ingredients, cooking directions, and tags,
 * alongside proportions adults and children. It encapsulates the essence of a recipe for presentation and
 * preparation guidance.
 */
public class Recipe {
  private boolean favorite = false;

  private final Integer recipeId;
  private final RecipeInfo recipeInfo;
  private final List<RecipeIngredient> ingredients;
  private final List<Direction> directions;
  private final List<Tag> tags;
  private Integer personsAdults;
  private Integer personsChildren;

  private boolean beenBought = false;

  /**
   * Constructs a Recipe with specified details.
   *
   * @param recipeInfo      Object containing recipe meta-information.
   * @param ingredients     List of ingredients required for the recipe.
   * @param directions      List of cooking directions.
   * @param tags            List of tags associated with the recipe.
   * @param personsAdults   Number of Adults recorded for base ingredients.
   * @param personsChildren Number of Children recorded for base ingredients.
   * @param recipeId        RecipeId id matching the id in the db.
   */
  public Recipe(RecipeInfo recipeInfo, List<RecipeIngredient> ingredients,
      List<Direction> directions, List<Tag> tags, Integer personsAdults, Integer personsChildren, Integer recipeId) {
    this.recipeInfo = recipeInfo;
    this.ingredients = ingredients;
    this.directions = directions;
    this.tags = tags;
    this.personsAdults = personsAdults;
    this.personsChildren = personsChildren;
    this.recipeId = recipeId;
  }

  /**
   * Gets the recipe information.
   *
   * @return The RecipeInfo object containing meta-information.
   */
  public RecipeInfo getRecipeInfo() {
    return recipeInfo;
  }

  /**
   * Gets the list of ingredients.
   *
   * @return The list of RecipeIngredient objects.
   */
  public List<RecipeIngredient> getIngredients() {
    return ingredients;
  }

  /**
   * Gets the list of directions.
   *
   * @return The list of Direction objects.
   */
  public List<Direction> getDirections() {
    return directions;
  }

  /**
   * Gets the list of tags.
   *
   * @return The list of Tag objects.
   */
  public List<Tag> getTags() {
    return tags;
  }

  /**
   * Gets the number of adults served.
   *
   * @return The number of adults.
   */
  public Integer getPersonsAdults() {
    return personsAdults;
  }

  /**
   * Sets the number of adults served.
   *
   * @param personsAdults The new number of adults served.
   */
  public void setPersonsAdults(Integer personsAdults) {
    this.personsAdults = personsAdults;
  }

  /**
   * Gets the number of children served.
   *
   * @return The number of children.
   */
  public Integer getPersonsChildren() {
    return personsChildren;
  }

  /**
   * Sets the number of children served.
   *
   * @param personsChildren The new number of children served.
   */
  public void setPersonsChildren(Integer personsChildren) {
    this.personsChildren = personsChildren;
  }
  public void changeFavoriteStatus(){
    this.favorite = !this.favorite;
  }
  public boolean getFavoriteStatus(){
    return this.favorite;
  }

  public Integer getRecipeId(){
    return recipeId;
  }

  public void changeBoughtStatus() {
    this.beenBought = !this.beenBought;
  }

  public boolean getBeenBought() {
    return this.beenBought;
  }

}
