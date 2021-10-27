package controller;


import model.Game;
import view.View;


/**
 * Scenario controller for playing the game.
 */
public class Player implements model.CardObserver {

  Game game;
  View ui;


  Player(Game g, View ui) {
    this.game = g;
    this.ui = ui;

    game.getDealer().addSubscriber(this);
  }


  public boolean playGame() {
    startNewGame();

    do {
      if (!game.isGameOver()) {
        gameChoice(ui.getInput(), game);
      }
    }   while (!game.isGameOver());

    ui.displayGameOver(game.isDealerWinner());
    return !ui.promptQuitMessage();
  }


  public void cardDrawn(model.Card.Mutable c, String playerType) {
    ui.cardDrawn(c, playerType);
    ui.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    ui.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
  }


  public void gameChoice(int n, Game g) {

    switch (n) {
      case 's':
        g.stand();
        break;

      case 'h':
        g.hit();
        break;

      case 'q':
        break;

      default:
        break;
    }
  }

  public boolean startNewGame() {
    ui.displayWelcomeMessage();
    return game.newGame();
  }

}