import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class testpipe {
// Group 25: Pierre Oskarsson, Johan Gustafsson, Lukas Gunnarsson
// Course: 1DV512

  public String getTimestamp() {
    // time formating
    LocalTime time = LocalTime.now();
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    String formatedTime = time.format(timeFormat);
    return formatedTime;
  }


  ProcessBuilder fork(String[] args) {
    ProcessBuilder p;
    p = new ProcessBuilder(args);
    return p;
  }


  static Process startProcess(ProcessBuilder pb) {
    try {
      return pb.start();
    } catch (Exception e) {
    System.out.println("== Process started =="); 
    return null;
    }
  }


  ProcessHandle watchProcess(Process process) {

    ProcessHandle p = process.toHandle();
    return p;
  }


  static BufferedReader readInputStream(Process process) {
    return new BufferedReader(new InputStreamReader(process.getInputStream()));
  }

  static void displayProcess(Process p, testpipe pipe, String data) {
    long pid = p.pid();
    // Add if needed
    System.out.printf("\nPID: %d \nTime: %s\nData: %s", pid, pipe.getTimestamp(), data);
  }

  


  public static void main(String[] args) throws IOException, InterruptedException {
    
    testpipe pipe = new testpipe();
    String[] cmd = {"cat","<>","pls"};
    ProcessBuilder pb = new ProcessBuilder(cmd);
    Process p = startProcess(pb);
    BufferedReader output = readInputStream(p);
    String line = "";

    while(true) {
      while(line != null) {
        line = output.readLine();
        displayProcess(p, pipe, line);
        Thread.sleep(2000);
      }
      pb = new ProcessBuilder(cmd);
      p = startProcess(pb);
      output = readInputStream(p);
      line = "";
    }
  }
}






