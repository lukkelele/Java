package controller;


import model.Register;
import view.ConsoleUI;

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
    run();
  }


  /**
   * For starting the application and running it.
   */
  private static void run() {

    Register registry = new Register();
    ConsoleUI ui = new ConsoleUI();
    User user = new User(registry, ui);

    do {
      user.mainMenu();
    } while (ui.quitPrompt()); 

  }
}