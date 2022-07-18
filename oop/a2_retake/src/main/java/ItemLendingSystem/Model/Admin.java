package ItemLendingSystem.Model;

import java.util.ArrayList;

public class Admin extends Person {
    
    private ArrayList<Member> members;
    private Bank bank;

    public Admin() {
        this.members = new ArrayList<Member>();
        this.bank = new Bank();
    }

    public Admin(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.members = new ArrayList<Member>();
        this.bank = new Bank();
    }

    public Member createNewMember(String firstName, String lastName, String phone, String email) {
        try {
            Member member = new Member(firstName, lastName, phone, email);
            return member;
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

    public boolean payLoan(Contract contract) {
        return contract.dailyPayment(this.bank);
    }

    

}
