package ItemLendingSystem.Model;

import java.util.ArrayList;

public class Bank implements Persistance {
    
    private ArrayList<BankAccount> accounts; // MIGHT BE REDUNDANT
    public void save(){}
    public void load(){}

    public Bank() {
        this.accounts = new ArrayList<BankAccount>();
    }

    /* MIGHT BE REDUNDANT */
    public boolean addMember(Member member) {
        BankAccount bankAccount = member.getBankAccount();
        if (!this.accounts.contains(bankAccount)) {
            this.accounts.add(member.getBankAccount());
            return true;
        }
        return false;
    }
    
    /* MIGHT BE REDUNDANT */
    public boolean removeMember(Member member) {
        BankAccount bankAccount = member.getBankAccount();
        if (this.accounts.contains(bankAccount)) {
            this.accounts.remove(bankAccount);
            return true;
        }
        return false;
    }

    public boolean deposit(Member member, int credits) {
        BankAccount bankAccount = member.getBankAccount();
        bankAccount.addFunds(credits);
        return true;
    }

    /* Returns int instead of a boolean value because of possibilities 
     * of the Bank to loan a member money if such a thing was to be 
     * allowed and implemented later.
     */
    public int withdraw(Member member, int credits) {
        BankAccount bankAccount = member.getBankAccount();
        int withdrawedAmount = bankAccount.removeFunds(credits);
        return withdrawedAmount;
    }

    /* Transfer money from member1 -> member2 */
    public boolean transfer(Member member1, Member member2, int credits) {
        int transferCredits = withdraw(member1, credits);
        if (transferCredits != 0) {
            // if member1 has sufficient balance on their account
            boolean transferDeposit = deposit(member2, transferCredits);
            if (transferDeposit == true) {
                // if the funds was successfully moved
                return true;
            } else {
                return false;
            }
        }
        return false; // no funds correctly transferred
    }

}
