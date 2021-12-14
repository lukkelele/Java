package Encapsulation;
public class Person {

    private String FirstName;
    private String LastName;
    private InterfaceExample strategy;
    private Name NameObject;


    Person(){
        NameObject = new Name();
    }


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) throws Exception {
        this.FirstName = NameObject.setFirstName(this, firstName);
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public static void main(String[] args) throws Exception {
        
        Person human = new Person();
        System.out.println(human.getFirstName());
        human.setFirstName("Luks");
        System.out.println(human.getFirstName());

    }

    public void play() {
        strategy.roar();
    }

    public void setStrategy(InterfaceExample strategy) {
        this.strategy = strategy;
    }
}
