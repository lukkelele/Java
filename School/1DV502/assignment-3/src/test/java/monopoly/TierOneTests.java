package monopoly;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// These tests are to be uncommented for the passing grade
// preferably solve the tests in order
public class TierOneTests {

  // implement the constructor in Tile so that it sets the next and prev links to this
  @Test
  public void tileConstructionTest_1() {
    // implement the constructor in Tile so that it sets the next and prev links correctly
    DummyTile sut = new DummyTile();

    // (1) <- 1 -> (1)
    assertEquals("Link to next tile not set correctly", sut, sut.getNext());
    assertEquals("Link to prev tile not set correctly.", sut, sut.getPrev());

  }

  @Test
  public void tileConstructionTest_2() {
    // implement the constructor in Tile so that it sets the next and prev links correctly
    DummyTile sut1 = new DummyTile();
    DummyTile sut2 = new DummyTile(sut1);

    // the list should look like this
    // (2) <- 1 <-> 2 -> (1)
    assertEquals("Link to sut1 next tile not set correctly", sut2, sut1.getNext());
    assertEquals("Link to sut1 prev tile not set correctly", sut2, sut1.getPrev());
    assertEquals("Link to sut2 prev tile not set correctly", sut1, sut2.getPrev());
    assertEquals("Link to sut2 next tile not set correctly", sut1, sut2.getNext());
  }

  @Test
  public void tileConstructionTest_3() {
    // implement the constructor in Tile so that it sets the next and prev links correctly
    DummyTile sut1 = new DummyTile();
    DummyTile sut2 = new DummyTile(sut1);
    DummyTile sut3 = new DummyTile(sut2);

    // the list should look like this
    // (3) <- 1 <-> 2 <-> 3 -> (1)
    assertEquals("Link to sut3 next tile not set correctly", sut1, sut3.getNext());
    assertEquals("Link to sut3 prev tile not set correctly", sut2, sut3.getPrev());
    assertEquals("Link to sut2 prev tile not set correctly", sut1, sut2.getPrev());
  }

  @Test
  public void tileConstructionTest_4() {
    // implement the constructor in Tile so that it sets the next and prev links correctly
    DummyTile sut1 = new DummyTile();
    DummyTile sut2 = new DummyTile(sut1);
    DummyTile sut3 = new DummyTile(sut1);

    // (2) <- 1 <-> 3 <-> 2 -> (1)
    assertEquals("Link to sut3 next tile not set correctly", sut2, sut3.getNext());
    assertEquals("Link to sut3 prev tile not set correctly", sut1, sut3.getPrev());
    assertEquals("Link to sut1 prev tile not set correctly", sut3, sut1.getNext());
    assertEquals("Link to sut1 prev tile not set correctly", sut2, sut1.getPrev());
    assertEquals("Link to sut2 prev tile not set correctly", sut3, sut2.getPrev());
    assertEquals("Link to sut2 prev tile not set correctly", sut1, sut2.getNext());
  }


  @Test
  public void propertyTest_buy_ok() {
    // implement the Property buy operation according to the Design document sequence diagram
    Start s = new Start();
    final String streetName = "Downing Street";
    Property sut = new Property(s, streetName);

    DummyPlayer p = new DummyPlayer(sut, "Dummy Player");
    int originalFunds = p.getFunds();

    boolean ret = sut.buy(p);

    assertEquals("Property owner not correct set", true, sut.isOwner(p));
    assertEquals("Funds not correctly deduced", originalFunds - sut.getPrice(), p.getFunds());
    assertEquals("buy should return true if the property is bought", true, ret);
  }

  @Test
  public void propertyTest_buy_hasOwner() {
    // implement the Property buy operation according to the Design document sequence diagram
    Start s = new Start();
    final String streetName = "Downing Street";
    Property sut = new Property(s, streetName);

    DummyPlayer p = new DummyPlayer(sut, "Dummy Player");
    DummyPlayer someOtherPlayer = new DummyPlayer(sut, "Someone else");

    sut.buy(someOtherPlayer);

    int originalFunds = p.getFunds();

    boolean ret = sut.buy(p);

    
    assertEquals("Property owner should not change", true, sut.isOwner(someOtherPlayer));
    assertEquals("Property owner should not change", false, sut.isOwner(p));

    assertEquals("Funds should not be deduced", originalFunds, p.getFunds());
    assertEquals("buy should return false if the property is not bought", false, ret);
  }

  @Test
  public void propertyTest_buy_notOnTile() {
    // implement the Property buy operation according to the Design document sequence diagram
    Start s = new Start();
    final String streetName = "Downing Street";
    Property sut = new Property(s, streetName);

    DummyPlayer p = new DummyPlayer(s, "Dummy Player");
    int originalFunds = p.getFunds();

    boolean ret = sut.buy(p);

    assertEquals("Property owner should not be set", false, sut.isOwner(p));
    assertEquals("Funds should not be deduced", originalFunds, p.getFunds());
    assertEquals("buy should return false if the property is not bought", false, ret);
  }

  @Test
  public void propertyTest_buy_noFunds() {
    // implement the Property buy operation according to the Design document sequence diagram
    Start s = new Start();
    final String streetName = "Downing Street";
    Property sut = new Property(s, streetName);

    DummyPlayer p = new DummyPlayer(sut, "Dummy Player");
    int originalFunds = p.getFunds();
    p.deduceFunds(originalFunds - 1);

    boolean ret = sut.buy(p);

    assertEquals("Property owner should not be set", false, sut.isOwner(p));
    assertEquals("buy should return false if the property is not bought", false, ret);
  }

  
  
  
  // These are some internal classes used to facilitate testing. You do not need to bother with them

  class DummyPlayer extends HumanPlayer {

    public DummyPlayer(Tile a_currentTile, String a_name) {
      super(a_currentTile, a_name, new ConsoleUi());
    }
  }

  class DummyTile extends Tile {

    DummyTile() {
      super();
    }

    DummyTile(Tile a_prev) {
      super(a_prev);
    }

    @Override
    public void startOn(Player player) {

    }

    @Override
    public void visit(Player player) {

    }

    @Override
    public void stoppedOn(Player player) {

    }

    @Override
    public boolean buy(Player player) {
      return false;
    }
  }

}
