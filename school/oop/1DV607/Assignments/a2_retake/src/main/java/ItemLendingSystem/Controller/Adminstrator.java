package ItemLendingSystem.Controller;

import ItemLendingSystem.View.UserInterface;
import ItemLendingSystem.Model.Admin;

public class Adminstrator {
    
    private UserInterface view;
    private Admin admin;
   
    public Adminstrator(Admin admin, UserInterface view) {
        this.admin = admin;
        this.view = view;
    }
    
    public boolean registerMember() {
        String[] memberInfo = this.view.memberRegistration();
        boolean registration = admin.registerMember(admin.createNewMember(memberInfo[0], memberInfo[1], memberInfo[2], memberInfo[3]));
        view.memberRegistrationResult(registration);
        return registration;
    }
    
    public boolean start() {
        int u = view.mainMenu();
        switch (u) {
            case '3': registerMember();
            case 'q':
                    System.out.println("QUIT!");
                    return false;
            default: ;
        } 
        return true;
    }

}

