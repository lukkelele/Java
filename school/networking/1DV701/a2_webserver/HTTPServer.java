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



public class HTTPServer implements Runnable {

  private Socket connection;
  private static int port;
  private static int default_port = 8888;
  private static final String root = "./public/";
  private static final String DEFAULT = "index.html";
  private static final String FILE_NOT_FOUND = "404.html";
  private static final String FOUND = "302.html";
  private static String path = "";

  private static final String redirect_url = "./public/redirect.html";      // Hardcoded for 302 error message
  private static final String redirect_location =  "clown.png";


  public HTTPServer(Socket s) {
    connection = s;                   
  }


  public static void main(String[] args) {
      Scanner user = new Scanner(System.in);
      path = checkPathArg(args[1]);
      
      int c = boot(args);
          // START SERVER
        ServerSocket serverConnection = null;
        try {   
            // This infinite loop creates new threads if multiple connections are queueing at the chosen port
            serverConnection = new ServerSocket(port);     // Create socket for the server connection
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

  
  public void run() {

    BufferedReader in = null; 
    BufferedOutputStream out = null;
    PrintWriter output = null;
    File file = null;
    String file_request = null;

    try {
      in = getSocketInput();
      out = getSocketOutput();
      output = new PrintWriter(connection.getOutputStream());          // true for enabling autoflush
      String s, method;

      // Read request
      s = in.readLine();                           // Read first line to get mandatory info
      String[] header = s.split(" ");              // Split header in to three pieces
      method = header[0].trim().toUpperCase();     // Trim to remove potential whitespaces. Could use method.matches instead as well
      file_request = header[1].toLowerCase();
      System.out.println("==> FILE REQUEST ==> "+file_request);
      // Find out the name of the file to respond with
      if (file_request.trim().equals("http")) {    // If file request is "empty", for initial responses etc.
        System.out.println("File request empty.. setting default");
        file_request = DEFAULT;
      } else if (file_request.equals("redirect.html")) {
          System.out.println("Sending FOUND error!");
          send_FOUND_ERROR(out, output);
      } else {
        file_request = file_request.split(" ")[0]; // If file request isn't empty, remove the "http" part 
      }
      try {
          file = new File(root, file_request);
          int len_file = (int) file.length();
      } catch (Exception e) {
          System.out.println("FILE NOT FOUND!");
          // Check if there is a redirect path available
      }
      
      // For GET methods
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
      System.out.println("IOException raised! || file_request = " + file_request + " || "+e);
      if (file_request.equals("/redirect.html")) {
        try {
          send_FOUND_ERROR(out, output);
        } catch (IOException n) {
              System.out.println("Whoops!\n" + e);
        }
      } else {
        try {
          send_FILE_NOT_FOUND(out, output, file_request);
        } catch (IOException n) {
              System.out.println("Whoops!\n" + e);
        }
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
    try {
      inputstream = new FileInputStream(file);
      inputstream.read(data);
    } finally {
        if (inputstream != null) inputstream.close();
    }
    return data;
  }


  private void send_FOUND_ERROR(BufferedOutputStream output_stream, PrintWriter output) throws IOException { 
    System.out.println("=-=-=-=-=-=-=-=- Entered FOUND ERROR ==> ");
    File file = new File(root, redirect_location);
    int len_file = (int) file.length();
    output.println("HTTP/1.1 302 FOUND");
    output.println("Server: HTTPServer.java : 1.0");
    output.println("Date: " + new Date());
    output.println("Content-type: "+checkType(file.getName()));
    output.println("Content-length: " + len_file);
    output.println();
    output.flush();
    
    System.out.println("OUTGOING DATA: [FILE: "+file.getPath()+", LENGTH: "+len_file+" , REQUESTED FILE ===> "+file.getPath()+"]");
  }


  private void send_FILE_NOT_FOUND(BufferedOutputStream output_stream, PrintWriter out, String requested_file) throws IOException {
    System.out.println("=-=-=-=-=-=-=-=- Entered FILE NOT FOUND ==> "+requested_file);
    File file;
    try {
      if (requested_file.equals("/redirect.html")) {
         System.out.println("FILE REQUESTED IS FOUND AT ANOTHER LOCATION!");
         send_FOUND_ERROR(output_stream, out);
      } else {
        file = new File(root, FILE_NOT_FOUND);
        System.out.println("FILE NOT FOUND | SENDING ERROR MESSAGE!");
        int len_file = (int) file.length();   // cast to int since file.length is long;
        out.println("HTTP/1.1 404 File Not Found");
        out.println("Server: cowabunga : 1.0");
        out.println("Date: " + new Date());
        out.println("Content-type: " + checkType(requested_file));
        out.println("Content-length: " + len_file);
        out.println();
        out.flush();

        System.out.println("OUTGOING DATA: [FILE: "+file.getPath()+", LENGTH: "+len_file+" , REQUESTED FILE ===> "+file.getPath()+"]");
      }
    } finally {
      System.out.println("Done");
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


  private static int boot(String[] args) {
    int c = 0;
    if (checkPortArg(args[0]) == true) {    // if valid command line argument
      c = 1;                                // For switch clause
    } 
    return c;
  }


  private static boolean checkPortArg(String arg) {
    System.out.println("Checking validity of port argument..");
    try {
      int p = Integer.parseInt(arg);
      System.out.println("Port argument VALID!");
      port = p;
      return true;
    } catch (Exception e) {
      System.out.println("Port argument INVALID!\nCorrected to predefined port "+default_port);
      // Set default value for invalid argument
      port = 8888;
      return false;
    }
  }


  private static String checkPathArg(String path) {
    boolean flag;
    try {
      Integer.parseInt(path);      // If error occurs, the string is NOT a number, hence being VALID
      System.out.println("Path argument INVALID!");
      path = "public";
    } catch (NumberFormatException e) {
      System.out.println("Path argument VALID!");
    }  
    if (path.startsWith("..")) {
      System.out.println("DIRECTORY ACCESS RESTRICTED!\nPATH SET TO DEFAULT.");
      path = "public";
    }
    if (path.endsWith("/")) path = path.replace("/", "");
    if (path.startsWith("./")) path = path.replace("./", "");
    path = "./" + path + "/";
    System.out.println("MODIFIED PATH ==> " + path);
    return path;
  }


  BufferedOutputStream getSocketOutput() throws IOException {
    return new BufferedOutputStream(connection.getOutputStream());
  }


  BufferedReader getSocketInput() throws IOException {
    return new BufferedReader(new InputStreamReader(connection.getInputStream()));
  }


}
