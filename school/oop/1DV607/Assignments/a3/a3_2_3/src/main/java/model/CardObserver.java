package model;

public interface CardObserver {
  
  public void cardDrawn(Card.Mutable c, String playerType);
}
