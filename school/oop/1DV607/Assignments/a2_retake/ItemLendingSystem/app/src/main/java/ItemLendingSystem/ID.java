package ItemLendingSystem;

import java.util.UUID;

public class ID {
    
    private String id;

    public ID(int size) {
        this.id = this.generateId(size);
    }

    private String generateId(int size) {
        char[] strUUID = UUID.randomUUID().toString().toCharArray();
        char[] arrId = new char[size];
        for (int i=0 ; i < size ; i++) {
            arrId[i] = strUUID[i];
        }
        System.out.println("Created ID: "+String.valueOf(arrId));
        return String.valueOf(arrId);
    }

    public String getId() {
        return id;
    }

}
