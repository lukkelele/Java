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
  ArrayList<Task> completed_tasks = new ArrayList<Task>();
  ExecutorService executor;          // Pool of threads --> ADD NUMTHREADS AS
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
      displayTaskInfo(this);
      double starting_time = getCurrentTimeMs();
      try {
        sleep(this);
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
    double start = getCurrentTimeMs();
    try {
      TimeUnit.MILLISECONDS.sleep(t.burst);
      t.time_spent = t.burst;
      queue.remove(0);
      completed_tasks.add(t);
      displayTaskInfo(t);
    } catch (InterruptedException e) {
      t.time_spent = (int)(getCurrentTimeMs() - start);
    }
    }


	Random rng;

	public MultithreadedService (long rngSeed) {
        this.rng = new Random(rngSeed);
        this.executor = Executors.newFixedThreadPool(4);          // Pool of threads --> ADD NUMTHREADS AS ATTRIBUTE
        this.threadpool = (ThreadPoolExecutor) executor;
    }

  /* 
   * Resets all necessary elements to their initial states.
   */
	public void reset(int numThreads) {
      current_tasks = 0;                                      // Reset task counter
      queue.clear();
      completed_tasks.clear();
      executor = Executors.newFixedThreadPool(numThreads);    // New pool of threads
      threadpool = (ThreadPoolExecutor) executor;             // Cast executor to ThreadPoolExecutor to gather pooldata
    }
   

  /*
   * Count active threads.
   */
  public int getActiveThreads() {
    return threadpool.getActiveCount();
  }

  /**
   * Returns currently executing threads. 
   */
  public int getExecutingThreads() {
    return threadpool.getPoolSize();
  }


  public int getInactiveThreads() {
    return (threadpool.getPoolSize() - threadpool.getActiveCount());
  }


  public void displayResults() {
    System.out.println("Active threads: "+getActiveThreads());
  }


  public int[] getTaskInfo(Task t) {
    int id = t.id;
    int burst = t.burst;
    int worktime = t.time_spent;
    int time_left = burst - worktime;
    int[] s = {id, burst, worktime, time_left};
    return s;
  }


  public void displayTaskInfo(Task t) {
    int[] taskInfo = getTaskInfo(t);
    String s = "ID: " + taskInfo[0] + "\nBURST: " + taskInfo[1] + "\nWORKTIME: " + taskInfo[2] + "\nTIME LEFT: " + taskInfo[3];
    System.out.println(s);
  }


  /* 
   * Pass task to a thread.
   */
  public void passTask(Task t) {
    executor.execute(t);
  }


  public Task getRandomTask() {
    Task t = queue.get(rng.nextInt(0, queue.size()));
    return t;
  }


  public Task getTask() {
    // Add functionality to detect if threads are active, adjust index
    int active_threads = getActiveThreads();
    return queue.get(active_threads);
  }


    public void runNewSimulation(final long totalSimulationTimeMs,
        final int numThreads, final int numTasks,
        final long minBurstTimeMs, final long maxBurstTimeMs, final long sleepTimeMs) {
        reset(numThreads);

        System.out.println("ACTIVE THREADS: "+threadpool.getActiveCount()+"\nCORE POOLSIZE: "+threadpool.getCorePoolSize());
        


        double start_time = getCurrentTimeMs();
        double time_end = start_time + totalSimulationTimeMs;
        
        // Create tasks
        for (int k = 0 ; k < numTasks ; k++) {
          Task t = new Task(k, maxBurstTimeMs, minBurstTimeMs);
          queue.add(t);
        }

        // Delegate tasks to the threads!!!!
        while (start_time < time_end) {
          passTask(getTask());
          start_time = getCurrentTimeMs();
        }
        System.out.println("-- Completed tasks = "+completed_tasks.size());
        displayResults();
        System.out.println("15 seconds spent!\n");

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

