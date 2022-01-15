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
    ArrayList<Task> interrupted_tasks = new ArrayList<Task>();
    ExecutorService executor; // Pool of threads --> ADD NUMTHREADS AS
    ThreadPoolExecutor threadpool;

    // TODO: implement a nested public class titled Task here
    // which must have an integer ID and specified burst time (duration) in
    // milliseconds,
    // see below
    // Add further fields and methods to it, if necessary
    // As the task is being executed for the specified burst time,
    // it is expected to simply go to sleep every X milliseconds (specified below)

    public class Task implements Runnable {
        int id;
        int burst;
        int time_spent;
        double start;
        double finish;
        boolean busy;

        // Give each task a flag to indicate if its taken by a thread or not
        public Task(int id, long maxBurstTime, long minBurstTime) {
            this.id = id;
            this.burst = (int) generateBurst(maxBurstTime, minBurstTime);
            this.time_spent = 0;
            this.busy = false;
            queue.add(this); // Add task to queue
        }

        long generateBurst(long maxBurstTimeMs, long minBurstTimeMs) {
            return rng.nextLong((maxBurstTimeMs - minBurstTimeMs)) + minBurstTimeMs; // Random number between allowed
        }


        public void run() {
            displayTaskInfo(this);
            double starting_time = getCurrentTimeMs();
            try {

                sleep(this);
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted! ----> " + this.id);
            }
        }
    }


    /*
     * For executing tasks.
     */
    void sleep(Task t) throws InterruptedException {
        t.start = getCurrentTimeMs();
        try {
            TimeUnit.MILLISECONDS.sleep(t.burst);
            t.finish = getCurrentTimeMs();
            t.time_spent = t.burst;
            displayTaskInfo(t);
        } catch (InterruptedException e) {
            t.time_spent = (int) (getCurrentTimeMs() - t.start);
            t.finish = getCurrentTimeMs();
        }
    }


    Random rng;

    public MultithreadedService(long rngSeed) {
        this.rng = new Random(rngSeed);
        this.executor = Executors.newFixedThreadPool(4); // Pool of threads --> ADD NUMTHREADS AS ATTRIBUTE
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
        String s = "ID: " + taskInfo[0] + "\nBURST: " + taskInfo[1] + "\nWORKTIME: " + taskInfo[2] + "\nTIME LEFT: "
                + taskInfo[3];
        System.out.println(s);
    }

    /*
     * Pass task to a thread.
     */
    public void passTask(Task t) {
        executor.execute(t);
        queue.remove(t);
        completed_tasks.add(t);
    }

    public Task getTask() {
        // Add functionality to detect if threads are active, adjust index
        int k = getActiveThreads();
        try {
            Task task = queue.get(k);
            if (task.busy) {
                while (task.busy == true) {
                    task = queue.get(k++);
                }
                System.out.println("K = "+k);
            } else {
                task.busy = true;
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error -> "+e+"\nat getTask()!");
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
            Task t = new Task(k, maxBurstTimeMs, minBurstTimeMs);
            queue.add(t);
        }

        while (start_time < time_end) {
            passTask(getTask());
            TimeUnit.MILLISECONDS.sleep(20);
            start_time = getCurrentTimeMs();
        }

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

        // If your program has not completed after the message printed above,
        // it means that some threads are not properly stopped! -> this issue will
        // affect the grade
    }


    public Task getRandomTask() {
        Task t = queue.get(rng.nextInt(0, queue.size()));
        return t;
    }

    /*
     * Resets all necessary elements to their initial states.
     */
    public void reset(int numThreads) {
        current_tasks = 0; // Reset task counter
        queue.clear();
        completed_tasks.clear();
        executor = Executors.newFixedThreadPool(numThreads); // New pool of threads
        threadpool = (ThreadPoolExecutor) executor; // Cast executor to ThreadPoolExecutor to gather pooldata
    }


    /**
     * Returns currently executing threads.
     */
    public int getExecutingThreads() {
        return threadpool.getPoolSize();
    }

    /*
     * Count active threads.
     */
    public int getActiveThreads() {
        return threadpool.getActiveCount();
    }

    public int getInactiveThreads() {
        return (threadpool.getPoolSize() - threadpool.getActiveCount());
    }

    public void displayResults() {
        System.out.println("Active threads: " + getActiveThreads());
    }

}
