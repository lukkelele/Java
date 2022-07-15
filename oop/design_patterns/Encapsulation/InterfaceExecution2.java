package Encapsulation;

public class InterfaceExecution2 implements InterfaceExample {

    private InterfaceExample Dos;

    @Override
    public void roar() {
        System.out.println("reeeeee");
    }

    @Override
    public void sit() {
        System.out.println("nah 2");    
    }

    public InterfaceExample getDos() {
        return Dos;
    }

    public void setDos(InterfaceExample dos) {
        Dos = dos;
    }
    
}
