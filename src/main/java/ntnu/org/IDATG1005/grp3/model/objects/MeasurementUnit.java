package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Enumerates measurement units with corresponding database ID and unit name.
 * Supports conversion from ID or name to enum value.
 */
public enum MeasurementUnit {
  GRAM(1, "g"),
  ML(2, "ml"),
  MM(3, "mm"),
  SS(4, "ss"),
  TS(5, "ts"),
  STK(6, "stk"),
  PINCH(7, "klype"),
  GLASS(8, "glass"),
  PK(9, "pk"),
  LITER(10, "l");

  private final int id;
  private final String unitName;

  /**
   * Initializes the enum value with an ID and a unit name.
   *
   * @param id The database ID of the measurement unit.
   * @param unitName The name of the measurement unit.
   */
  MeasurementUnit(int id, String unitName) {
    this.id = id;
    this.unitName = unitName;
  }

  /**
   * Returns the database ID of the unit.
   *
   * @return The ID of the unit.
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the name of the unit.
   *
   * @return The unit name.
   */
  public String getUnitName() {
    return unitName;
  }

  /**
   * Finds a measurement unit by its database ID.
   *
   * @param id The ID to search for.
   * @return The corresponding MeasurementUnit.
   * @throws IllegalArgumentException If no unit matches the ID.
   */
  public static MeasurementUnit fromId(int id) {
    for (MeasurementUnit unit : values()) {
      if (unit.getId() == id) {
        return unit;
      }
    }
    throw new IllegalArgumentException("No measurement unit with id: " + id);
  }

  /**
   * Finds a measurement unit by its name.
   *
   * @param unitName The name to search for.
   * @return The corresponding MeasurementUnit.
   * @throws IllegalArgumentException If no unit matches the name.
   */
  public static MeasurementUnit fromUnitName(String unitName) {
    for (MeasurementUnit unit : values()) {
      if (unit.getUnitName().equalsIgnoreCase(unitName)) {
        return unit;
      }
    }
    throw new IllegalArgumentException("No measurement unit with name: " + unitName);
  }

  /**
   * Returns the unit name as the string representation.
   *
   * @return The unit name.
   */
  @Override
  public String toString() {
    return unitName;
  }
}
