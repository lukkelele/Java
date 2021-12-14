package observer;

public class runtime {
    

    public static void main(String[] args) {
        
        // subject to subscribe the observers to
        CommandVessel subject = new CommandVessel();
        subject.setName("Subject 1");

        PilotObserver one = new PilotObserver();
        PilotObserver two = new PilotObserver();
        PilotObserver three = new PilotObserver("Lukas");


        // Register observers to subject
        subject.register(one);
        subject.register(two);
        subject.register(three);

        System.out.println("1. "+one.getCurrentMessage()+". Name: "+one.getType()+"\n2. "+two.getCurrentMessage()+". Name: "+two.getType()+"\n3. "+three.getCurrentMessage()+". Name: "+three.getType());
        
        // Notify observers with a message
        subject.notify("LLLL");
        
        System.out.println("1. "+one.getCurrentMessage()+". Name: "+one.getType()+"\n2. "+two.getCurrentMessage()+". Name: "+two.getType()+"\n3. "+three.getCurrentMessage()+". Name: "+three.getType());
        
        // Test unregistering
        subject.unregister(two);
        System.out.println("1. "+one.getCurrentMessage()+". Name: "+one.getType()+"\n2. "+two.getCurrentMessage()+". Name: "+two.getType()+"\n3. "+three.getCurrentMessage()+". Name: "+three.getType());


    }

}