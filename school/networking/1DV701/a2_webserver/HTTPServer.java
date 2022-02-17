import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HTTPServer {

  static int port;
  static String path = "";

  public HTTPServer(Socket s) {
  }


  public static boolean startup(String[] args) {
    System.out.println("!====================================!\nAttempting to start server..");
    if (checkPortArg(args[0]) == false) return false;
    return true;
  }


  // Take command line arguments, first PORT then relative path
  public static void main(String[] args) {
    // first  arg --> port  --> parseInteger 
    // second arg --> relative path --> String
    Scanner user = new Scanner(System.in);
    int c = 0;
    boolean start = startup(args);
    if (start == true) {
      c = 1;
      port = Integer.parseInt(args[0]);
    };

    switch(c) {
      case 0:
        // FIX ARGUMENTS
        String input = "";
        boolean arg_flag = false;
        while (arg_flag == false) {
          try {
          input = user.nextLine();
          arg_flag = checkPortArg(input);
          } catch (Exception e) {}
        } 
        port = Integer.parseInt(input);
      case 1:
        // START SERVER
        System.out.println("Starting server on port "+port+"...");
        break;
    }

  }


  static boolean checkPortArg(String arg) {
    System.out.println("CHECKING PORT ARGUMENT");
    try {
      Integer.parseInt(arg);
      System.out.println("ARGUMENT VALID!");
      return true;
    } catch (Exception e) {
      System.out.println("ARGUMENT INVALID!");
      return false;
    }
  }

}
