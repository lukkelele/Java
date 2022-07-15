package view;

import java.util.Scanner;

/**
 * Implements an english console view.
 */
public class EnglishView implements View {

  Scanner input = new Scanner(System.in);

  String hit = "h";
  String stand = "s";
  String quit = "q";
  String play = "p";
  String[] commands = {play, hit, stand, quit};

  public EnglishView(){}

  public EnglishView(String play, String hit, String stand, String quit) {
    this.hit = hit;
    this.stand = stand;
    this.quit = quit;
    this.play = play;
  }


  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }
    System.out.println("Hello Black Jack World");
    displayInGameMessage();
  }


  public void displayInGameMessage() {
    System.out.printf("%nType '%s' to Hit, '%s' to Stand or '%s' to Quit%n", hit, stand, quit);
  }

  public boolean promptQuitMessage() {
    System.out.printf("%nDo you want to play another round?%nType '%s' to play again%nType '%s' to quit%n", play, quit);
    return getInput() == "q";
  }

  /**
   * Get string input.
   *
   * @return string containing users keypresses
   */
  public String getInput() {
    String userInput = input.nextLine();
    if (userInput.equals(play)) {     // Play
      return "p";
    }
    if (userInput.equals(hit)) {    // Hit
      return "h";
    }
    if (userInput.equals(stand)) {  // Stand
      return "s";
    }
    if (userInput.equals(quit)) {   // Quit
      return "q";
    } else {
      return "q";
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
