package ntnu.org.IDATG1005.grp3.model.objects;

/**
 * Represents a tag associated with recipes, characterized by its title.
 */
public class Tag {
  private final String title;

  /**
   * Constructs a Tag with a specified title.
   *
   * @param title The title of the tag.
   */
  public Tag(String title) {
    this.title = title;
  }

  /**
   * Retrieves the title of the tag.
   *
   * @return The tag's title.
   */
  public String getTitle() {
    return title;
  }
}
