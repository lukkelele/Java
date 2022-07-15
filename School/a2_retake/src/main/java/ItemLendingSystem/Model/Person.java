package ItemLendingSystem.Model;

public class Person {
    
    protected String firstName, lastName;
    protected String phone;
    protected String email;

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
    
    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }

    public String getPhoneNumber() {
        return this.phone;
    }
    


}
