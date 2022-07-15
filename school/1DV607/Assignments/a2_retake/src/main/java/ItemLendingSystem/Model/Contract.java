package ItemLendingSystem.Model;


public class Contract implements Observer {

    private int startDate;
    private int terminationDate;
    private Member owner;
    private Member lender;
    private Item item;

    protected Contract(int startDate, int termination, Member owner, Member lender, Item item) {
        this.startDate = startDate;
        this.terminationDate = termination;
        this.owner = owner;
        this.lender = lender;
        this.item = item;
    }

    protected void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getStartDate() {
        return startDate;
    }

    protected void setTerminationDate(int terminationDate) {
        this.terminationDate = terminationDate;
    }

    public int getTerminationDate() {
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

    @Override
    public int update(int days) {
        // NOTIFY BANK FOR TRANSFER
        return this.terminationDate;
    }

    @Override
    public void reset() {}

}
