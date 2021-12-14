package controller;

//import model.Boat;
//import model.Boat.boatType;
import model.Member;
import model.Register;
import view.UserInterface;

/**
 * Runtime for the CRUD registry.
 *
 * @author Lukas Gunnarsson
 */

public class App {

  /**
   * Regular parameter for main method.
   *
   * @param args main
   */
  public static void main(String[] args) {

    UserInterface ui = new UserInterface();
    Register registry = new Register();
    User user = new User(registry, ui);

    //Member m1 = new Member("Lukas", "Gunnarsson");

    String a;
    do {
      user.mainMenu();
      ui.continueMessage();
      a = ui.getStringInput();
    } while (!a.equals("q"));
    }
}