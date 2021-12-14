package observer;

import java.util.ArrayList;

public class CommandVessel implements Subject {
    
    ArrayList<observer> observerList = new ArrayList<>();
    private String message;
    private String name;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {

        this.message = message;
        notify(message);

    }

    public String getMessage() {
        return message;
    }


    @Override
    public void notify(String message) {

        for (observer o : observerList){
            o.update(message);
        }
    }
  
    @Override
    public void register(observer o) {
        
        observerList.add(o);
    }

    @Override
    public void unregister(observer o) {
        
        o.reset();      // to reset observers message plus subscription name
        observerList.remove(o);
    }

}
