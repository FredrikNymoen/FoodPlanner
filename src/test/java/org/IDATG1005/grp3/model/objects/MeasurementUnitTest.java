package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.MeasurementUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeasurementUnitTest {

  @Test
  void fromIdValidId() {
    // testing fromId with a valid ID
    assertEquals(MeasurementUnit.GRAM, MeasurementUnit.fromId(1));
    assertEquals(MeasurementUnit.LITER, MeasurementUnit.fromId(10));
  }

  @Test
  void fromIdInvalidId() {
    // testing fromId with an invalid ID
    Exception exception = assertThrows(IllegalArgumentException.class, () -> MeasurementUnit.fromId(-1));
    assertTrue(exception.getMessage().contains("No measurement unit with id: -1"));
  }

  @Test
  void fromUnitNameValidName() {
    // testing fromUnitName with a valid name
    assertEquals(MeasurementUnit.GRAM, MeasurementUnit.fromUnitName("g"));
    assertEquals(MeasurementUnit.LITER, MeasurementUnit.fromUnitName("l"));
  }

  @Test
  void fromUnitNameInvalidName() {
    // testing fromUnitName with an invalid name
    Exception exception = assertThrows(IllegalArgumentException.class, () -> MeasurementUnit.fromUnitName("invalid"));
    assertTrue(exception.getMessage().contains("No measurement unit with name: invalid"));
  }

  @Test
  void toStringTest() {
    // testing toString method
    assertEquals("g", MeasurementUnit.GRAM.toString());
    assertEquals("l", MeasurementUnit.LITER.toString());
  }
}

