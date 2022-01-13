import java.util.Random;
import java.util.concurrent.Executors;
import java.util.time;
import java.util.ArrayList;

/*
 * File:	MultithreadedService.java
 * Course: 	21HT - Operating Systems - 1DV512
 * Author: Lukas Gunnarsson	
 * Date: 	January 2022
 */

// TODO: put this source code file into a new Java package with meaningful name (e.g., dv512.YourStudentID)!

// You can implement additional fields and methods in code below, but
// you are not allowed to rename or remove any of it!

// Additionally, please remember that you are not allowed to use any third-party libraries

public class MultithreadedService {

  int current_tasks = 0;
  ArrayList<Task> queue = new ArrayList<Task>();
  ExecutorService threadpool;          // Pool of threads

    // TODO: implement a nested public class titled Task here
    // which must have an integer ID and specified burst time (duration) in milliseconds,
    // see below
    // Add further fields and methods to it, if necessary
    // As the task is being executed for the specified burst time, 
    // it is expected to simply go to sleep every X milliseconds (specified below)

  public class Task {
    int id;
    int burst;
    int time_spent;
   
    public Task(int id) {
      this.id = id;
      this.burst = generateBurst();
      this.time_spent = 0;
      queue.add(this);            // Add task to queue
    }


    int generateBurst() {
      return rng.nextInt((maxBurstTimeMs-minBurstTimeMs))+minBurstTimeMs;     // Random number between allowed interval
    }
  }

  /*  
   * For executing tasks.
   */
  void sleep(Task t) {
    long current_time;
    long difference = 0;
    long start_t = System.nanoTime();
    while (t.time_spent < t.burst) {
      current_time = System.nanoTime(); 
      difference = current_time - start_t;
      t.time_spent = difference / 1000;
    }
  }


    // Random number generator that must be used for the simulation
	Random rng;
    // ... add further fields, methods, and even classes, if necessary
    

	public MultithreadedService (long rngSeed) {
        this.rng = new Random(rngSeed);
    }

  /* 
   * Resets all necessary elements to their initial states.
   */
	public void reset() {
      current_tasks = 0;                                              // Reset task counter
      queue.clear();
      threadpool = Executors.newFixedThreadPool(numThreads);          // New pool of threads
    }
   
  /* 
   * Pick a task randomly from the queue.
   */
  public Task pickTask(ArrayList<Task> tasks) {
    Task t;
    if (tasks.size() >= 0) {
      random_num = rng.nextInt(tasks.size());         // Get a number between 0 and length of queue
      t = tasks.get(random_num);                      // Gets a random task from the queue with the provided number from above
    }
    return t;
  }

  /* 
   * Pass task to a thread.
   */
  public void passTask(Task t) {
    threadpool.execute(t);    
  }

    // If the implementation requires your code to throw some exceptions, 
    // you are allowed to add those to the signature of this method
    public void runNewSimulation(final long totalSimulationTimeMs,
        final int numThreads, final int numTasks,
        final long minBurstTimeMs, final long maxBurstTimeMs, final long sleepTimeMs) {
        reset();
        LocalTime clock = new LocalTime();

        // TODO:
        // 1. Run the simulation for the specified time, totalSimulationTimeMs
        // 2. While the simulation is running, use a fixed thread pool with numThreads
        // (see https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/Executors.html#newFixedThreadPool(int) )
        // to execute Tasks (implement the respective class, see above!)
        // 3. The total maximum number of tasks is numTasks, 
        // and each task has a burst time (duration) selected randomly
        // between minBurstTimeMs and maxBurstTimeMs (inclusive)
        // 4. The implementation should assign sequential task IDs to the created tasks (0, 1, 2...)
        // and it should assign them to threads in the same sequence (rather any other scheduling approach)
        // 5. When the simulation time is up, it should make sure to stop all of the currently executing
        // and waiting threads!
        
        long start_time = clock.toSecondOfDay() * 1000;   // in milliseconds 
        long time_end = start_time + totalSimulationTimeMs;
        // Create tasks
        for (int k = 0 ; k < numTasks ; k++) {
          Task t = new Task(k);
          queue.add(t);
        }

        while (start_time < time_end) {


          start_time = clock.toSecondOfDay() * 1000; 
        }

    }


    public void printResults() {
        // TODO:
        System.out.println("Completed tasks:");
        // 1. For each *completed* task, print its ID, burst time (duration),
        // its start time (moment since the start of the simulation), and finish time
        
        System.out.println("Interrupted tasks:");
        // 2. Afterwards, print the list of tasks IDs for the tasks which were currently
        // executing when the simulation was finished/interrupted
        
        System.out.println("Waiting tasks:");
        // 3. Finally, print the list of tasks IDs for the tasks which were waiting for execution,
        // but were never started as the simulation was finished/interrupted
	}




    // If the implementation requires your code to throw some exceptions, 
    // you are allowed to add those to the signature of this method
    public static void main(String args[]) {
		// TODO: replace the seed value below with your birth date, e.g., "20001001"
		final long rngSeed = 19990520;  
				
        // Do not modify the code below — instead, complete the implementation
        // of other methods!
        MultithreadedService service = new MultithreadedService(rngSeed);
        
        final int numSimulations = 3;
        final long totalSimulationTimeMs = 15*1000L; // 15 seconds
        
        final int numThreads = 4;
        final int numTasks = 30;
        final long minBurstTimeMs = 1*1000L; // 1 second  
        final long maxBurstTimeMs = 10*1000L; // 10 seconds
        final long sleepTimeMs = 100L; // 100 ms

        for (int i = 0; i < numSimulations; i++) {
            System.out.println("Running simulation #" + i);

            service.runNewSimulation(totalSimulationTimeMs,
                numThreads, numTasks,
                minBurstTimeMs, maxBurstTimeMs, sleepTimeMs);

            System.out.println("Simulation results:"
					+ "\n" + "----------------------");	
            service.printResults();

            System.out.println("\n");
        }

        System.out.println("----------------------");
        System.out.println("Exiting...");
        
        // If your program has not completed after the message printed above,
        // it means that some threads are not properly stopped! -> this issue will affect the grade
    }
}
