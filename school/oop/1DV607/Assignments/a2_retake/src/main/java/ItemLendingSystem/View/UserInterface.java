package ItemLendingSystem.View;

import java.util.Scanner;

public class UserInterface {
  
    private Scanner userInput;    
    private MemberView memberView;

    public UserInterface() {
        this.userInput = new Scanner(System.in);
        this.memberView = new MemberView(this.userInput);
    }

    public String input() {
        String input = this.userInput.nextLine();
        return input;
    }

    public String[] memberRegistration() {
        return this.memberView.promptMemberRegistration();
    }

    public void memberRegistrationResult(boolean registration) {
        this.memberView.memberRegistrationResult(registration);
    }
    
    public int mainMenu() {
        System.out.println("======== MENU \n"
        +"1. List members\n"
        +"2. Show all items\n"
        +"3. Register new member\n"
        
        );
        return getChar();


    }
    public int getChar() {
        try {
          int c = System.in.read();
          while (c == '\r' || c == '\n') {
            c = System.in.read();
          }
          return c;
        } catch (java.io.IOException e) {
          System.out.println("" + e);
          return 0;
        }
    }

}
