package model;

import java.util.UUID;

/**
 * Class for identification.
 * Unique ID's are generated and assigned.
 */

public class Identification {
  
  private UUID id;
  private String stringId;

  /**
   * Constructor that also generates an unique identification.
   */
  public Identification() {
    newIdentification();
  }

  /**
   * Generates an unique ID.
   */
  public boolean newIdentification() {
    try {
      id = UUID.randomUUID();   
      String newId = id.toString();
      this.stringId = newId.substring(0, 6);  // 6 Characters were allowed
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String getId() {
    return stringId;
  }

  /**
   * Gets the ID in string format. 
   *
   * @return the ID in string format
   */
  public String showId() {
    return stringId;
  }
}