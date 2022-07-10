package ItemLendingSystem;

public class Member {
   
    private String firstName, lastName;
    private String phone;
    private String email;
    private ID id;
    private Item[] ownedItems;
    private Item[] borrowedItems;

    public Member(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.id = new ID(6);
    }

    public Member() { }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getId() {
        return id.getId();
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }

    public String getPhoneNumber() {
        return phone;
    }
    
    public void setOwnedItems(Item[] ownedItems) {
        this.ownedItems = ownedItems;
    }

    public Item[] getOwnedItems() {
        return ownedItems;
    }

    public void setBorrowedItems(Item[] borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public Item[] getBorrowedItems() {
        return borrowedItems;
    }



}
