import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class HTTPServer implements Runnable {

  static final File root = new File(".");
  static final String FILE_NOT_FOUND = "404.html";
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
      path = args[1];
      if (path.split("/").length > 1) {
        System.out.println("DIRECTORY ACCESS RESTRICTED!");
        path = "public";
      }
      System.out.println(port+" | "+path);
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
              // This infinite loop creates new threads if multiple connections are queueing at the chosen port
              System.out.println("Starting server on port "+port+"...");
              ServerSocket serverConnection = new ServerSocket(port);     // Create socket for the server connection
              while (true) {
                  System.out.println("Server socket: LISTENING ON PORT "+port);
                  HTTPServer server = new HTTPServer(serverConnection.accept());       // Create server with the socket that is listening for a connection

                  System.out.println("Assigning connection to a separate thread..");
                  Thread server_thread = new Thread(server);                  // Add the newly created HTTPServer object to a runnable thread
                  server_thread.start();       // thread.start() to run the server on a separate thread to be able to manage it
                }    

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
      System.out.println("Connection established!");
      String s, method, version, version_no;
      // Store request methods in a hashtable
      Hashtable<Integer, String> dict = new Hashtable<Integer, String>(); 
      dict.put(200, "OK");
      dict.put(302, "FOUND");
      dict.put(404, "NOT FOUND");
      dict.put(500, "INTERNAL SERVER ERROR");
      // Read request
      while ((s = in.readLine()) != null) {   // Read recieved data
        System.out.println(s);
      }
      String[] header = s.split("\n");
      String[] head = header[0].split("/");   // First line of the header [METHOD, VERSION, VERSION_NO]
      method = head[0];
      version = head[1];
      version_no = head[2];


    } catch (IOException e) {
      System.out.println("Server: DOWN");
    }
  }


  void send_OK(PrintWriter out, File file) {
    out.println("HTTP/1.1 200 OK");
    out.println("Server: HTTPServer.java : 1.0");
    out.println("Date: " + new Date());
    out.println("Content-type: text/html");
    out.println("Content-length: " + file.length());
    out.println();
  }

  private byte[] read_file(File file) throws IOException {
    FileInputStream inputstream = null;
    byte[] data = new byte[file.length()];  // Create buffer to fit data

    try {
      inputstream = new FileInputStream(file);
      inputstream.read(data);
    } finally {
        if (inputstream != null) inputstream.close();
    }
    return data;
  }

  /**
   * Checks command line arguments if they are valid or not.
   */
  public static boolean startup(String[] args) {
    System.out.println("!====================================!\n\n"+
        "Attempting to start server..");
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
