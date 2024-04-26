package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents a step in a recipe.
 */
public class Direction {
  private final Integer directionStep;
  private final String directionDescription;

  public Direction(Integer directionStep, String direction) {
    this.directionStep = directionStep;
    this.directionDescription = direction;
  }

  /**
   * Gets the step number of this direction in the recipe.
   *
   * @return The direction step number.
   */
  public Integer getDirectionStep() {
    return directionStep;
  }

  /**
   * Gets the text of the direction.
   *
   * @return The direction text.
   */
  public String getDirection() {
    return directionDescription;
  }
}
