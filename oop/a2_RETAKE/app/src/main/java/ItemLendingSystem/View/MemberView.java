package ItemLendingSystem.View;

import java.util.Scanner;

public class MemberView {
  
    private Scanner userInput;
    
    public MemberView(Scanner userInput){
        this.userInput = userInput;
    }

    public String[] promptMemberRegistration() {
        String firstName, lastName, phone, email;
        System.out.println("==> MEMBER REGISTRATION");
        System.out.println("1. Enter your first name");
        firstName = this.userInput.nextLine();
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

    public void memberRegistrationResult(boolean registration) {
        if (registration == false) {
            System.out.println("==> MEMBER REGISTRATION FAILED!");
        } else {
            System.out.println("==> MEMBER REGISTRATION SUCCESS!");
        }
    }

    public void displayMemberInfo(String[] member) {
        String firstName, lastName, phone, email, credits;
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
