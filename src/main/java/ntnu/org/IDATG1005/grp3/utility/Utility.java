package ntnu.org.IDATG1005.grp3.utility;

import java.util.Random;

public class Utility {

  private Utility () {

  }

  private static final Random random = new Random();

  public static String generateRandomJoinCode(int length) {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    StringBuilder result = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int index = random.nextInt(characters.length());
      result.append(characters.charAt(index));
    }
    return result.toString();
  }

  public static String generateRandomName(String base, int postfixLength) {
    if (postfixLength < 1) {
      throw new IllegalArgumentException("postfixLength must be at least 1");
    }
    int lowerBound = (int) Math.pow(10, (double) postfixLength - 1);
    int number = lowerBound + random.nextInt(9 * lowerBound);
    return base + number;
  }
}