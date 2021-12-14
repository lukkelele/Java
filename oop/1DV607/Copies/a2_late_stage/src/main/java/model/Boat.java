package model;

/**
 * Boat class with two properties, type and length.
 *
 * @author Lukas Gunnarsson
 */

public class Boat {

  /**
   * Different kinds of boats.
   */
  public enum BoatType {
    sailboat,
    motorboat,
    cayak,
    canoe,
    other
  }

  private BoatType type;
  private int length;

  
  /**
  * Constructor.
  *
  * @param type of boat to create.
  * @param length of the boat to be created.
  */
  public Boat(BoatType type, int length) {
    this.type = type;
    this.length = length;
  }


  /**
   * To get the length of the boat.
   *
   * @return length of the boat.
   */
  public int getLength() {
    return length;
  }


  /**
   * Method to get the type of boat.
   *
   * @return the type of boat.
   * 
   */
  public BoatType getType() {
    return type;
  }


  /**
   * Set a new length to the boat.
   *
   * @param length is the new size.
   */
  public void setLength(int length) {
    this.length = length;
  }


  /**
   * Change the type of the boat.
   *
   * @param type can be one of the BoatTypes.
   */
  public void setType(BoatType type) {
    this.type = type;
  }
}