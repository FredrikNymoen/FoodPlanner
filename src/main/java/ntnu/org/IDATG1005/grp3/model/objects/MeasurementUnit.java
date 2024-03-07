package ntnu.org.IDATG1005.grp3.model.objects;

public enum MeasurementUnit {
  GRAM(1, "g"),
  GLASS(8, "glass"),
  PINCH(7, "klype"),
  ML(2, "ml"),
  MM(3, "mm"),
  PK(9, "pk"),
  SS(4, "ss"),
  STK(6, "stk"),
  TS(5, "ts"),
  LITER(10, "L");

  private final int id;
  private final String unitName;

  MeasurementUnit(int id, String unitName) {
    this.id = id;
    this.unitName = unitName;
  }

  public int getId() {
    return id;
  }

  public String getUnitName() {
    return unitName;
  }

  public static MeasurementUnit fromId(int id) {
    for (MeasurementUnit unit : values()) {
      if (unit.getId() == id) {
        return unit;
      }
    }
    throw new IllegalArgumentException("No measurement unit with id: " + id);
  }

  public static MeasurementUnit fromUnitName(String unitName) {
    for (MeasurementUnit unit : values()) {
      if (unit.getUnitName().equalsIgnoreCase(unitName)) {
        return unit;
      }
    }
    throw new IllegalArgumentException("No measurement unit with name: " + unitName);
  }

  @Override
  public String toString() {
    return unitName;
  }
}
