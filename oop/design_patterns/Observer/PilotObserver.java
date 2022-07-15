package observer;


// Example

public class PilotObserver implements observer {

    private String type;
    private String currentMessage;
    

    PilotObserver() {
        this.type = "Pilot";
    }

    PilotObserver(String name) {
        this.type = ("Pilot: "+name);
    }

    public String getType() {
        return type;
    }


    @Override
    public void update(String message) {
        this.currentMessage = message;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void reset() {
        this.type = null;
        this.currentMessage = null;
    }
    
}
