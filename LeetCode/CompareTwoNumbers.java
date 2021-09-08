import java.util.Scanner;

public class CompareTwoNumbers {

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        int firstNumber = user.nextInt();
        int secondNumber = user.nextInt();
        if (firstNumber>secondNumber){
            System.out.println(firstNumber+">"+secondNumber);
        } else {
            System.out.println(firstNumber+"<"+secondNumber);
        }
    }
    
}
