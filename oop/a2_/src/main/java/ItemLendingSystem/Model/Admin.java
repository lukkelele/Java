package ItemLendingSystem.Model;

import java.util.ArrayList;

public class Admin {
    
    private ArrayList<Member> members;
    private ArrayList<Contract> contracts;
    private Calendar calendar;


    public Admin() {
        this.members = new ArrayList<Member>();
        this.contracts = new ArrayList<Contract>();
        this.calendar = new Calendar();
    }

    private boolean checkUniqueEmailPhoneNumber(String phone, String email) {
        String memberPhone, memberEmail;
        for (Member m : this.members) {
            memberPhone = m.getPhone(); 
            memberEmail = m.getEmail();
            if (memberPhone == phone || memberEmail == email) {
                return false;
           }
        }
        return true;
    }

    public Member createNewMember(String firstName, String lastName, String phone, String email) {
        try {
            if (checkUniqueEmailPhoneNumber(phone, email) == true) {
                Member member = new Member(firstName, lastName, phone, email);
                return member;
            }
            throw new Exception();
        } catch (Exception e1) {
            System.out.println("ERROR: "+e1);
            return null;
        }
    }

    public boolean registerMember(Member member) {
        if (member != null && !this.members.contains(member)) {
            this.members.add(member);
            return true;
        }
        return false;
    }

    public boolean unregisterMember(Member member) {
        if (this.members.contains(member)) {
            this.members.remove(member);
            return true;
        }
        return false;
    }


    public int getMemberCountItems(Member member) {
        return member.getItemCount();       
    }

/**
 * The getMemberItems function returns a string containing all the items that are owned by the member.
 * 
 * @param member Used to Get the member's full name.
 * @param boolean Used to Determine if the amount of owned items or the items should be returned.
 * @return A string that contains the item information for all of a member's items.
 * 
 */
    public String getMemberItems(Member member) {
        String itemsString = "";
        for (Contract c : member.getContracts()) {
            if (member.isOwner(c)) {
                Item item = c.getItem();
                String itemName = item.getName();
                String itemCategory = item.getCategory().toString();
                String dayCost = String.valueOf(item.getDayCost());
                itemsString += "Item name: " + itemName + "\nCategory: " + itemCategory + "\nCost/day : "
                + dayCost + "\nLoaned to: " + c.getLender().getFullName() + "\nExpiration: " + c.getTerminationDate() + "\n---\n";
            }
        }
        for (Item item : member.getOwnedItems()) {
            String itemName = item.getName();
            String itemCategory = item.getCategory().toString();
            String dayCost = String.valueOf(item.getDayCost());
            itemsString += "Item name: " + itemName + "\nCategory: " + itemCategory + "\nCost/day : "
            + dayCost +"\n---\n";
        }
        return itemsString;
    }


/**
 * The getMemberCredentials function returns a String array containing the member's 
 * first name, last name, phone number, email address and credits.
 * 
 * @param member Used to Get the member's first name, last name, phone number, email address and balance.
 * @param boolean Used to Determine whether or not the member's items should be printed.
 * @return A string array containing the member name, phone number, email address and balance.
 * 
 */
    public String[] getMemberCredentials(Member member, boolean verbose) {
        String firstName, lastName, phone, email, credits, items;
        firstName = member.getFirstName();
        lastName = member.getLastName();
        phone = member.getPhone();
        email = member.getEmail();
        credits = String.valueOf(member.getBalance());
        if (verbose) {
            items = getMemberItems(member);
        } else {
            items = String.valueOf(getMemberCountItems(member));
        }
        String[] credentials = {firstName, lastName, phone, email, credits, items};
        return credentials;
    }

/**
 * Used to retrieve a String[][] array containing the information
 * of each member in the group. The first dimension is the number of members, and each 
 * element contains an array with all relevant information about that member. If verbose is true, 
 * then more detailed information will be returned for each member.
 * 
 * @param verbose Used to Determine whether or not the function should return verbose information about each member.
 * @return A 2d array of strings.
 * 
 */
    public String[][] getMembersInformation(boolean verbose) {
        int membersLength = this.members.size();
        String[][] membersInformation = new String[membersLength][];
        for (int i=0 ; i < membersLength ; i++) {
            Member member = this.members.get(i);
            membersInformation[i] = getMemberCredentials(member, verbose);
        } 
        return membersInformation;
    }


/**
 * Used to loan an item from one member to another.
 * It takes in the two members and the item that is being loaned, as well as
 * how many days it will be loaned for. The function then checks if either of 
 * the members owns this item, and if so, creates a contract with these two 
 * members and adds it to our list of contracts. If neither member owns this 
 * item however, a null value is returned.
 * 
 * @param member1 Used to Access the member object that is currently logged in.
 * @param Member Used to Determine which member owns the item.
 * @param Item Used to Get the item that is being loaned.
 * @param int Used to Specify the number of days that the item is loaned for.
 * @return True if the item is successfully loaned.
 * 
 */
    public boolean loanItem(Member member1, Member member2, Item item, int days) {
        Contract contract;
        if (member1.getOwnedItems().contains(item)) {
            // If member1 owns the item
            contract = new Contract(this.calendar.getCurrentDay(), days, member1, member2, item);
            member1.addContract(contract);
            member1.removeOwnedItem(item);
            System.out.println("Item loaned! From member1 --> member2");
            this.contracts.add(contract);
        }
        else if (member2.getOwnedItems().contains(item)) {
            // If member2 owns the item
            contract = new Contract(this.calendar.getCurrentDay(), days, member2, member1, item);
            member2.addContract(contract);
            member2.removeOwnedItem(item);
            System.out.println("Item loaned! From member2 --> member1");
            this.contracts.add(contract);
        } else {
            System.out.println("ITEM NOT FOUND IN POSSESSION OF ANY MEMBER!");
            return false;
        }
        return true;
    }

/**
 * Used to return items to their respective owner when a contract has expired.
 * 
 * @param member1 Used to Specify the member that is returning the item.
 * @param Member Used to Get the item that is being returned.
 * @param Contract Used to Get the item that is being returned.
 * @return True if the owner of the contract is member 1 or 2.
 * 
 */
    public boolean returnItem(Member member1, Member member2, Contract contract) {
        Member owner = contract.getOwner();
        if (member1 == owner) {
            // If member1 is the owner
            Item item = contract.getItem();
            member1.addOwnedItem(item);
        }
        else if (member2 == owner) {
            Item item = contract.getItem();
            member2.addOwnedItem(item);
        } else {
            System.out.println("Error expiring contract!");
            return false;
        }
        this.contracts.remove(contract);
        member1.removeContract(contract);
        member2.removeContract(contract);
        return true;
    }

}
