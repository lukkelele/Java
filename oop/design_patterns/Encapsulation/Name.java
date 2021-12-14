package Encapsulation;

public class Name {


    protected String setFirstName(Person person, String name) throws Exception {
       
        if (name.matches("[A-Za-z]")) {
            return name;
            }
        else {
            throw new Exception("Error occured... no digits allowed.");
        }
    }
}