import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Date;
import javax.imageio.ImageIO;


public class HTTPServer implements Runnable {

  private Socket connection;
  static final String root = "./public/";
  static final String DEFAULT = "index.html";
  static final String FILE_NOT_FOUND = "404.html";
  private static int port;
  private static String path = "";


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
        path = "./public/";
      }
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
              System.out.println("Server socket: LISTENING ON PORT "+port);
              while (true) {
                  HTTPServer server = new HTTPServer(serverConnection.accept());       // Create server with the socket that is listening for a connection
                  Thread server_thread = new Thread(server);                  // Add the newly created HTTPServer object to a runnable thread
                  server_thread.start();       // thread.start() to run the server on a separate thread to be able to manage it
                }    

              } catch (Exception e) {
                  System.out.println("Error: RUNNABLE TRY/CATCH CLAUSE\n"+e);
          }
      }
  }

  
  public void run() {

    BufferedReader in = null; 
    BufferedOutputStream out = null;
    PrintWriter output = null;

    try {
      in = getSocketInput();
      out = getSocketOutput();
      output = new PrintWriter(connection.getOutputStream());          // true for enabling autoflush
      File file = null;
      //System.out.println("Connection established!");
      String s, method, file_request;
      // Store request methods in a hashtable
      Hashtable<Integer, String> dict = new Hashtable<Integer, String>(); 
      dict.put(200, "OK");
      dict.put(302, "FOUND");
      dict.put(404, "NOT FOUND");
      dict.put(500, "INTERNAL SERVER ERROR");
      // Read request
      s = in.readLine();                           // Read first line to get mandatory info
      System.out.println("HEADER | " + s);
      String[] header = s.split(" ");              // Split header in to three pieces
      method = header[0].trim().toUpperCase();     // Trim to remove potential whitespaces. Could use method.matches instead as well
      file_request = header[1].toLowerCase();
      System.out.println("request ==> " + file_request);
      if (file_request.trim().equals("http")) {    // If file request is "empty", for initial responses etc.
        System.out.println("File request empty.. setting default");
        file_request = DEFAULT;
      } else {
        file_request = file_request.split(" ")[0]; // If file request isn't empty, remove the "http" part 
      }
      System.out.println("CREATING FILE >>>");
      try {
          file = new File(root, file_request);
          int len_file = (int) file.length();
      } catch (Exception e) {
          System.out.println("FILE NOT FOUND! CREATING DEFAULT FILE TO REPLACE THE REQUEST! ; "+e);
          file = new File(root, DEFAULT);
      }

      if (method.equals("GET")) {
       if (file.isDirectory()) {
          for (File f : file.listFiles()) {
            if (f.getName().contains("index.html")) {
              send_data(out, output, f);
            }
          }
        } else {
        send_data(out, output, file);
        }
      }
    } catch (IOException e) {
      System.out.println("IOException raised! || "+e);
      try {
        File default_file = new File(root, DEFAULT);    
        send_data(out, output, default_file); 
      } catch (IOException n) {
            System.out.println("Whoops!\n" + e);
      }
    }
  }


  private void send_data(BufferedOutputStream output_stream, PrintWriter output, File file) throws IOException { 
        byte[] file_data = read_file(file);
        int len_file = (int) file.length();
        output.println("HTTP/1.1 200 OK");
        output.println("Server: HTTPServer.java : 1.0");
        output.println("Date: " + new Date());
        output.println("Content-type: "+checkType(file.getName()));
        output.println("Content-length: " + len_file);
        output.println();
        output.flush();
        
        output_stream.write(file_data, 0, len_file);
        output_stream.flush();

        System.out.println("OUTGOING DATA: [FILE: "+file.getPath()+", LENGTH: "+len_file+" , REQUESTED FILE ===> "+file.getPath()+"]");
  }



  private byte[] read_file(File file) throws IOException {
    FileInputStream inputstream = null;          // needed for finally clause
    byte[] data = new byte[(int)file.length()];  // Create buffer to fit data
    String file_name = file.getName();
    try {
      if (file_name.endsWith(".png") || file_name.endsWith(".jpg")) {
      
      } else {
      inputstream = new FileInputStream(file);
      inputstream.read(data);
      }
    } finally {
        if (inputstream != null) inputstream.close();
    }
    return data;
  }



  private void send_FILE_NOT_FOUND(BufferedOutputStream output_stream, PrintWriter out) throws IOException {
    File file = new File(root, FILE_NOT_FOUND);
    int len_file = (int) file.length();   // cast to int since file.length is long
    byte[] data = read_file(file);

    out.println("HTTP/1.1 404 File Not Found");
    out.println("Server: cowabunga : 1.0");
    out.println("Date: " + new Date());
    out.println("Content-type: text/html");
    out.println("Content-length: " + len_file);
    out.println();
    out.flush();

    output_stream.write(data, 0, len_file);
    output_stream.flush();
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


  private String checkType(String request) {
    if (request.endsWith(".html") || request.endsWith(".html")) {
        return "text/html";
    } else if (request.endsWith(".jpg")) {
        return "image/jpeg";  
    } else if (request.endsWith(".png")) {
        return "image/png";
    } else {
        return "text/plain";
    }
  }


  public static boolean startup(String[] args) {
    System.out.println("!====================================!\n\n"+
        "Attempting to start server..");
    if (checkPortArg(args[0]) == false) return false;
    return true;
  }

  BufferedOutputStream getSocketOutput() throws IOException {
    return new BufferedOutputStream(connection.getOutputStream());
  }


  BufferedReader getSocketInput() throws IOException {
    return new BufferedReader(new InputStreamReader(connection.getInputStream()));
  }


}
