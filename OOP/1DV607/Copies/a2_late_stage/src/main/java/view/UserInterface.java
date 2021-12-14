package view;

import java.util.Scanner;

/**
 * All visuals are done via this class.
 * All input is done via this class.
 *
 * @author Lukas Gunnarsson
 */

public class UserInterface {    // 
  
  Scanner input = new Scanner(System.in);
  
  /**
   * Constructor
   * To be used by controller.User.
   */
  public UserInterface() {
  }


  /**
   * Display a specific member and their information. 
   */
  public void displayMemberDetails(String memberData) {
    System.out.println("===================================");
    System.out.println("|          MEMBER DETAILS         |");
    System.out.println("===================================");
    System.out.println(memberData);
  }


  /**
   * Header for when searching for a member.
   */
  public void searchMemberMessage() {
    System.out.println("===================================");
    System.out.println("|        SEARCH FOR A MEMBER      |");
    System.out.println("===================================");
  }

  /**
   * To visualize a menu in the console.
   */
  public void displaySearchFirstName() {
    searchMemberMessage();
    System.out.println("| FIRSTNAME:                      |");
  }

  /**
   * To visualize a menu in the console.
   */
  public void displaySearchLastName() {
    searchMemberMessage();
    System.out.println("| LASTNAME:                       |");
  }

  /**
   * Displays the menu and takes input to decide what to do next by returning an integer.
   * NOTE: Since the Controller is supposed to manipulate the data, I might move the conversion part.
   *
   * @return an integer that has been converted from a string by parseInt()
   */
  public int promptMenu() {
    displayMenu();
    String choice = getStringInput();
    int convertedString = Integer.parseInt(choice);     // ERROR HANDLING
    return convertedString;
  }

  /**
   * To reduce duplicate code, and getting inputs is a repeating process.
   *
   * @return a string provided by the user
   */
  public String getStringInput() {
    System.out.print("INPUT: ");
    String s = input.next();
    return s;
  }

  /**
   * Displays a quit message to the user.
   */
  public void continueMessage() {
    System.out.println("=========================================");
    System.out.println("IF YOU WISH TO EXIT , PRESS Q \nPRESS ANY OTHER KEY TO CONTINUE");
    System.out.println("=========================================");
  }

  /**
   * Displays a header followed by an instruction.
   */
  public void newMemberForm() {
    System.out.println("| A NEW MEMBER!");
    System.out.println("| Enter firstname followed by lastname");
  }

  /**
   * Displays the header for the application.
   * It appears on top of the menu at all times.
   */
  public void displayMenu() {
    System.out.println("===================================");
    System.out.println("|        CRUD-CODILE DUNDEE V1    |");
    System.out.println("===================================");
    displayMenuChoices();
  }

  /**
   * Displays the menu options.
   */
  public void displayMenuChoices() {
    System.out.println("| 1.  New member (Registration)   |");
    System.out.println("| 2.  View all members            |");
    System.out.println("| 3.  Search member               |");
    System.out.println("| 4.  Update member info          |");
    System.out.println("===================================");
    System.out.println("| Enter a number!                 |");
  }

  /**
   * Might delete this, early implementation inspired from Tobias DiceGame example.
   *
   * @return a character
   */
  public int getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (Exception e) {
      System.out.println("ERROR: " + e);
    }
    return 0;
  }


  public void displayMembers(String members) {
    System.out.println(members);
  }

  // MEMBER 

  /**
   * Displays the whole menu, with the application header on top.
   */
  public void displayMemberMenu() {
    displayMenu();
    System.out.println("| 1.  Edit name                   |");
    System.out.println("| 2.  Generate new ID             |");
    System.out.println("| 3.  Delete                      |");
    System.out.println("===================================");
    System.out.println("| Enter a number!                 |");
  }

  /**
   * Display question if the user wishes to edit the name or not.
   */
  public void editMemberNameQuestion() {
    System.out.println("Edit name:\n(1) yes\n(2) no");
  }

  /**
   * Takes input from user to change a members name.
   * The old name is displayed to clarify which user the change is going to be applied to.
   *
   * @param firstName is the members current firstname
   * @param lastName is the members current lastname
   * @return a new name for the member
   */
  // Needs error handling
  public String editName(String firstName, String lastName) { 
    System.out.println("Old name:\n" + firstName + " " + lastName + "\n");
    String name = "";
    name = getStringInput();
    return name;
  }

  /**
   * WORKING ON IT.
   */
  public void newNamePrompt() {
    editMemberNameQuestion();

  }

  /**
   * Displays a message of a successful registration.
   */
  public void memberRegisteredMessage() {
    System.out.println("Member is registered.");
  }


  /**
   * Displays a question if the user wants to generate a new ID for the member or not.
   */
  public void questionEditMemberId() {
    System.out.println("Generate new ID:\n(1) yes\n(2) no");
  }

  /**
   * TO REMOVE ?? Dont overcomplicate things...!!!!
   *
   * @return a combination of characters that could resemble an ID of a member
   */
  public String enterMemberId() {
    String MemberId = getStringInput();
    return MemberId;
  }

  /**
   * Displays a message of an successful new identification assigned to the member.
   */
  public void newMemberIdMessage() {
    System.out.println("New ID was successfully generated and assigned, replacing the old one.");
  }

  /**
   * Displays a message that the generation of a new identification was cancelled.
   */
  public void newMemberIdCancelledMessage() {
    System.out.println("A new ID was NOT generated.");
  }

  // Member Delete

  /**
   * Displays a question if the user wishes to delete a member (resigning their membership, 
   * since deleting anyone except themselves is not allowed).
   */
  public void deleteMemberQuestion() {
    System.out.println("Delete member:\n(1) yes\n(2) no");
  }

  /**
   * Displays a message saying that the member was deleted.
   */
  public void memberDeletedMessage() {
    System.out.println("Member was successfully deleted.");
  }

  /**
   * Displays a message saying that the member deletion was cancelled.
   */
  public void memberDeletionCanceledMessage() {
    System.out.println("Member deletion cancelled.");
  }

  /**
   * Displays a member menu which holds all options that the user can choose from.
   */
  public void displayUpdateMenu() {
    displayMenu();
    System.out.println("| 1.  Specific boat               |");
    System.out.println("| 2.  Member details              |");
    System.out.println("| 3.  Add new boat                |");
    System.out.println("===================================");
    System.out.println("| Enter a number!                 |");
  }
  
  /**
   * Displays a menu regarding boats.
   * The menu is targeting a single boat at a time.
   */
  public void displayBoatMenu() {
    displayMenu();
    System.out.println("| 1.  Edit type                   |");
    System.out.println("| 2.  Edit length                 |");
    System.out.println("| 3.  Delete                      |");
    System.out.println("===================================");
    System.out.println("| Enter a number!                 |");
  }

  /**
   * Prompt message asking if the user wishes to edit the type of the boat or not.
   */
  public void editTypeMessage() {
    System.out.println("Edit type of boat:\n(1) yes\n(2) no");
  }

  /**
   * Prompt message asking if the user wishes to edit the length of the boat or not.
   */
  public void editLengthMessage() {
    System.out.println("Edit length of boat:\n(1) yes\n(2) no");
  }

  /**
   * Prompt message asking if the user wishes to delete the boat or not.
   */  
  public void deleteBoatMessage() {
    System.out.println("Delete boat:\n(1) yes\n(2) no");
  }


}