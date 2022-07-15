package model;

import java.util.ArrayList;




/**
 * The Register is a place to manipulate data of members.
 * It holds information and can alter it.
 */
public class Register implements Persistance {

  private ArrayList<Member> members;
  private ArrayList<String> ids;
  private ArrayList<Boat.BoatType> boatTypes;

  /** Constructor, creates a new arraylist and assigns it.
   * Some hard coded data is included.
   */
  public Register() {
    this.members = new ArrayList<>();
    this.boatTypes = new ArrayList<>();
    this.ids = new ArrayList<>();

    addBoatTypes();

    // HARD CODED DATA
    Member one = createMember("Lukas", "Gunnarsson");
    one.addBoat(new Boat(Boat.BoatType.canoe, 20));
    Member two = createMember("Lucanos", "Gun");
    two.addBoat(new Boat(Boat.BoatType.cayak, 10));
    Member three = createMember("Lukke", "Kjellman");
    three.addBoat(new Boat(Boat.BoatType.sailboat, 5));
    three.addBoat(new Boat(Boat.BoatType.motorboat, 24));
    addMember(one);
    addMember(two);
    addMember(three);
    // HARD CODED DATA END
  } 

  /**
   * Adds types of boat to arraylist.
   * 
   */
  private void addBoatTypes() {
    for (Boat.BoatType b : Boat.BoatType.values() ) {
      boatTypes.add(b);
    }
  }

  /**
   * Registers name entered as a new member.
   * Mostly used for hard coded data for debugging purposes. 
   * registerMember is used otherwise.
   *
   * @param firstName is the firstname of the member to be registered 
   * @param lastName is the lastname of the member to be registered 
   * @return a new member
   */
  public Member createMember(String firstName, String lastName) {
    // Registers user
    Member newMember = new Member(firstName, lastName); // Creates new Member
    return newMember;
  }


  /** Registers name entered as a new member.
   *
   * @param firstName is the firstname of the member to be registered 
   * @param lastName is the lastname of the member to be registered 
   * @return true if member was created , else false
   */
  public boolean registerMember(String firstName, String lastName) {
    // Registers user
    try {
      Member newMember = new Member(firstName, lastName); // Creates new Member

      for (Member m : members) {              // Check if ID exists, if it does, make another one.
        while (m.getIdentification().equals(newMember.getIdentification())) {
          newMember.newId();
        }
      }
      addMember(newMember);
      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /** Adds a member.
   *
   */
  private void addMember(Member member) {
    // Adds a member to the arraylist
    members.add(member);
    ids.add(member.getIdentification());
  }

  /** Deletes a member. 
   *
   */
  public boolean deleteMember(String firstName, String lastName) {
    try {
      Member member = getMember(firstName, lastName);
      members.remove(member);
      return true;
      } catch (Exception e) {
        return false;
      }
    }


  /** Deletes a member. 
   *
   */
  public boolean deleteMember(String name) {
    // Removes a member from the arraylist
    try {
    Member member = getMember(name);
    members.remove(member);
    return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Get boat type. 
   *
   * @param index is the index of the boat type to return
   * @return the boat type which is chosen by index
   */
  public Boat.BoatType getBoatType(int index) {
    if (index <= 0 || index > boatTypes.size()) {   // In case type index that is entered is not legitimate
      index = 5;        // If not legit, set type to index 5 which correlates to boat type OTHER 
    }
    return boatTypes.get(index - 1);
  }

  /**
   * Changes name on a member.
   *
   * @param member the member which should get their name changed
   * @param firstName new firstname to override current firstname
   * @param lastName new lastname to overrride current lastname
   */
  public boolean changeName(Member member, String firstName, String lastName) {
    return member.setFullName(firstName, lastName);
  }

  
  /**
   * Gets member if name matches a registered member. 
   *
   * @param firstName of the member to be found
   * @param lastName of the member to be found
   * @return member if the name matches, if not return null
   */
  public Member getMember(String firstName, String lastName) {
    String currentName = "";
    String fullName = firstName + " " + lastName;
    for (Member member : members) {
      currentName = member.getName();
      if (currentName.equals(fullName)) {
        return member;
      }
    }
    return null;
  }


  /**
   * Get member. 
   *
   * @param fullName of the member to get
   * @return the member if they exist, else return null
   */
  public Member getMember(String fullName) {
    try {
    String currentName = "";
    for (Member member : members) {
      currentName = member.getName();
      if (currentName.equals(fullName)) {
        return member;
      }
    }
    } catch (NullPointerException e) {
      return null;
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
    return member.getIdentification();
  }

  /**
   * Creates a new member identification and assigns it. 
   *
   * @param member to have a new identification assigned
   */
  public boolean newMemberId(Member member) {
    try {
      return member.newId();
    } catch (NullPointerException e) {
      return false;                                                     // If member provided doesnt exist in the registry, return false
    }
  }


  /**
   * Get detailed data from a specific member if the name matches. 
   *
   * @param fullName of the member to get information about
   * @return a string with data about the member if the name matches, if not, return null
   */
  public String getMemberDetails(String fullName) {
    for (Member member : members) {
      String currentName = member.getName();
      if (currentName.equals(fullName)) {
        String data = member.getInformation();
        return data;
      }
    }
    return null;
  }
  
  /**
   * Gets the names of all members that are registered.
   * This is done by adding all the names to a string.
   * IN A LIST.
   *
   * @return the String which contains all the members names
   */
  public String getMembersCompact() {
    StringBuffer buf = new StringBuffer();
    for (Member member : members) {
      buf.append(member.getName() + "\n");
    }
    return buf.toString();
  }

  /**
   * Gets the name and boat details about every member. 
   * IN A LIST.
   *
   * @return a string that contains all information about every registered member
   */
  public String getMembersDetailed() {
    StringBuffer buf = new StringBuffer();
    for (Member member : members) {
      buf.append(member.getInformation() + "\n");
    }
    return buf.toString();
  }





  // TODO: PERSISTANCE
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