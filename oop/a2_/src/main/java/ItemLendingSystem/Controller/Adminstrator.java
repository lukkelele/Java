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
        DataLoader.loadHardcodedData(this.admin);

        int u = 0;

        while (u != 'q') {
            u = this.view.mainMenu();
            switch (u) {
                case '1': // List members
                    listMembers(false);
                    break;     
                case '2': // List members verbose
                    listMembers(true);
                    break;
                case '3': // Register member
                    registerMember();
                    break;
                case 'q':
                        System.out.println("QUIT!");
                        return false;
                default: break;
            } 
        }
        return true;
    }

    public void listMembers(boolean verbose) {
        String[][] membersInfo = this.admin.getMembersInformation(verbose);
        for (int i=0 ; i < membersInfo.length ; i++) {
            this.view.displayMemberInfo(membersInfo[i]);
        }
    }


}

