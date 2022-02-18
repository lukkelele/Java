import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;

public class HTTPServer implements Runnable {

  private static int port;
  private static String path = "";
  private Socket connection;


  /**
   * Constructor
   *
   * @param s is the socketto connect to
   */
  public HTTPServer(Socket s) {
    connection = s;                   
  }


  public static void main(String[] args) {
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
          // Runnable try/catch 
    try {   
          System.out.println("Starting server on port "+port+"...");
          ServerSocket serverConnection = new ServerSocket(port);     // Create socket for the server connection
          serverConnection.accept();                                  // Listen to chosen port and accept incoming connection
          HTTPServer server = new HTTPServer(serverConnection);       // Create server with the socket that is listening for a connection
 
          Thread server_thread = new Thread(server);                  // Add the newly created HTTPServer object to a runnable thread
          server_thread.start();       // thread.start() to create thread instead of running it directly, for multithread purposes


        } catch (Exception e) {
            System.out.println("Error: RUNNABLE TRY/CATCH CLAUSE\n"+e);
        }
      }
  }

  
  public void run() {

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
