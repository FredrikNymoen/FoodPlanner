package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

  @Test
  void testDirectionInitializationAndRetrieval() {
    Integer stepNumber = 1;
    String description = "Chop the vegetables.";

    Direction direction = new Direction(stepNumber, description);

    assertEquals(stepNumber, direction.getDirectionStep());
    assertEquals(description, direction.getDirection());
  }
}
