import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.Flushable;


public class Task {

    String path = "";

	public String getTimestamp() {
    // time formating
    LocalTime currentTime = LocalTime.now();
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    String formattedTime = currentTime.format(timeFormat);
    return formattedTime;
  }

  public static void main(String[] args) throws Exception {

    int iterations = 10000;         /* how many loops */
    int totalIterations = 500;
    int sleep = 10;                 /* in milliseconds */


		try {
			Task t = new Task(); 
			boolean directoryCreation = new File(path).mkdirs();

			if (directoryCreation != false) {
        System.out.println("\nDirectory was successfully created.\n");
        
        for(int i = 0 ; i<totalIterations ; i++) {
          System.out.println("Running new file..");
          for(int k = 0; k < iterations; k++) {
            String time = t.getTimestamp();
            File f = new File(path+time);
            System.out.printf("new file named ==> %s\n", time);
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "utf-8"));

            writer.write(time);
           
            Thread.sleep(sleep);
            // Flush buffer
            writer.flush();

            System.out.println("File flushed.");
            writer.close();
          }
        } 

      } else { System.out.println("\nThe directory specified could not be created, aborting execution.\n"); }
    } catch (Exception e) {
			System.out.println("ERROR: "+e);
		}	
	}
}
