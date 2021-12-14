package controller;


import model.Game;
import view.View;


/**
 * Scenario controller for playing the game.
 */
public class Player implements model.CardObserver {

  Game game;
  View ui;

  /**
   * Constructor for player controller. 
   *
   * @param g is the game instance which is manipulated by player
   * @param ui is the user interface providing print statements in the console
   */
  Player(Game g, View ui) {
    this.game = g;
    this.ui = ui;

    game.getDealer().addSubscriber(this);
  }


  /**
   * Main program. 
   *
   * @return true if player wishes to play again, else false
   */
  public boolean playGame() {
    startNewGame();

    do {
        gameChoice(ui.getInput(), game);
    }   while (!game.isGameOver());

    ui.displayGameOver(game.isDealerWinner());
    return !ui.promptQuitMessage();
  }


  /**
   * Method used after getting notified by Dealer that a card has been drawn.
   * Displays the card drawn and updates the console.
   */
  public void cardDrawn(model.Card.Mutable c, String playerType) {
    ui.cardDrawn(c, playerType);
    ui.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    ui.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
  }


  /**
   * The game menu for either 1. stand or 2. hit.
   *
   * @param n is the returned character from ui.getInput()
   * @param g
   */
  public void gameChoice(String n, Game g) {

    switch (n) {
      case "s":
        g.stand();
        break;

      case "h":
        g.hit();
        break;

      default:
        break;
    }
  }

  /**
   * Starts a new game. 
   *
   * @return true if new game was started, else false
   */
  public boolean startNewGame() {
    ui.displayWelcomeMessage();
    return game.newGame();
  }

}