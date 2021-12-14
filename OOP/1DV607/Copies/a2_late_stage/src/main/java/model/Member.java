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
  private MemberID id;
  private ArrayList < Boat > boats;

  /**
   * Creates a new member.
   *
   * @param firstName is the firstname of the new member
   * @param lastName is the lastname of the new member
   */
  public Member(String firstName, String lastName) { // To Do : Boat Details 
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = new MemberID(); // MemberID constructor generates id
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
   */

  /**
   *
   * @param firstName is the firstname provided by user
   * @param lastName is the lastname provided by user
   */
  public void setFullName(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
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


  /**
   * Get the identification of the member.
   *
   * @return the identification of the member
   */
  public MemberID getMemberID() {
    return this.id;
  }

  /**
   * Creates a new identification for this member.
   */
  public void newID() {
    this.id = new MemberID();
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
  public void newBoat(Boat boat) {
    boats.add(boat);
  }
}