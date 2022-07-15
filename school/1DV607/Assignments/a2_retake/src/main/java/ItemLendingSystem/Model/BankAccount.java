package ItemLendingSystem.Model;

public class BankAccount {
    
    private Member owner;
    private int credits;

    public BankAccount(Member member) {
        this.owner = member;
        this.credits = 0;
    }

    public BankAccount(Member member, int startCapital) {
        this.owner = member;
        this.credits = startCapital;
    }

    public int getBalance() {
        return this.credits;
    }

    public void addFunds(int credits) {
        this.credits += credits;
    }

    /*  @TODO: Check for negative values  */
    public int removeFunds(int credits) {
        this.credits -= credits;
        if (this.credits < 0) {
            // negative balance
            this.credits += credits; // Refund 
            return 0;       // No funds removed
        }
        return credits;
        
    }

    public String getOwnerId() {
        return this.owner.getId();
    }


}
