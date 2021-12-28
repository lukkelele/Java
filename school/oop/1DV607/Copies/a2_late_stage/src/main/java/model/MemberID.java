package model;

import java.util.Random;
/**
 * @author Lukas Gunnarsson
 * This class is used to generate unique ID's for members
 * 
 */

public class MemberID {


  private String ID;

  public MemberID() {
    /**
     * Creates new MemberId
     */
    generateID();
  }


  public MemberID getID() {
    /**
     * @return ID
     */
    return this;
  }

  public String showID() {
    /**
     * @return string of characters that together make up the ID
     */
    return ID;
  }

  // Fix for unique
  public void generateID() { 
    /**
     * GENERATES RANDOM ID - To Do: Add Unique element as discussed in journal.md
     */
    Random rand = new Random();
    String allowedCharacters = allowedCharacters();
    int len_allowedCharacters = allowedCharacters.length();
    int length = 6;
    char[] newID = new char[length];
    for (int k = 0; k < length; k++) {
      newID[k] = allowedCharacters.charAt(rand.nextInt(len_allowedCharacters));
    }
    String ID = String.valueOf(newID);
    this.ID = ID;
  }


  private String allowedCharacters() {
    /**
     * alphanumeric allowed
     * @return 
     */

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