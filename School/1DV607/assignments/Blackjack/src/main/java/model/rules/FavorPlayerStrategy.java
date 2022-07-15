package model.rules;

import model.Player;

public class FavorPlayerStrategy implements FavorStrategy {

  private static final int maxScore = 21;

  /**
   * Compare player and dealers hands to determine who wins.
   * The player is in favor with this rule.
   *
   * @param dealer is the dealer
   * @param player is the player currently playing
   * @return  true if the player score fits the conditional statements, else false
   */
  public boolean isWinner(Player dealer, Player player) {

    if (player.calcScore() <= maxScore && dealer.calcScore() > maxScore) {
      return true;
    } else if (player.calcScore() == dealer.calcScore() && player.calcScore() <= maxScore) {
      return true;
    } else {
      return false;
    }
  }
}
