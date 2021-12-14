package controller;

import model.Register;
import view.UserInterface;

/**
 * Regular user class.
 * Can do all regular feats that are not considered admin.
 *
 * @author Lukas Gunnarsson
 * @version 1.0
 */

public class User {

  private UserInterface ui;
  private Register register;

  /**
   * Single constructor.
   *
   * @param register Contains saved members and methods to manipulate the registry
   * @param ui This is used for I/O operations
   */
  public User(Register register, UserInterface ui) {

    this.register = register;
    this.ui = ui;
  }

  /**
   * Method to be looped to run the program continously in console.
   */
  public void mainMenu() {

    int choice = ui.promptMenu();
    switch (choice) {
      case 1:
        registerUser();
        break;
      case 2:
        showList();
        showListDetailed();
        break;
      case 3: // Update member info
        showMemberDetails();
        break;
      case 4:
        update();
        break;
      default:
        break;
    }
  }

  /**
   * Gets all saved members in the register in string format and this is sent to the UI.
   */
  public void showList() {
    String members = register.getMembersCompact();
    ui.displayMembers(members);
  }


  /**
   * Same as showList() but provides a detailed version.
   */
  public void showListDetailed() {

    String members = register.getMembersDetailed();
    ui.displayMembers(members);
  }

  /**
   * Takes input and registers a new member.
   */
  public void registerUser() { // New member
    ui.newMemberForm();
    String[] fullName = getName();
    register.registerUser(fullName[0], fullName[1]); // firstname [0], lastname[1]
  }
  // ERROR HANDLING !!

  /**
   * Searches for a member by the name provided by the user.
   * If none is found, null is returned.
   *
   * @return an String[2] array holding the firstname on index 0 and lastname on index 1
   */
  public String[] search() {
    String[] fullName = getName();
    // Try to find member
    try {
      register.getMember(fullName[0], fullName[1]);
      return fullName;
    } catch (Exception e) { // FIX exception
      System.out.println("ERROR : " + e + "| Member not found\n");
      return new String[2];
    }

  }

  /**
   * Search for a particular member.
   */
  public void showMemberDetails() {
    String[] fullName = search();
    // Try to find member
    try {
      String member = register.getMemberDetailed(fullName[0], fullName[1]);
      ui.displayMemberDetails(member);
    } catch (Exception e) { // FIX exception
      System.out.println("ERROR : " + e);
    }
  }

  /**
   * Update member credentials.
   */
  public void update() {
    ui.displayUpdateMenu();
    int choice = userMenuChoice();
    switch (choice) {
      case 1: // EDIT BOAT 
        boatMenu();
        break;
      case 2: // EDIT MEMBER DETAILS
        memberMenu();
        break;
      default:
        break;
    }
  }


  /**
   * Edit name of a member.
   *
   * @throws NullPointerException if name is null
   */
  public void memberNameEdit() {
    ui.editMemberNameQuestion();
    try {
      String[] name = search();
      if (name[0].equals(null) || name[1].equals(null)) {
        System.out.println("||| THROW EXCEPTION HERE |||");
        System.out.println(" AND EXIT !!!!!!!!!");
      }
      String[] newName = getName();
      register.changeName(register.getMember(name[0], name[1]), newName[0], newName[1]);
      System.out.println("Name changed successfully");
    } catch (NullPointerException n) {
      System.out.println("ERROR: " + n);
    }
  }

  /**
   * The member menu and its content.
   */
  public void memberMenu() {
    ui.displayMemberMenu();
    int choice = userMenuChoice();
    switch (choice) {
      case 1:
        // Edit name
        memberNameEdit();
        break;
      case 2:
        // Generate new ID
        editMemberId();
        break;
      case 3:
        // Delete
        deleteMemberPrompt();
        break;
      default:
        break;
    }
  }

  /**
   * Prompt if user wants to edit a members identification.
   */
  public void editMemberId() {
    // GENERATE NEW ID
    ui.questionEditMemberId();
    int choice = userMenuChoice();
    if (choice == 1) {
      try {
        System.out.println("Enter name of member: "); // debug purposes
        String[] name = getName();
        register.getMember(name[0], name[1]); // If it fails --> nullpointerException
        register.newMemberId(register.getMember(name[0], name[1]));
        ui.newMemberIdMessage();
      } catch (Exception e) { // Change Exception to catch misinput
        System.out.println("ERROR: " + e + "\n");
      }
    } else {
      ui.newMemberIdCancelledMessage();
    }
  }

  /**
   * Delete member prompt.
   */
  public void deleteMemberPrompt() {
    ui.deleteMemberQuestion();
    int choice = userMenuChoice();
    if (choice == 1) {
      String[] fullName = getName();
      register.deleteMember(fullName[0], fullName[1]);
      ui.memberDeletedMessage();
    } else {
      ui.memberDeletionCanceledMessage();
    }

  }


  /**
   * Show menu regarding boats.
   */
  public void boatMenu() {
    ui.displayBoatMenu();
    int choice = userMenuChoice();
    switch (choice) {
      case 1:
        ui.editTypeMessage();
        break;
      case 2:
        ui.editLengthMessage();
        break;
      case 3:
        ui.deleteBoatMessage();
        break;
      default:
        break;
    }
  }

  /**
   * Converts user input from string to integer.
   *
   * @return an integer that has been converted from a string
   */
  private int userMenuChoice() {
    return Integer.parseInt(ui.getStringInput());
  }

  /**
   * A method to get a name from the user by calling the UI.
   *
   * @return firstname and lastname provided by user
   */
  public String[] getName() {
    String[] name = new String[2];
    ui.displaySearchFirstName();
    String firstName = ui.getStringInput();
    ui.displaySearchLastName();
    String lastName = ui.getStringInput();
    name[0] = firstName;
    name[1] = lastName;
    return name;
  }

}