package controller;

import model.Game;
import view.ConsoleUI;

/**
 * Starts the application using the console.
 */
public class App {
  /**
   * Starts the game.

  * @param args Not used.
  */
  public static void main(String[] args) {

    Game g = new Game();
    ConsoleUI ui = new ConsoleUI() ;
    Player player = new Player(g, ui.getEnglishUI());

    player.playGame();
  }
}