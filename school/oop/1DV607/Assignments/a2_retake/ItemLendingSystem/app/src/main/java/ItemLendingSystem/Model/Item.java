package ItemLendingSystem.Model;

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
    private Contract contract;
    private Category category;

    public Item(String name, String desc, Date manufactured, int dayCost, Category category) {
        this.name = name;
        this.desc = desc;
        this.manufactured = manufactured;
        this.dayCost = dayCost;
        if (category == null) {
            this.category = Category.OTHER;
        } else {
            this.category = category;
        }
    }
    
    public Item() {}
   
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }

    public void setDayCost(int dayCost) {
        this.dayCost = dayCost;
    }

    public int getDayCost() {
        return dayCost;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }

    public Date getManufactured() {
        return manufactured;
    }

}
