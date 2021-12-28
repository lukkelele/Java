package model;

import java.util.Random;

/**
 * Class for identification.
 * Unique ID's are generated and assigned.
 * NEEDS TO BE FIXED!
 * A new identification method has to be implemented because of failure within tests.
 */

public class Identification {
  
  private String id;

  /**
   * Constructor that also generates an unique identification.
   */
  public Identification() {
    generateId();
  }

  
  /**
   * Get identification.
   *
   * @return the identification
   */
  public Identification getId() {
    return this;
  }
  
  /**
   * Gets the string of the ID.
   *
   * @return string of characters that together make up the ID
   */
  public String showId() {
    return id;
  }

  /**
   * Generate a unique identification.
   */
  public void generateId() { 
    Random rand = new Random();
    String allowedCharacters = allowedCharacters();
    int len_allowedCharacters = allowedCharacters.length();
    int length = 6;
    char[] newId = new char[length];
    for (int k = 0; k < length; k++) {
      newId[k] = allowedCharacters.charAt(rand.nextInt(len_allowedCharacters));
    }
    String id = String.valueOf(newId);
    this.id = id;
  }

  /**
   * Generates a string of characters that are allowed. 
   *
   * @return a string of allowed characters
   */
  private String allowedCharacters() {
    String s_uppercase = "";
    String s_lowercase = "";
    for (int i = 97; i < 123; i++) {
      s_lowercase += (char) i;
    }
    char[] s_lowercaseArray = s_lowercase.toCharArray();
    for (char c: s_lowercaseArray) {
      char c_uppercase = Character.toUpperCase(c);
      s_uppercase += c_uppercase;
    }
    String nums = "0123456789";
    String all_AllowedCharacters = s_lowercase + s_uppercase + nums;

    return all_AllowedCharacters;
  }
}