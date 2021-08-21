package Encapsulation;

public class InterfaceExecution implements InterfaceExample {

    private InterfaceExample Uno;
    
    @Override
    public void sit() {
        System.out.println("IM SITTING NOW 1");        
    }

    @Override
    public void roar() {
        System.out.println("AAAAAAAARGH!!");
    }

    protected void afk() {
        System.out.println("Afk!");
    }

    public void setUno(InterfaceExample uno) {
        Uno = uno;
    }

    public InterfaceExample getUno() {
        return Uno;
    }
}
