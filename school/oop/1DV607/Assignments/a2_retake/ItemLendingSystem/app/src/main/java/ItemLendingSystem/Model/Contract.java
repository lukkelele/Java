package ItemLendingSystem.Model;

import java.util.Date;

public class Contract {

    private Date startDate;
    private Date terminationDate;
    private Member owner;
    private Member lender;
    private Item item;

    protected Contract(Date start, Date termination, Member owner, Member lender, Item item) {
        this.startDate = start;
        this.terminationDate = termination;
        this.owner = owner;
        this.lender = lender;
        this.item = item;
    }

    protected void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    protected void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }
    
    protected void setLender(Member lender) {
        this.lender = lender;
    }

    public Member getLender() {
        return lender;
    }

    protected void setOwner(Member owner) {
        this.owner = owner;
    }

    public Member getOwner() {
        return owner;
    }
    
    protected void setItem(Item item) {
        this.item = item;
    }
    
    public Item getItem() {
        return item;
    }



}
