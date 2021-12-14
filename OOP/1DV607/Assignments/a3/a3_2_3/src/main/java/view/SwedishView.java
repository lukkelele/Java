package view;

import java.util.Scanner;

/**
 * Implements a Swedish console view.
 */
public class SwedishView implements View {

  Scanner input = new Scanner(System.in);

  String hit = "h";
  String stand = "s";
  String quit = "q";
  String play = "p";
  String[] commands = {play, hit, stand, quit};

  public SwedishView(){}

  public SwedishView(String play, String hit, String stand, String quit) {
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

    System.out.println("Hej Black Jack Världen");
    System.out.println("----------------------");
    System.out.printf("Skriv '%s' för att spela, '%s' för ett nytt kort, '%s' för att stanna och '%s' för att avsluta", play, hit, stand, quit);
  }

  public void displayInGameMessage() {
    System.out.printf("%nSkriv '%s' för att slå, '%s' för att stanna eller '%s' för att avsluta%n", hit, stand, quit);
  }

  public boolean promptQuitMessage() {
    System.out.printf("%nVill du spela en runda till?%nSkriv '%s' för att spela igen%nSkriv '%s' för att avsluta%n", play, quit);
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

  /**
   * Displays a card.

   * @param card The card to display.
   */
  public void displayCard(model.Card card) {
    if (card.getColor() == model.Card.Color.Hidden) {
      System.out.println("Dolt Kort");
    } else {
      String[] colors = { "Hjärter", "Spader", "Ruter", "Klöver" };
      String[] values = { "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio",
                          "knekt", "dam", "kung", "ess" };
      System.out.println("" + colors[card.getColor().ordinal()] + " " + values[card.getValue().ordinal()]);
    }
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Spelare", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Croupier", hand, score);
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("Slut: ");
    if (dealerIsWinner) {
      System.out.println("Croupiern Vann!");
    } else {
      System.out.println("Du vann!");
    }
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Har: " + score);
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Poäng: " + score);
    System.out.println("");
  }


  public void cardDrawn(model.Card c, String playerType) {
    try {
      System.out.println("*pause*");
      Thread.sleep(1000);
      displayCard(c);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

