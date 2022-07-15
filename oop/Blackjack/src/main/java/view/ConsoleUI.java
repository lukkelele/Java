package view;

public class ConsoleUI {

  public EnglishView getEnglishUI() {
    return new EnglishView();
  }

    /**
   * Secondary constructor to be able to change keybinds from outside the view package. 
   *
   * @param play is the string to start a game
   * @param hit is the string to 'hit' a.k.a get another card
   * @param stand is the string to 'stand' a.k.a let dealer take cards
   * @param quit is the string to quit
   * @return the new View object
   */
  public EnglishView getEnglishUI(String play, String hit, String stand, String quit) {
    return new EnglishView(play, hit, stand, quit);
  }


  public SwedishView getSwedishUI() {
    return new SwedishView();
  }

  /**
   * Secondary constructor to be able to change keybinds from outside the view package. 
   *
   * @param play is the string to start a game
   * @param hit is the string to 'hit' a.k.a get another card
   * @param stand is the string to 'stand' a.k.a let dealer take cards
   * @param quit is the string to quit
   * @return the new View object
   */
  public SwedishView getSwedishView(String play, String hit, String stand, String quit) {
    return new SwedishView(play, hit, stand, quit);
  }

}
