package controller;

//import model.Boat;
//import model.Boat.boatType;
//import model.Member;
import model.Register;
import view.UserInterface;

/**
 *  Runtime for the CRUD registry.
 * 
 *  @author Lukas Gunnarsson
 */

public class App {

  /**
   * Regular parameter for main method.
   * 
   * @param args 
   */
  public static void main(String[] args) {

    UserInterface ui = new UserInterface();
    Register registry = new Register();
    User user = new User(registry, ui);

    boolean flag = true;
    while (flag) {
      user.mainMenu();
      ui.continueMessage();
      String a = ui.getStringInput();
      if (a.equals("q")) {
        flag = false;
      } else {
        continue;
      }
    }
  }

}