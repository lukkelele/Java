package ItemLendingSystem;

import java.util.Date;

public class Item {
  
    private enum Category {
        TOOL,
        VEHICLE,
        GAME,
        TOY,
        SPORT,
        OTHER
    }

    private String name;
    private String desc;
    private Date manufactured;
    private int dayCost;
    
    protected Item(String name, String desc, Date manufactured, int dayCost) {
        this.name = name;
        this.desc = desc;
        this.manufactured = manufactured;
        this.dayCost = dayCost;
    }
    
    protected Item() {}
}
