package model.rules;

import model.Dealer;
import model.Player;

class AmericanNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Dealer dealer, Player player) {

    dealer.clearHand();
    player.clearHand();
    // Cards are dealt to self are even, first card goes to player then to dealer 
    // Counter starts at 0, causing the cards dealt to dealer are odd instead
    for (int k = 0 ; k < 3; k++) {            
      if (k%2 != 0) {      // Odd numbers
        dealer.dealCard(dealer, true);
      } else {
        dealer.dealCard(player, true);                          // true == Card NOT hidden
      }
    }
    dealer.dealCard(dealer, false);                            // Fourth card is hidden and dealt to dealer
    return true;
  }
}