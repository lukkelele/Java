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
        String firstName, lastName, phone, email;
        System.out.println("==> MEMBER REGISTRATION");
        System.out.println("1. Enter your first name");
        userInput.nextLine();
        firstName = userInput.nextLine();   // Consume '\n'
        System.out.println("2. Enter your last name");
        lastName = this.userInput.nextLine();
        System.out.println("3. Enter your number");
        phone = this.userInput.nextLine();
        System.out.println("4. Enter your email");
        email = this.userInput.nextLine();
        System.out.println("==> Member registration started!");
        String[] memberInfo = {firstName, lastName, phone, email};
        return memberInfo;
    }
    
    public int mainMenu() {
        System.out.println("======== MENU \n"
        +"1. List members\n"
        +"2. Show all items\n"
        +"3. Register new member\n"
        
        );
        int i = getChar();
        return i;

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

    public void memberRegistrationResult(boolean registration) {
        if (registration == false) {
            System.out.println("==> MEMBER REGISTRATION FAILED!");
        } else {
            System.out.println("==> MEMBER REGISTRATION SUCCESS!");
        }
    }

    public void displayMemberInfo(String[] member) {
        String firstName, lastName, phone, email, credits, items;
        firstName = member[0];
        lastName = member[1];
        phone = member[2];
        email = member[3];
        credits = member[4];
        items = member[5];
        System.out.println("==> Member\n"
        +"Firstname: " + firstName + "\n"
        +"Lastname: " + lastName + "\n"
        +"Phone: " + phone + "\n"
        +"Email: " + email + "\n"
        +"Credits: " + credits + "\n"
        +"Owned items: \n===\n" + items + "\n"
        );
    }

    public void displayMemberInfoVerbose(String[] member) {
        String firstName, lastName, phone, email, credits, items;
        firstName = member[0];
        lastName = member[1];
        phone = member[2];
        email = member[3];
        credits = member[4];
        System.out.println("==> Member\n"
        +"Firstname: " + firstName + "\n"
        +"Lastname: " + lastName + "\n"
        +"Phone: " + phone + "\n"
        +"Email: " + email + "\n"
        +"Credits: " + credits + "\n"
        );
    }

}
