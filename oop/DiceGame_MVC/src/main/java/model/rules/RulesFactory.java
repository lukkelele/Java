package model.rules;


/**
 * Creates concrete rules.
 */
public class RulesFactory {

  HitStrategy hitStrategy;
  NewGameStrategy newGameStrategy;
  FavorStrategy favorStrategy;

  public RulesFactory(){
    this.hitStrategy = new SoftHitStrategy();                         // TODO: If this is BASIC the SoftRuleTest needs to be disabled
    this.newGameStrategy = new InternationalNewGameStrategy();
    this.favorStrategy = new FavorDealerStrategy();
  }

  /**
   * Creates the basic hit rule to use for the dealer's hit behavior.

   * @return The rule to use
   */
  public HitStrategy getHitRule() {
    //return new BasicHitStrategy();
    return this.hitStrategy;
  }

  /**
   * Crates the rule to use when starting a new game.

   * @return The rule to use.
   */
  public NewGameStrategy getNewGameRule() {
    return this.newGameStrategy;
    //return new InternationalNewGameStrategy();
  }

  

  public FavorStrategy  getFavorStrategy() {
    return  this.favorStrategy;
    //return new FavorPlayerStrategy();
  }

}