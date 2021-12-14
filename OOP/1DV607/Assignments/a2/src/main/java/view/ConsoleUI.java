package view;

import java.util.Scanner;


public class ConsoleUI {
  
  Scanner input = new Scanner(System.in);


  /**
   * Prints a string.
   *
   * @param s is string to be printed
   */
  public void display(String s) {
    System.out.println("\n"+s);
  }

  /**
   * To reduce duplicate code, and getting inputs is a repeating process.
   *
   * @return a string provided by the user
   */
  public String getStringInput() {
    System.out.print("INPUT: ");
    return input.nextLine();
  }

  
  /**
   * Prompt message asking if the user wishes to edit the length of the boat or not.
   *
   * @return the new length to change the boats length in to
   */
  public int editBoatLength() {
    System.out.println("\nEnter length: ");
    return getInput();
  }

    /**
   * Displays allowed boat types.
   */
  public void boatTypeEnums() {      // TEMPORARY SOLUTION. Will be a pain to add new types.
    System.out.println("===== BOAT TYPES =====");
    System.out.println("1. Sailboat");
    System.out.println("2. Motorboat");
    System.out.println("3. Kayak");
    System.out.println("4. Canoe");
    System.out.println("5. Other");
  }


  /**
   * Get input from user for true/false statements.
   *
   * @return false if user enters 2, returns true on all other characters
   */
  public boolean getInputBool() {
    String input = getStringInput();
    if (input.equals("2")) {
      return false;
    }
    return true;
  }


 /**
 * Get user input. 
 *
 * @return a string[] array
 */
  public String[] searchName() {
    String[] name = new String[2];
    System.out.println("Firstname: ");
    name[0] = getStringInput();
    System.out.println("\nLastname: ");
    name[1] = getStringInput();
    return name;
  }


  /**
   * Get input and return in one single string.
   * Mostly used for search purposes. 
   *
   * @return firstname and lastname in one single string, separated by a space
   */
  public String searchFullName() {
    System.out.println("Searching:\n");
    String[] name = new String[2];
    System.out.println("Firstname: ");
    name[0] = getStringInput();
    System.out.println("\nLastname: ");
    name[1] = getStringInput();
    return (name[0] + " " + name[1]);
  }



  /**
   *  Get user input for boat type and boat length.
   *
   * @return a int[] array with type and length
   */
  public int[]  newBoatData() {
    int[] boatData = new int[2];
    System.out.println("Type: ");
    boatData[0] = editTypeMessage();
    System.out.println("\nLength: ");
    boatData[1] = editBoatLength();
    return boatData;
  }


  /**
   * Prompt user to quit. 
   *
   * @return true if 1, else false
   */
  public boolean quitPrompt() {
    System.out.println("\nContinue?\n(1) yes --- (2) no\n");
    return getInputBool();
  }


  /**
   * Prints the result of a task.  
   *
   * @param edit is the process that was done
   */
  public void editResult(boolean edit) {
    if (edit) {
      System.out.println("Success!");
    } else {
      System.out.println("Failure..");
    }
  }

  public int getInput() {
    return convertStringToInteger(getStringInput());
  }

  /**
   * Integer.parseInt(string s) with error handling.
   *
   * @param s string to be converted to an integer
   * @return converted string as an integer if successful, else return 0
   */
  public int convertStringToInteger(String s) {
    try {
      int i = Integer.parseInt(s);
      return i;
    } catch (NumberFormatException n) {
    }
    int i = 0;
    return i;
  }


  /**
   * Converts a string array to a string. 
   *
   * @param s is the String[] array to be converted
   * @return a single string with the strings from the array concatenated
   */
  protected String convertStringArrayToString(String[] s) {
    String first = s[0];      // Firstname
    String last = s[1];       // Lastname
    String fullName = first + " " + last;
    return fullName;
  }

  
  /**
   * Ask user for what kind of list they wish to print.
   *
   *
   * @return a number o
   */
  public boolean showList() {
    System.out.println("Compact or verbose?\n(1) compact\n(2) verbose");
    return getInputBool();
  }


  public void memberRegistration(boolean memberRegistered) {
    if (memberRegistered) {
      System.out.println("       Member is now registered.");
    } else {
    System.out.println("       Member is NOT registered.");
    }
  }

  public boolean memberRegisterWithBoat() {
    System.out.println("Do you wish to add a boat to the registration?");
    return getInputBool();
  }

  /**
   * Displays the header for the application.
   * It appears on top of the main menu.
   */
  public void menuHeader() {
    System.out.println("===================================");
    System.out.println("|        CRUD-CODILE DUNDEE V1    |");
    System.out.println("===================================");
  }


  /**
   * Displays the header for the application.
   * It appears on top of the menu at all times.
   */
  public void menu() {
    menuHeader();
    menuChoices();
  }


  /**
   * Displays the whole menu, with the application header on top.
   */
  public void displayUpdateMenu(String s) {
  
    switch(s) {
      case ("member"):
        System.out.println("\n\n");
        System.out.println("| 1.  Edit name                   |");
        System.out.println("| 2.  Generate new ID             |");
        System.out.println("| 3.  Delete                      |");
        System.out.println("| 4.  Go back to main menu        |");
        System.out.println("\n\n");
        break;

      case ("boat"):
        System.out.println("\n\n");
        System.out.println("| 1.  Edit type                   |");
        System.out.println("| 2.  Edit length                 |");
        System.out.println("| 3.  Delete                      |");
        System.out.println("| 4.  Go back to main menu        |");
        System.out.println("\n\n");
        break;

      default:
        break;
    }
  }

  public int boatMenu() {
    displayUpdateMenu("boat");
    System.out.println("\n");
    return getInput();
  }

  public int editTypeMessage() {
    boatTypeEnums();
    System.out.println("Enter type index: ");
    return getInput();
  }

  public int whatBoat() {
    System.out.println("What boat do you wish to edit?\nEnter index:");
    return getInput();
  }

  /**
   * Displays the menu options.
   */
  public void menuChoices() {
    System.out.println("| 1.  New member (Registration)   |");
    System.out.println("| 2.  View all members            |");
    System.out.println("| 3.  Search member               |");
    System.out.println("| 4.  Update member info          |");
    System.out.println("| 5.  Go back to main menu        |");
  }


  /**
   * Displays the update menu and its options.
   */
  public int updateMenu() {
    System.out.println("| 1.  Specific boat               |");
    System.out.println("| 2.  Member details              |");
    System.out.println("| 3.  Add new boat                |");
    System.out.println("| 4.  Go back to main menu        |");
    return convertStringToInteger(getStringInput());
  }

}

