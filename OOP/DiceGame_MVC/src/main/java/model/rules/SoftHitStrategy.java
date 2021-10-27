package model.rules;


import model.Card;
import model.Player;

/**
 * Strategy for Soft 17 rule.
 */
public class SoftHitStrategy implements HitStrategy {
  private static final int hitLimit = 17;

  /**
   * Checks and sees if dealer has 17 with an ace and applies Soft 17 rule.

   * @return True if dealer is under 17, or when at 17 with an Ace.
   */
  public boolean doHit(Player dealer) {

    if (dealer.calcScore() == 17 & dealer.hasCard(Card.Mutable.Value.Ace)) {
      return true;
    }
    return dealer.calcScore() < hitLimit;        // If dealer isn't at 17, do regular check
  }
}