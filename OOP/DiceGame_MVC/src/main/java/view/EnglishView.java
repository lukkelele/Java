package view;


// View is observing the state of the app and responds to different changes
/**
 * Implements an english console view.
 */
public class EnglishView implements View {

  private int cardCount = 0;

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }
    System.out.println("Hello Black Jack World");
  }

  public void displayInGameMessage() {
    System.out.println("\n\nType 'h' to Hit, 's' to Stand or 'q' to Quit\n");
  }

  public boolean promptQuitMessage() {
    System.out.println("\n\nDo you want to play another round?\nType 'p' to play again\nType 'q' to quit\n");
    return getInput() != 'q';
  }

  /**
   * Returns pressed characters from the keyboard.

   * @return the pressed character.
   */
  public int getInput() {
    displayInGameMessage();
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  public void displayCard(model.Card card) {
    System.out.println("" + card.getValue() + " of " + card.getColor());
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Player", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Dealer", hand, score);
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Has: ");
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Score: " + score);
    System.out.println("");
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("GameOver: ");
    if (dealerIsWinner) {
      System.out.println("Dealer Won!");
    } else {
      System.out.println("You Won!");
    }
  }

  public void cardDrawn(model.Card c, String playerType) {
    try {
      Thread.sleep(1000);    
      whatPlayer(playerType);
      displayCard(c);
      Thread.sleep(1000);
      System.out.println("\n");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void whatPlayer(String player) {
    if (player.equals("class model.Dealer")) {
      System.out.print("Dealer was dealt: ");
    } else {
      System.out.print("Player was dealt: ");
    }
  }
}
