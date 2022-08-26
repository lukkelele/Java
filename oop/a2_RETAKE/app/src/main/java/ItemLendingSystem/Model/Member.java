package ItemLendingSystem.Model;

import java.util.ArrayList;
//import java.util.Iterator;

public class Member {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private BankAccount bankAccount;
    private ID id;
    private ArrayList<Item> ownedItems;
    private ArrayList<Contract> contracts;

    public Member(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.id = new ID(6);
        this.bankAccount = new BankAccount(this);
        this.ownedItems = new ArrayList<Item>();
        this.contracts = new ArrayList<Contract>();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public boolean isOwner(Contract c) {
        return this == c.getOwner();
    }

    public int getItemCount() {
        int itemCount = this.ownedItems.size();
        for (Contract c : this.contracts) {
            if (this.isOwner(c)) {
                itemCount += 1;
            }
        }
        return itemCount;
    }

    public void addOwnedItem(Item item) {
        this.ownedItems.add(item);
    }

    public void removeOwnedItem(Item item) {
        this.ownedItems.remove(item);
    }

    public String getId() {
        return id.getId();
    }

    /* 
    public Item getItem(String itemName) {
        Iterator<Item> iter = this.ownedItems.iterator();
        while (iter.hasNext()) {
            String currentItemName = iter.next().getName();
            if (itemName.equals(currentItemName)) {
                return iter.next();
            }
        }
        return null;
    } */

    public int getBalance() {
        return this.bankAccount.getBalance();
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public ArrayList<Item> getOwnedItems() {
        return this.ownedItems;
    }

    public ArrayList<Contract> getContracts() {
        return this.contracts;
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    public void removeContract(Contract contract) {
        this.contracts.remove(contract);
    }

}
