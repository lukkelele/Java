package controller;

import model.Register;
import view.ConsoleUI;

/**
 * Regular user class.
 * Can do all regular feats that are not considered admin.
 *
 * @author Lukas Gunnarsson
 * @version 1.0
 */

public class User {

  private Register register;
  private ConsoleUI ui;


  /**
   * Single constructor.
   *
   * @param register Contains saved members and methods to manipulate the registry
   * @param ui This is used for I/O operations
   */
  public User(Register register, ConsoleUI ui) {

    this.register = register;
    this.ui = ui;
  }

  private void showMembers() {
    ui.display(register.getMembersCompact());
  }


  /**
   * Method to be looped to run the program continously in console.
   */
  public void mainMenu() {
    ui.menuChoices();
    int choice = ui.getInput();
    switch (choice) {
      case 1:
        registerUser();
        break;
      case 2:
        showList();
        break;
      case 3: 
        showMember();
        break;
      case 4:
        displayUpdateMenu();
        break;
      case 5:
        break;
      default:
        break;
    }
  }


  /**
   * The member menu and its content.
   */
  public void memberMenu() {
    showMembers();
    ui.displayUpdateMenu("member"); 
    switch (ui.getInput()) {
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
        deleteMember();
        break;

      default:
        break;
    }
  }

  /**
   * Show menu regarding boats.
   */
  public void boatMenu() {
    try {
      String name = ui.searchFullName();
      ui.display(register.getMemberDetails(name));      // Show info if user found
      int boatIndex = ui.whatBoat();
      if (register.getMember(name).countBoats() <= 0) {
        throw new NullPointerException();
      }
      switch (ui.boatMenu()) {
        case 1:       // EDIT TYPE
          int typeIndex = ui.editTypeMessage();
          ui.editResult(register.getMember(name).getBoat(boatIndex).setType(register.getBoatType(typeIndex)));
            break;

        case 2:       // EDIT LENGTH
          ui.editResult(register.getMember(name).getBoat(boatIndex).setLength(ui.editBoatLength()));
          break;

        case 3:       // REMOVE BOAT
          ui.editResult(register.getMember(name).removeBoat(register.getMember(name).getBoat(boatIndex)));
          break;

        default:
          break;
      }
    } catch (Exception e) {
  
    }
  }

  /**
   * Display menu to update specific member information.
   */
  public void displayUpdateMenu() {
    try {
      int choice = ui.updateMenu();
      showMembers();
      switch (choice) {
        case 1: // Go to boat menu and edit ONE boat
          boatMenu();
          break;

        case 2: // Go to member menu and edit ONE member
          memberMenu();
          break;

        case 3: // Add a new boat to a member
          addNewBoat();
          break;
        case 4:
          break;

        default:
          break;
      }

    } catch (NumberFormatException n) {
    }
  }

  /**
   * Show list and take input to decide which version to output.
   */
  private void showList() {
    boolean choice = ui.showList();
    if (choice) {
      ui.display(register.getMembersCompact());
    } else {
      ui.display(register.getMembersDetailed());
    }
  }

  /**
   * Outputs member with details if provided input matches a member.
   */
  public void showMember() {
    ui.display(register.getMembersCompact());
    String fullName = ui.searchFullName();
    // Try to find member
    try {
      String member = register.getMemberDetails(fullName);
      if (member.equals("null")) {
        throw new NullPointerException();
      }
      ui.display(member);
    } catch (Exception e) {
    }
  }


  /**
   * Creates and registers a new member.
   */
  public void registerUser() {
    try {
      String[] name = ui.searchName();
      ui.memberRegistration(register.registerMember(name[0], name[1])); // firstname [0], lastname[1]
      if (ui.memberRegisterWithBoat()) {
        registerUserBoat(name[0], name[1]);
      } else {
      }
    } catch (NullPointerException n) {
    }
  }


  /**
   * Registers a boat for a existing member.
   *
   * @param firstName of the member to have boat registered
   * @param lastName of the member to have boat registered
   */
  public void registerUserBoat(String firstName, String lastName) {
    try {

      int[] boatData = ui.newBoatData();
      // Type
      // boatData[0] contains the index for the type
      ui.editResult(register.getMember(firstName, lastName).newBoat(register.getBoatType(boatData[0]), boatData[1]) );
    } catch (NullPointerException n) {
    }
  }

  /**
   * Add a new boat to an existing member.
   * Uses registerUserBoat.
   */
  public void addNewBoat() {
    String[] name = ui.searchName();
    registerUserBoat(name[0], name[1]);
  }

  // ---------------------------------------------------------------------
  // Searching

  /**
   * Searches for a member by the name provided by the user.
   * If none is found, null is returned.
   *
   * @return an String[2] array holding the firstname on index 0 and lastname on index 1
   */
  public String[] searchMember() {
    String[] name = ui.searchName();
    // Try to find member
    try {
      register.getMember(name[0], name[1]);
      return name;
    } catch (NullPointerException n) { // FIX exception
      String[] emptyName = new String[2];
      return emptyName;
    }
  }


  /**
   * Edit name of a member.
   *
   * @throws NullPointerException if name is null
   */
  public void memberNameEdit() {
    try {
      String[] name = searchMember();
      String[] newName = ui.searchName();
      ui.editResult(register.changeName(register.getMember(name[0], name[1]), newName[0], newName[1]));
    } catch (NullPointerException n) {
    }
  }

  /**
   * Prompt if user wants to edit a members identification.
   */
  public void editMemberId() {
        ui.editResult(register.newMemberId(register.getMember(ui.searchFullName())));
  }


  /**
   * Delete member.
   */
  public void deleteMember() {
    ui.editResult(register.deleteMember(ui.searchFullName()));
  }



}