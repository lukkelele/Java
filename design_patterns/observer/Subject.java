package observer;

public interface Subject {
    
    void notify(String message);
    void register(observer o);
    void unregister(observer o);

}
