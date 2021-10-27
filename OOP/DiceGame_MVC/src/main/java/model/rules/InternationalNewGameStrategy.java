package model.rules;

import model.Dealer;
import model.Player;

/**
 * Deals three cards, two to the player and one to the dealer.
 * No cards are hidden.
 */
class InternationalNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Dealer dealer, Player player) {

    dealer.clearHand();
    player.clearHand();
    // Cards are dealt to self are even, first card goes to player then to dealer 
    // Counter starts at 0, causing the cards dealt to dealer are odd instead
    for (int k = 0 ; k < 3 ; k++) {            
      if (k%2 != 0) {      // Odd numbers
        dealer.dealCard(dealer, true);
      } else {
        dealer.dealCard(player, true);
      }
    }
    return true;
  }
}