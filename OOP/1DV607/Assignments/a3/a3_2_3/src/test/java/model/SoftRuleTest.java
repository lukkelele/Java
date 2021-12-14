package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


/**
 * Test for Soft 17 rule.
 */
public class SoftRuleTest {

  model.rules.RulesFactory rules = new model.rules.RulesFactory();    
  Player player = new Player();                                                                           // This is done to ensure that the Soft17 rule is being tested.
  Dealer dealer = new Dealer(rules);
   
  /**
   * Test to see if dealers hand increase in amount when holding 17 with an ace.
   */
  @Test
  public void TwoCards17() {


    Card.Mutable ace = new Card.Mutable(Card.Color.Hearts, Card.Value.Ace);
    Card.Mutable six = new Card.Mutable(Card.Color.Hearts, Card.Value.Six);

    dealer.newGame(player);                                                                   // New game, includes the creation of Deck
    dealer.clearHand();                                                                            // Remove dealt cards at Deck init
    dealer.giveCard(dealer, ace);
    dealer.giveCard(dealer, six);
    dealer.stand();

    int count = 0;
    for (Card c : dealer.getHand() ) {
      count++;
    }
    assertTrue(count > 2);                                                                   // Checks if card has been drawn
  }



  /**
   * Test to see if dealers hand increase in amount when holding 17 with an ace.
   * Dealer should NOT hit here, hence the cards held shall be three.
   */
  @Test
  public void noAce17() {

    Card.Mutable five = new Card.Mutable(Card.Color.Hearts, Card.Value.Five);
    Card.Mutable six = new Card.Mutable(Card.Color.Hearts, Card.Value.Six);
    Card.Mutable another_six = new Card.Mutable(Card.Color.Hearts, Card.Value.Six);

    dealer.newGame(player);                                                                   // New game, includes the creation of Deck
    dealer.clearHand();                                                                            // Remove dealt cards at Deck init
    dealer.giveCard(dealer, five);
    dealer.giveCard(dealer, six);
    dealer.giveCard(dealer, another_six);
    dealer.stand();

    int count = 0;
    for (Card c : dealer.getHand() ) {
      count++;
    }
    assertTrue(count == 3);                                                                   // Checks if card has been drawn
  }


    /**
   * Test to see if dealers hand increase in amount when holding 16 without an ace.
   * Dealer should hit here, if the card hit is an ace, Soft 17 rule shall apply which will result in 5 cards.
   * All other scenarios shall result with 4 cards.
   */
  @Test
  public void noAce16() {

    Card.Mutable four = new Card.Mutable(Card.Color.Hearts, Card.Value.Four);
    Card.Mutable six = new Card.Mutable(Card.Color.Hearts, Card.Value.Six);
    Card.Mutable another_six = new Card.Mutable(Card.Color.Hearts, Card.Value.Six);

    dealer.newGame(player);                                                                   // New game, includes the creation of Deck
    dealer.clearHand();                                                                            // Remove dealt cards at Deck init
    dealer.giveCard(dealer, four);
    dealer.giveCard(dealer, six);
    dealer.giveCard(dealer, another_six);
    dealer.stand();

    int count = 0;
    for (Card c : dealer.getHand() ) {
      if (c.getValue() == Card.Value.Ace) {
        count--;                                                                                      // Subtract one from count incase drawn card was an Ace
      }                                                                                                   // This is done because assertTrue is set to 4, and an Ace results in one extra card.
      count++;                                                                                      // If an Ace is to be drawn, Soft 17 is achieved which results in 5 cards. 
    }                                                                                                     // Taking this in to account, the count variable is decreased if an Ace is found.
    assertTrue(count == 4);                                                                 // One more card should be drawn, if not an Ace, resulting in 4 total
  }



  /**
   * Test to see if the dealer is holding two cards.
   * The value  of the two cards held are 18 which is over Soft 17.
   */
  @Test
  public void TwoCards18() {

    Card.Mutable ace = new Card.Mutable(Card.Color.Hearts, Card.Value.Ace);
    Card.Mutable seven = new Card.Mutable(Card.Color.Spades, Card.Value.Seven);

    dealer.newGame(player);                                                                   // New game, includes the creation of Deck
    dealer.clearHand();
    dealer.giveCard(dealer, ace);                                                                        // Deal ace to dealer, value 11
    dealer.giveCard(dealer, seven);                                                                    // Deal seven to dealer
    dealer.stand();                                                                                   // Checks gamerule, if over 17, stand

    int count = 0;
    for (Card c : dealer.getHand() ) {
      count++;
    } 
    
    assertTrue(count == 2);                                                                   // Checks if card has been drawn
  }
}
