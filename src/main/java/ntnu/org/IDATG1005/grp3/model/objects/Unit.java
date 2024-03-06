package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents a unit of measurement for ingredients.
 */
public class Unit {
  private final Integer unitId;
  private String unitName; // TODO: Consider Enum

  public Unit(Integer unitId, String unitName) {
    this.unitId = unitId;
    this.unitName = unitName;
  }

  /**
   * Gets the unique identifier for the unit.
   *
   * @return The unit's ID.
   */
  public Integer getUnitId() {
    return unitId;
  }

  /**
   * Gets the name of the unit.
   *
   * @return The unit's name.
   */
  public String getUnitName() {
    return unitName;
  }

  /**
   * Sets the name of the unit.
   *
   * @param unitName The new name for the unit.
   */
  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }
}
