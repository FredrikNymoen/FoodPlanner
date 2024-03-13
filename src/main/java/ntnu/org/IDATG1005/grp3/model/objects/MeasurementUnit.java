package ntnu.org.IDATG1005.grp3.model.objects;

public enum MeasurementUnit {
  // id must match db
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
