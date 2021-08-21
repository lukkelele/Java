package factory;

public class DoodooFactory implements AirplaneFactory {
    
    @Override
    public Airplane createAirplane() {
        return new Doodooplane();
    }

    @Override
    public Airplane createAirplane(String name) {
        Airplane newDoodoo = new Doodooplane();
        newDoodoo.setName(name);
        return newDoodoo;

    }
    
}
