import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;
import java.util.ArrayList;

/*
 * File:	MultithreadedService.java
 * Course: 	21HT - Operating Systems - 1DV512
 * Author:  Lukas Gunnarsson	
 * Date: 	16/1-2022
 */


public class MultithreadedService {

    double time_begin;
    ArrayList<Task> queue = new ArrayList<Task>();
    ArrayList<Task> completed_tasks = new ArrayList<Task>();
    ArrayList<Task> interrupted_tasks = new ArrayList<Task>();
    ExecutorService executor;
    ThreadPoolExecutor threadpool;


    public class Task implements Runnable {
        int id;
        int burst;
        int time_spent;
        double start;
        double finish;
        boolean busy;

        public Task(int id, long maxBurstTime, long minBurstTime) {
            this.id = id;
            this.burst = (int) generateBurst(maxBurstTime, minBurstTime);
            this.time_spent = 0;
            this.busy = false;
        }

        long generateBurst(long maxBurstTimeMs, long minBurstTimeMs) {
            return rng.nextLong((maxBurstTimeMs - minBurstTimeMs)) + minBurstTimeMs; // Random number between allowed
        }

        public void run() {
            displayTaskInfo(this);
            try {
                sleep(this);
            } catch (InterruptedException e) {
            }
        }
    }

    /*
     * For executing tasks.
     */
    void sleep(Task t) throws InterruptedException {
        t.start = getCurrentTimeMs();
        try {
            queue.remove(t);
            TimeUnit.MILLISECONDS.sleep(t.burst);
            completed_tasks.add(t);                 // When sleep is fully done, add to completed
            t.finish = getCurrentTimeMs();
            t.time_spent = t.burst;
        } catch (InterruptedException e) {
            t.time_spent = (int) (getCurrentTimeMs() - t.start);
            t.finish = getCurrentTimeMs();
            interrupted_tasks.add(t);
            queue.remove(t);
        }
    }

    Random rng;

    public MultithreadedService(long rngSeed) {
        this.rng = new Random(rngSeed);
        this.executor = Executors.newFixedThreadPool(4); 
        this.threadpool = (ThreadPoolExecutor) executor;
    }


    public int[] getTaskInfo(Task t) {
        int id = t.id;
        int burst = t.burst;
        int worktime = t.time_spent;
        int time_left = burst - worktime;
        int[] s = { id, burst, worktime, time_left };
        return s;
    }


    public void displayTaskInfo(Task t) {
        int[] taskInfo = getTaskInfo(t);
        String s = "ID: \t" + taskInfo[0] + "    ||  BURST: " + taskInfo[1] + "   ||  TIME SPENT: " + taskInfo[2] + "   ||  TIME LEFT: "
                + taskInfo[3]+" ";
        System.out.println(s);
    }

    /*
     * Pass task to a thread.
     */
    public void passTask(Task t) {
        if (t == null);
        else {
            if (t.busy == false) {
                t.busy = true;
                executor.execute(t);
            }
        }
    }

    /**
     *  Send kill signal to threads.
     */
    public void interruptTasks() {
        executor.shutdownNow();
    }

    /**
     * Get a task that isn't taken already.
     * 
     * @return a task which hasn't been taken yet.
     */
    public Task getTask() {
        int k = 0;
        try {
            Task task = queue.get(0);
            if (task.busy == true) {
                task = queue.get(k++);
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }    

    public void runNewSimulation(final long totalSimulationTimeMs,
        final int numThreads, final int numTasks,
        final long minBurstTimeMs, final long maxBurstTimeMs, final long sleepTimeMs) throws InterruptedException {

        reset(numThreads); 
        double start_time = getCurrentTimeMs();
        double time_end = start_time + totalSimulationTimeMs;

        // Create tasks
        for (int k = 0; k < numTasks; k++) {
            Task t = new Task(k+1, maxBurstTimeMs, minBurstTimeMs);         // k+1 to make range go from 1-30 instead of 0-29
            queue.add(t);
        }
        int begin_time = LocalTime.now().toSecondOfDay();
        
        // Main loop
        while (start_time <= time_end) {
            start_time = getCurrentTimeMs();
            while (getActiveThreads() < 4) {
                start_time = getCurrentTimeMs();
                passTask(getTask());
            }
        }
        interruptTasks();       // Kill all running tasks
        System.out.println("TOTAL TIME IN SIMULATION: "+(LocalTime.now().toSecondOfDay() - begin_time));
    
    }

    public void reset(int numThreads) {
        queue.clear();
        completed_tasks.clear();
        interrupted_tasks.clear();
        executor = Executors.newFixedThreadPool(numThreads); // New pool of threads
        threadpool = (ThreadPoolExecutor) executor;          // Cast executor to ThreadPoolExecutor to gather pooldata
    }

    /*
     * Count active threads.
     */
    public int getActiveThreads() {
        return threadpool.getActiveCount();
    }

    public double getCurrentTimeMs() {
        return LocalTime.now().toNanoOfDay() / Math.pow(10, 6);
    }

    public void printResults() {
        System.out.println("Completed tasks: " + completed_tasks.size());
        for (Task t : completed_tasks) {
            displayTaskInfo(t);
        }
        System.out.println("\n---------------");

        System.out.println("Interrupted tasks: " + interrupted_tasks.size());
        for (Task t : interrupted_tasks) {
            displayTaskInfo(t);
        }
        System.out.println("\n---------------");

        System.out.println("Waiting tasks: " + queue.size());
        for (Task t : queue) {
            displayTaskInfo(t);
        }
        System.out.println("\n---------------");
    }


    public static void main(String args[]) throws InterruptedException {

        final long rngSeed = 19990520;

        MultithreadedService service = new MultithreadedService(rngSeed);

        final int numSimulations = 3;
        final long totalSimulationTimeMs = 15 * 1000L; // 15 seconds
        final int numThreads = 4;
        final int numTasks = 30;
        final long minBurstTimeMs = 1 * 1000L; // 1 second
        final long maxBurstTimeMs = 10 * 1000L; // 10 seconds
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

    }
}
