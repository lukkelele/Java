package ItemLendingSystem.Model;

import java.util.ArrayList;

public class Member extends Person {
 
    private BankAccount bankAccount;
    private ID id;
    private ArrayList<Item> ownedItems;
    private ArrayList<Item> borrowedItems;

    public Member(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.id = new ID(6);
        this.bankAccount = new BankAccount(this);
        this.ownedItems = new ArrayList<Item>();
        this.borrowedItems = new ArrayList<Item>();
    }

    public Member() {
        this.id = new ID(6);
        this.bankAccount = new BankAccount(this);
        this.ownedItems = new ArrayList<Item>();
        this.borrowedItems = new ArrayList<Item>();
    }

    public void addOwnedItem(Item item) {
        this.ownedItems.add(item);
    }

    public void removeOwnedItem(Item item) {
        this.ownedItems.remove(item);
    }

    public void addBorrowedItem(Item item) {
        this.borrowedItems.add(item);
    }

    public void removeBorrowedItem(Item item) {
        this.borrowedItems.remove(item);
    }

    public String getId() {
        return id.getId();
    }

    public int getBalance() {
        return this.bankAccount.getBalance();
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public ArrayList<Item> getOwnedItems() {
        return this.ownedItems;
    }

    public ArrayList<Item> getBorrowedItems() {
        return this.borrowedItems;
    }



}
