package model;

import java.util.ArrayList;

/**
 * The Register is a place to manipulate data of members.
 * It holds information and can alter it.
 */
public class Register implements Persistance {

  private ArrayList<Member> registry;

  /** Constructor, creates a new arraylist and assigns it. */
  public Register() {
    this.registry = new ArrayList<Member>();
  }

  /** Registers name entered as a new member.*/
  public void registerUser(String firstName, String lastName) {
    // Registers user
    Member newMember = new Member(firstName, lastName); // Creates new Member
    addMember(newMember);
  }

  /** Adds a member. */
  public void addMember(Member member) {
    // Adds a member to the arraylist
    registry.add(member);
  }

  /** Deletes a member. */
  public void deleteMember(String firstName, String lastName) {
    // Removes a member from the arraylist
    Member member = getMember(firstName, lastName);
    registry.remove(member);
  }

  /**  Changes the name for a member. */
  public void changeName(Member member, String firstName, String lastName) {
    member.setFullName(firstName, lastName);
  }
  
  /**
   * Gets member if name matches a registered member. 
   *
   * @param firstName of the member to be found
   * @param lastName of the member to be found
   * @return member if the name matches, if not return null
   */
  public Member getMember(String firstName, String lastName) {
    String first = "";
    String last = "";
    String currentName = "";
    String fullName = firstName + " " + lastName;
    for (Member m : registry) {
      first = m.getFirstName();
      last = m.getLastName();
      currentName = first + " " + last;
      if (currentName.equals(fullName)) {
        return m;
      }
    }
    return null;
  }
  
  /**
   * Gets member identification. 
   *
   * @param member to get the identification from
   * @return a string that holds the unique combination
   */
  public String getMemberId(Member member) {
    return member.getIdentification().showId();
  }
  
  /** Gets all members.
   *
   *@return the arraylist which contains all members
   */
  public ArrayList<Member> getMembers() {
    return registry;
  }

  /**
   * Creates a new member identification and assigns it. 
   *
   * @param member to have a new identification assigned
   */
  public void newMemberId(Member member) {
    member.newId();
    System.out.println("- NEW ID -"); // Debug
  }
  
  /**
   * Get detailed data from a specific member if the name matches. 
   *
   * @param firstName of the member to get information about
   * @param lastName of the member to get information about
   * @return a string with data about the member if the name matches, if not, return null
   */
  public String getMemberDetailed(String firstName, String lastName) {
    String fullName = firstName + " " + lastName;
    for (Member m : registry) {
      String first = m.getFirstName();
      String last = m.getLastName();
      String currentName = first + " " + last;
      if (currentName.equals(fullName)) {
        String data = getMemberData(m);
        return data;
      }
    }
    return null;
  }
  
  /**
   * Gets member data. 
   *
   * @param member to get data from
   * @return data of the member
   */
  public String getMemberData(Member member) {
    String boatData = "";
    for (Boat b : member.getBoats()) {
      boatData += b.getType() + " " + b.getLength() + "\n";
    }
    if (boatData.equals("")) {
      boatData = "Member does not own any boats";
    }
    String memberData = member.getFirstName() + " " + member.getLastName() + "\n" + boatData;
    return memberData;
  }

  /**
   * Gets the names of all members that are registered.
   * This is done by adding all the names to a string.
   *
   * @return the String which contains all the members names
   */
  public String getMembersCompact() {
    String members = "";
    for (Member m : registry) {
      members += m.getFirstName() + " " + m.getLastName() + "\n";
    }
    return members;
  }

  /**
   * Gets the name and boat details about every member. 
   *
   * @return a string that contains all information about every registered member
   */
  public String getMembersDetailed() {
    String members = "";
    for (Member m : registry) {
      members += m.getFirstName() + " " + m.getLastName() + " | ID = " + m.getIdentification().showId() + "\n";
    }
    return members;
  }

  /**
   * Saving to future database.
   */
  @Override
  public void save() {
    // Later implemented
    
  }

  /**
   * To load data when app starting.
   */
  @Override
  public void load() {
    // Later implemented
    
  }

}