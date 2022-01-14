import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;
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
  ExecutorService executor;          // Pool of threads
  ThreadPoolExecutor threadpool;

    // TODO: implement a nested public class titled Task here
    // which must have an integer ID and specified burst time (duration) in milliseconds,
    // see below
    // Add further fields and methods to it, if necessary
    // As the task is being executed for the specified burst time, 
    // it is expected to simply go to sleep every X milliseconds (specified below)

  public class Task implements Runnable {
    int id;
    int burst;
    int time_spent;
   
    public Task(int id, long maxBurstTime, long minBurstTime) {
      this.id = id;
      this.burst = (int) generateBurst(maxBurstTime, minBurstTime);
      this.time_spent = 0;
      queue.add(this);            // Add task to queue
    }


    long generateBurst(long maxBurstTimeMs, long minBurstTimeMs) {
      return rng.nextLong((maxBurstTimeMs-minBurstTimeMs))+minBurstTimeMs;     // Random number between allowed interval
    }

    public void run() {

      try {
        System.out.println("TASK RUNNING\nid: "+this.id);
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
      }
      catch (InterruptedException e) {
        System.out.println("Thread interrupted! ----> "+this.id);
      }
    }

  }

  /*  
   * For executing tasks.
   */
  void sleep(Task t) throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(t.burst);
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
	public void reset(int numThreads) {
      current_tasks = 0;                                      // Reset task counter
      queue.clear();
      executor = Executors.newFixedThreadPool(numThreads);    // New pool of threads
      threadpool = (ThreadPoolExecutor) executor;             // Cast executor to ThreadPoolExecutor to gather pooldata
    }
   
  /* 
   * Pick a task randomly from the queue.
   */
  public Task pickTask(ArrayList<Task> tasks) {
    Task t;
    if (tasks.size() >= 0) {
      int random_num = rng.nextInt(tasks.size());                 // Get a number between 0 and length of queue
      t = tasks.get(random_num);                              // Gets a random task from the queue with the provided number from above
      return t;
    }
    return null;
  }

  /*
   * Count active threads.
   */
  public int getActiveThreads() {
    return threadpool.getActiveCount();
  }

  public int getTotalThreads() {
    return threadpool.getPoolSize();
  }

  public int getInactiveThreads() {
    return (threadpool.getPoolSize() - threadpool.getActiveCount());
  }

  public int[] getTaskInfo(Task t) {
    int id = t.id;
    int burst = t.burst;
    int worktime = t.time_spent;
    int time_left = burst - worktime;
    int[] s = {id, burst, worktime, time_left};
    return s;
  }

  /* 
   * Pass task to a thread.
   */
  public void passTask(Task t) {
    executor.execute(t);    
  }

    // If the implementation requires your code to throw some exceptions, 
    // you are allowed to add those to the signature of this method
    public void runNewSimulation(final long totalSimulationTimeMs,
        final int numThreads, final int numTasks,
        final long minBurstTimeMs, final long maxBurstTimeMs, final long sleepTimeMs) {
        reset(numThreads);

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
        double start_time = getCurrentTimeMs();   // in milliseconds 
        double time_end = start_time + totalSimulationTimeMs;
        System.out.println("END TIME = "+time_end);
        
        // Create tasks
        for (int k = 0 ; k < numTasks ; k++) {
          Task t = new Task(k, maxBurstTimeMs, minBurstTimeMs);
          queue.add(t);
        }
        start_time = getCurrentTimeMs(); 
        System.out.println("STARTING TIME = "+start_time);
        System.out.println("Start - End = "+(time_end - start_time));
        while (start_time < time_end) {


        }

    }

    public double getCurrentTimeMs() {
      return LocalTime.now().toNanoOfDay() / Math.pow(10, 6);
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

