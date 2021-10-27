package view;

public class ConsoleUI {
  EnglishView EnglishUI;
  SwedishView SwedishUI;

  public ConsoleUI () {
    EnglishUI = getEnglishUI();
    //SwedishUI = getSwedishUI;
  }

  public EnglishView getEnglishUI() {
    return new EnglishView();
  }

  public SwedishView getSwedishUI() {
    return new SwedishView();
  }
}
