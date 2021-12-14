package model.rules;

import model.Player;

public class FavorDealerStrategy implements FavorStrategy {
  
  private static final int maxScore = 21;

  /**
   * Compares score between dealer and player and decides who is a winner.
   *
   * @param dealer is the dealer of the game
   * @param player is the current player 
   * @return true if dealer has the same OR higher score than player
   */
  public boolean isWinner(Player dealer, Player player) {

    if (dealer.calcScore() <= maxScore && player.calcScore() > maxScore) {
      return true;
    } else if (dealer.calcScore() <= maxScore ) {
      return dealer.calcScore() >= player.calcScore();
    } else if (dealer.calcScore() > maxScore) {                                                                                     // If dealer goes bust, all higher points win, lower lose
      return player.calcScore() > dealer.calcScore();
    } else if (dealer.calcScore() == player.calcScore() && dealer.calcScore() <= maxScore) {
      return true;
    } else {
      return false;
    }
  }
}