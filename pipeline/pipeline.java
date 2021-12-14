import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


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
    return p;
  }


  void startProcess(ProcessBuilder pb) {
    pb.start();
    System.out.println("== Process started ==");
  }


  ProcessHandle watchProcess(Process process) {

    ProcessHandle p = process.toHandle();
    return p;
  }


  static BufferedReader readInputStream() {
    return new BufferedReader(new InputStreamReader(process.getInputStream()));
  }

  static void displayProcess(Process p, testpipe pipe, String data) {
    long pid = p.pid();
    // Add if needed
    printf("PID: %ld \nTime: %d\nData: %s", pid, pipe.getTimestamp(), data);
  }


  public static void main(String[] args) throws IOException {
    
    testpipe pr = new testpipe();

    while(true) {
      String[] cmd = {"cat","<>","test-named-pipe"};
      ProcessBuilder pb = new ProcessBuilder(cmd);
         
      try {
        // Start process
        Process process = pb.start();  
        ProcessHandle processHandle = process.toHandle();

        System.out.printf("PID: %s    Time: %s    Process started %n", processHandle.pid(), pr.getTimestamp());

        // read content
        BufferedReader pipeData = readInputStream();
        displayProcess(p, pr, "");
        String line = "";
        
        while ((line = pipeData.readLine()) != null) {  
          displayProcess(p, pr, line);
          Thread.sleep(3000);
          }

        // tell that the pipe is closed
        System.out.printf("PID: %s    Time: %s    Pipe closed %n", processHandle.pid(), pr.getTimestamp());

        // wait three seconds
        TimeUnit.SECONDS.sleep(3);

      } catch (Exception e) {
        System.out.println("ERROR: "+e);
      }
    } 
  }	
}






