package model;

import java.util.ArrayList;


/**
 * This is a class to represent a member within the boat club
 * Its constructor is only called with two parameters, 
 * which also generates a new id.
 *
 * @author Lukas Gunnarsson
 */
public class Member {

  private String firstName;
  private String lastName;
  private Identification id;
  private ArrayList<Boat> boats;

  /**
   * Creates a new member.
   *
   * @param firstName is the firstname of the new member
   * @param lastName is the lastname of the new member
   */
  public Member(String firstName, String lastName) { // To Do : Boat Details 
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = new Identification(); // Identification constructor generates id
    this.boats = new ArrayList<Boat>();
  }

  /**
   * Change the firstname.
   *
   * @param firstName is the firstname provided by user
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Change the lastname.
   *
   * @param lastName is the lastname provided by user
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * This method uses setFirstName() and setLastName() to reduce the calls from two to one.
   *
   * @param firstName is the firstname provided by user
   * @param lastName is the lastname provided by user
   * @return true if name was set successfully, else false
   */
  public boolean setFullName(String firstName, String lastName) {
    try {
      this.firstName = firstName;
      this.lastName = lastName;
      return true;
    } catch (Exception e) {   // If set name fails, return false
      return false;
    }
  }

  /**
   * Get fullname.
   *
   * @return member firstname followed by lastname
   */
  public String getName() {
    return this.firstName + " " + this.lastName;
  }

  /**
   * Get firstname.
   *
   * @return firstname of the member
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Get lastname of member.
   *
   * @return the lastname of the member
   */
  public String getLastName() {
    return lastName;
  }

  public int countBoats() {
    int k = 0;
    for (Boat b : boats) {
      k++;
    }
    return k;
  }

  /**
   * Get the identification of the member.
   *
   * @return the identification of the member
   */
  public String getIdentification() {
    return this.id.showId();
  }

  /**
   * Creates a new identification for this member.
   */
  public boolean newId() {
    return this.id.newIdentification();
  }

  /**
   * Get boat from member.
   *
   * @return a specific boat if the member owns any
   */
  public Boat getBoat(int index) {
    if (countBoats() <= 0) {
      return null;
    } else {
      return boats.get(index - 1);
    }
  }

  /**
   * Get all boats that the member owns.
   *
   * @return boats that is in member possession
   */
  public ArrayList<Boat> getBoats() {
    return boats;
  }

  /**
   * Adds a boat to be owned by this member.
   *
   * @param boat that is to be owned by this member.
   */
  public void addBoat(Boat boat) {
    boats.add(boat);
  }

  /**
   * Remove boat from member. 
   *
   * @param boat to be removed
   * @return true if boat was removed, else false
   */
  public boolean removeBoat(Boat boat) {
    try {
      boats.remove(boat);
      return true;
  } catch (Exception e) {
      return false;
  }
  }
  


  /**
   * Creates a new boat for member. 
   *
   * @param type of boat
   * @param length of the boat
   * @return true if the boat was created, else false
   */
  public boolean newBoat(Boat.BoatType type, int length) {
    try {
      Boat boat = new Boat(type, length);
      addBoat(boat);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  


  /**
   * Get all information about a member.
   *
   * @return member information in a string
   */
  public String getInformation() {
    StringBuffer buf = new StringBuffer();
    buf.append("\nMember: " + this.firstName + " " + this.lastName);
    buf.append("\nMember ID: " + this.getIdentification() + "\n");
    int boatCount = 0;
    for (Boat boat : boats) {
      boatCount++;
      buf.append("Boat nr: " + boatCount + ". " + "Type: " + boat.getTypeString());
      buf.append("\nLength: " + boat.getLength() + " m\n");
    }
    if (boatCount == 0) {
      buf.append("Does not own any boats at the moment");
    }
    return buf.toString();
  }
}