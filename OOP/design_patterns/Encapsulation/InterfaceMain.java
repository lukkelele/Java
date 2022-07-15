package Encapsulation;

public class InterfaceMain {
    

    public static void main(String[] args) {
        InterfaceExecution one = new InterfaceExecution();
        InterfaceExecution2 two = new InterfaceExecution2();
        one.setUno(new InterfaceExecution2());
        System.out.println(one.getUno());
        two.setDos(new InterfaceExecution2());
        one.afk();
        two.sit();

        System.out.println(one.getUno());
        System.out.println(two.getDos());
        one.setUno(new InterfaceExecution());
        two.setDos(new InterfaceExecution());
        System.out.println(one.getUno()) ;

        System.out.println();


        Person Lukas = new Person();
        Lukas.setStrategy(new InterfaceExecution2());
        Lukas.play();

        Lukas.setStrategy(new InterfaceExecution());
        Lukas.play();
    }

}
