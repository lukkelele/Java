import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Date;



public class HTTPServer implements Runnable {

  private static Socket connection;
  private static int port;
  private static int default_port = 8888;
  private static final String default_path = "public";
  private static final String DEFAULT = "index.html";
  private static final String FILE_NOT_FOUND = "404.html";
  private static final String FOUND = "302.html";
  private static final String INTERNAL_SERVER_ERROR = "500.html";
  private static String path = "";
  private static final String redirect_url = "public/redirect.html";      // Hardcoded for 302 error message


  public HTTPServer(Socket s) {
    connection = s;                   
  }


  public static void main(String[] args) throws RuntimeException {
      
      int amount_args = args.length;
      if (amount_args != 2) {
        if (amount_args == 0) {
          System.out.println("\nTwo command line arguments have to be passed when executing \"java HTTPServer [arg1] [arg2]\".\n"+
          "[arg1] is the desired port to run the server on.\n[arg2] is the RELATIVE path to where the server directory is held.");
          System.out.println("============\nExample: java HTTPServer 5555 /dir\n=============");
          throw new RuntimeException();
        } else {
        System.out.println("\nTWO commands are to be passed!\n");
        // if arguments provided are more than 0 but not 2, set default values
        if (parseInt(args[0]) == true && amount_args == 1) {  // if the first argument is a number, it is considered to be a port
          port = Integer.parseInt(args[0]);
          path = default_path;            
          System.out.println("No path was provided, default path set.");
        } else {
          path = args[0];
          port = default_port;
          System.out.println("No port was provided, predefined port value set.");
        }
        args = new String[2];
        args[0] = String.valueOf(port);
        args[1] = path;
        }
      }

      Scanner user = new Scanner(System.in);
      int checkedPort = checkPortArg(args[0]); 
      String checkedPath = checkPathArg(args[1]);
      ServerSocket serverConnection = null;
        try {   
            // This infinite loop creates new threads if multiple connections are queueing at the chosen port
            serverConnection = new ServerSocket(port);     // Create socket for the server connection
            System.out.println("\n  ==== SERVER ====\n  PATH: "+checkedPath+"\n  PORT: "+checkedPort+"\n  ================\n\nlistening...");
            while (true) {
                HTTPServer server = new HTTPServer(serverConnection.accept());       // Create server with the socket that is listening for a connection
                Thread server_thread = new Thread(server);                  // Add the newly created HTTPServer object to a runnable thread
                server_thread.start();       // thread.start() to run the server on a separate thread to be able to manage it
      
            }
        }    
             catch (IOException b) {
                System.out.println("Error starting server!\nThe port is already in use!\nTry another one.\n"+b);
                try {
                  PrintWriter output = new PrintWriter(connection.getOutputStream());          // true for enabling autoflush
                  send_INTERNAL_SERVER_ERROR(output);
                 
                } catch (IOException c) {
                  System.out.println("SEVERE ISSUES ACCOMPLISHED"); 
                }
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
      s = in.readLine();                           // Read first line to get header info
      String[] header = s.split(" ");              // Split header in to three pieces
      method = header[0].trim().toUpperCase();     // Trim to remove potential whitespaces. Could use method.matches instead as well
      file_request = header[1].toLowerCase();      // keep file requests to lowercase since it is the standard

      if (file_request.trim().equals("http")) {    // If file request is "empty", for initial responses etc.
        System.out.println("File request empty.. setting default");
        file_request = DEFAULT;
      } else {
        file_request = file_request.split(" ")[0]; // If file request isn't empty, remove the "http" part 
      }
      file = new File(path, file_request);

      // HARDCODED 302 ERROR HANDLING
      if (file.getPath().equals("./public/redirect.html")) {
        file = new File(path, FOUND);
      }

      // POST
      if (method.equals("POST")) {
      }

      // GET
      if (method.equals("GET")) {
       if (file.isDirectory()) {  // if file requested is a directory, fetch the index.html file inside that directory
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
      try {
        send_FILE_NOT_FOUND(output, file_request);
      } catch (IOException n) {
            System.out.println("--- FILE NOT FOUND EXCEPTION - ERROR #2 ==> "+n);
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

    System.out.println("OUTGOING DATA: [FILE: "+file.getPath()+" | LENGTH: "+len_file+" | REQUESTED FILE ===> "+file.getPath()+"]");
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



  private void send_FILE_NOT_FOUND(PrintWriter out, String requested_file) throws IOException {
    File file;
    if (requested_file.equals("/redirect.html")) {  // for 302 error simulation
      file = new File(path, FOUND); 
      out.println("HTTP/1.1 302 Found");
    } else {
      file = new File(path, FILE_NOT_FOUND);
      out.println("HTTP/1.1 404 File Not Found");
    }
    int len_file = (int) file.length();   // cast to int since file.length is long;

    out.println("Server: cowabunga : 1.0");
    out.println("Date: " + new Date());
    out.println("Content-type: " + checkType(file.getName()));
    out.println("Content-length: " + len_file);
    out.println();
    out.flush();

    System.out.println("OUTGOING DATA: [FILE: "+file.getPath()+" | LENGTH: "+len_file+" | REQUESTED FILE ===> "+requested_file+"] <-- ERROR MESSAGE");
  }


  private static void send_INTERNAL_SERVER_ERROR(PrintWriter output) throws IOException {
    File file = new File(path, INTERNAL_SERVER_ERROR);
    int len_file = (int) file.length();
    output.println("HTTP/1.1 500 Internal Server Error");
    output.println("Server: HTTPServer.java : 1.0");
    output.println("Date: " + new Date());
    output.println("Content-type: text/html");
    output.println("Content-length: " + len_file);
    output.println();
    output.flush();

    System.out.println("OUTGOING DATA: [FILE: "+file.getPath()+" | LENGTH: "+len_file+" ] <-- ERROR MESSAGE");
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

  private static int checkPortArg(String arg) {
    try {
      int p = Integer.parseInt(arg);
      return port = p;
    } catch (Exception e) {
      System.out.println("Port argument INVALID!\nCorrected to predefined port "+default_port);
      // Set default value for invalid argument
      return port = 8888;
    }
  }

  private static String checkPathArg(String path) {
    try {
      Integer.parseInt(path);      // If error occurs, the string is NOT a number, hence being VALID
      System.out.println("Path is a number... Setting default path");
      path = default_path;
    } catch (NumberFormatException e) {
      // If the try fails above, enter here
      if (path.contains("..")) {  // 'cd ..' will go back one directory
        System.out.println("DIRECTORY ACCESS RESTRICTED!\nFound '..' in passed path parameter\nPATH SET TO DEFAULT.");
        path = default_path;
      }
    }  
    if (path.startsWith("/")) {
      path = "." + path;
    } else {
      path = "./" + path; 
    }
    return path;
  }

  // Method to return new output stream, is not necessary but it makes the code a bit easier to read
  BufferedOutputStream getSocketOutput() throws IOException {
    return new BufferedOutputStream(connection.getOutputStream());
  }

  // Method to return new input stream, is not necessary but it makes the code a bit easier to read
  BufferedReader getSocketInput() throws IOException {
    return new BufferedReader(new InputStreamReader(connection.getInputStream()));
  }


  private static boolean parseInt(String arg) {
    try {
      Integer.parseInt(arg);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
