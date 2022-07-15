package factory;

public class FactoryMain {
    
    public static void main(String[] args) {

        AirplaneFactory DoodooFactory = new DoodooFactory();
        AirplaneFactory JetplaneFactory = new JetplaneFactory();

        Airplane Doodoo = DoodooFactory.createAirplane();
        Airplane Jetplane = JetplaneFactory.createAirplane();
        Airplane Jetplane2 = JetplaneFactory.createAirplane("Britta");
        //

        System.out.println(Doodoo.getName()+" -|- "+Doodoo.getClass().toString());
        System.out.println(Jetplane.getName()+" -|- "+Jetplane.getClass().toString());
        System.out.println(Jetplane2.getName()+" -|- "+Jetplane2.getClass().toString());
        

        Doodoo.setName("Doowdoow");
        Jetplane.setName("Jetty");
        Jetplane2.setName("Brettaw");


        System.out.println(Doodoo.getName()+" -|- "+Doodoo.getClass().toString());
        System.out.println(Jetplane.getName()+" -|- "+Jetplane.getClass().toString());
        System.out.println(Jetplane2.getName()+" -|- "+Jetplane2.getClass().toString());

    }
}
