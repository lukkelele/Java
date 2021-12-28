package factory;

public interface AirplaneFactory {

    Airplane createAirplane();
    Airplane createAirplane(String name);

}
