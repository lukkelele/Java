import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
          System.out.println("New server socket created.");
          System.out.println("Server socket: LISTENING ON PORT "+port);
          Socket serverSocket = serverConnection.accept();                                  // Listen to chosen port and accept incoming connection
          System.out.println("Connection established!");
          HTTPServer server = new HTTPServer(serverSocket);       // Create server with the socket that is listening for a connection
 
          Thread server_thread = new Thread(server);                  // Add the newly created HTTPServer object to a runnable thread
          server_thread.start();       // thread.start() to run the server on a separate thread to be able to manage it


        } catch (Exception e) {
            System.out.println("Error: RUNNABLE TRY/CATCH CLAUSE\n"+e);
        }
      }
  }

  
  public void run() {

    try {
      BufferedReader in = getSocketInput();
      BufferedOutputStream out = getSocketOutput();
      PrintWriter output_terminal = new PrintWriter(connection.getOutputStream());          // true for enabling autoflush

      System.out.println("Server: RUNNING");
      read_message(in);
      output_terminal.println("Welcome!");

      while (true) {
      }
      // GET shall only be handled
      

    } catch (IOException e) {
      System.out.println("Server: DOWN");
    }
  }

  
  String read_message(BufferedReader in) {
    String s = "";
    try {
      for (int k = 0 ; k < 10 ; k++) {
        s += in.readLine() + "\n";
        System.out.println(in.readLine());
      }
    } catch (Exception e) {};
    System.out.println("PRINTING s..\n"+s);
    return s;
  }


  /**
   * Checks command line arguments if they are valid or not.
   */
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


  BufferedOutputStream getSocketOutput() throws IOException {
    return new BufferedOutputStream(connection.getOutputStream());
  }


  BufferedReader getSocketInput() throws IOException {
    return new BufferedReader(new InputStreamReader(connection.getInputStream()));
  }


}
