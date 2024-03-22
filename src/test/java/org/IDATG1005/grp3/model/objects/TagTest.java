package org.IDATG1005.grp3.model.objects;

import ntnu.org.IDATG1005.grp3.model.objects.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TagTest {

  @Test
  void getTitle() {
    // creating a tag with a specific title
    Tag tag = new Tag("Healthy");
    // testing getTitle
    assertEquals("Healthy", tag.getTitle());
  }
}

