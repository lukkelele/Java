package model;

import java.util.ArrayList;
import model.rules.FavorStrategy;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.RulesFactory;

/**
 * Represents a dealer player that handles the deck of cards and runs the game using rules.
 */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private FavorStrategy favorRule;
  private ArrayList<CardObserver> subscribers;

  /**
   * Initializing constructor.

   * @param rulesFactory A factory that creates the rules to use.
   */
  public Dealer(RulesFactory rulesFactory) {

    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    favorRule = rulesFactory.getFavorStrategy();
    subscribers = new ArrayList<>();
  }

  
  public void addSubscriber(CardObserver s) {
    subscribers.add(s);
  }


  public void removeSubscriber(CardObserver s) {
    subscribers.remove(s);
  }

/**
 * Notifies all subsribed objects that a card has been drawn.
 *
 */
  private void notifyCardDrawn(Card.Mutable c, String playerType) {
    for (CardObserver observer : subscribers) {
      observer.cardDrawn(c, playerType);
    }
  }


  /**
   * Starts a new game if the game is not currently under way.

   * @param player The player to play agains.
   * @return True if the game could be started.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      return newGameRule.newGame(this, player);
    }
    return false;
  }

/**
 * Deal card to a player with the option to have it hidden or not. 
 *
 * @param player to deal the card to
 * @param show  is false if the card is to be hidden, else true
 */
  public void dealCard(Player player, boolean show) {
    Card.Mutable c = deck.getCard();
    c.show(show);
    player.addCard(c);
    notifyCardDrawn(c, (player.getClass().toString()));
    }

  /**
   * Gives the player one more card if possible. I.e. the player hits.

   * @param player The player to give a card to.
   * @return true if the player could get a new card, false otherwise.
   */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      dealCard(player, true);
      return true;
    }
    stand();      // If player hits whilst calcScore() >= 21 then run stand()
    return false;
  }

  /**
   * Checks if the dealer is the winner compared to a player.

   * @param player The player to check agains.
   * @return True if the dealer is the winner, false if the player is the winner.
   */
  public boolean isDealerWinner(Player player) {
    return this.favorRule.isWinner(this, player);
  }

  /**
   * Checks if the game is over, i.e. the dealer can take no more cards.

   * @return True if the game is over.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /** 
   * The player has choosen to take no more cards, it is the dealers turn.
   * 
   * @return true
   */
  public boolean stand() {                    
    showHand();                    // Lays the card on the table
    while (hitRule.doHit(this) == true) {
      hitRule.doHit(this);                  
      hit(this);                            
    }
    return true;                  
  }

  /**
   * Adds a card to the Player's hand. 

   * @param dealtCard The card dealt to a player who picks it up and adds it to their hand.
   */
  public void giveCard(Player player, Card.Mutable dealtCard) {
    player.addCard(dealtCard);
  }

}

