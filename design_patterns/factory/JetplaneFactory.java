package factory;

public class JetplaneFactory implements AirplaneFactory {
    
    @Override
    public Airplane createAirplane() {
        return new Jetplane();
    }

    @Override
    public Airplane createAirplane(String name) {
        Airplane newJet = new Jetplane();
        newJet.setName(name);
        return newJet;
    }
    

}
