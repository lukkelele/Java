package factory;

public class Airplane {

    private String name;
    
    //

    Airplane(){}

    Airplane(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void fly() {
        System.out.println("The plane soars in the sky.");
    }

    public void land() {
        System.out.println("We landed safely.");
    }

}
