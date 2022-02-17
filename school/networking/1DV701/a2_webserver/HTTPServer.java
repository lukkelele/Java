import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HTTPServer {

  private static int port;
  private static String path = "";
  private Socket connection;



  public HTTPServer(Socket s) {
    connection = s;                   
  }



  public static void main(String[] args) implements Runnable {
    try {   
      Scanner user = new Scanner(System.in);
      int c = boot(args);
      switch(c) {

        case 0:
          // FIX FAULTY ARGUMENTS
          String input = "";
          boolean arg_flag = false;
          while (arg_flag == false) {
            try {
            input = user.nextLine();
            arg_flag = checkPortArg(input);
            } catch (Exception e) {}
          } 
          port = Integer.parseInt(input);
          user.close();
        case 1:
          // START SERVER
          System.out.println("Starting server on port "+port+"...");
          ServerSocket serverConnection = new ServerSocket(port);
          serverConnection.accept();    // Listen to chosen port and accept incoming connection
          HTTPServer server = new HTTPServer(serverConnection);
          

          break;
        }
    } catch (Exception e) {
      System.out.println("Error: RUNNABLE TRY/CATCH CLAUSE\n"+e);
    }
  }


  public static boolean startup(String[] args) {
    System.out.println("!====================================!\nAttempting to start server..");
    if (checkPortArg(args[0]) == false) return false;
    return true;
  }


  static int boot(String[] args) {
    int c = 0;
    boolean start = startup(args);
    if (start == true) {
      c = 1;        // Indicates that server is ready to start
      port = Integer.parseInt(args[0]);
    };

    return c;
  }


  static boolean checkPortArg(String arg) {
    System.out.println("Checking validity of port argument..");
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
