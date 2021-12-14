package model.rules;


import model.Player;


public interface FavorStrategy {

  boolean isWinner(Player dealer, Player player);

  
}
