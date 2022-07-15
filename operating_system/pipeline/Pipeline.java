import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


public class Pipeline {


  public static void main(String[] args) {
    
    String[] cmd = ""; // Process creator
    ProcessBuilder pb = new ProcessBuilder(args);
    
    Process parent = pb.start();
    cmd = "";         // Command args for child process 
    pb = new ProcessBuilder();
    Process child  = pb.start();

    ProcessHandle parentHandler = parent.toHandle();
    ProcessHandle childHandler  = child.toHandle();

    // Communicate 
    //

    while (true) { }

  }

  static BufferedReader readInputStream(Process process) {
      return new BufferedReader(new InputStreamReader(process.getInputStream()));
  } 
}
