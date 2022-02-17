import java.util.InputMismatchException;
import java.util.Scanner;


public class ErrorHandler {

  int defaultPort = 8888;
  Scanner input;

  public ErrorHandler() {
    input = new Scanner(System.in);
  }


  public int getPort() {
    int p = 0;
    System.out.println("Enter a port number: ");
    try {
      System.out.println("Re-enter port..");
      p = input.nextInt(); 
    } catch (Exception e) {
      System.out.println("Error.. re-enter..");
    }
    return p;
  }

  public String getPath() {

    return "";
  }

  public boolean checkPortArg(String arg) {
    System.out.println("Entering checkPortArg()");
    try {
      int port = Integer.parseInt(arg);
      System.out.println("Port argument valid!");
      return true;
    } catch (Exception e) {
      System.out.println("Port argument invalid.");
      return false;
    }
  }
  
  public boolean checkPort(String arg) {
    System.out.println("Checking command line arguments if they are valid..");
    try {
      Integer.parseInt(arg);  
      return true;
    } catch (Exception e) {
      System.out.println("ERROR"+e);
      return false;
    }
      
  }

}
