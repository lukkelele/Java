package model;

/**
 * To be able to call a save/load method and to get all info saved this interface is introduced.
 *
 * @author Lukas Gunnarsson
 * @version 1.0
 */

public interface Persistance {
  
  /**
   * Save data to database.
   */
  public void save();

  /**
   * Load data from database.
   */
  public void load();

  
}
