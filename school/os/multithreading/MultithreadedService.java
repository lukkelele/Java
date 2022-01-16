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

        // Give each task a flag to indicate if its taken by a thread or not
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
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted! ----> " + this.id);
                interrupted_tasks.add(this);
                //queue.remove(this);
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
        String s = "ID: \t" + taskInfo[0] + "    ||  BURST: " + taskInfo[1] + "   ||  TIME SPENT: " + taskInfo[2] + "   ||  TIME LEFT: "
                + taskInfo[3]+" ";
        System.out.println(s);
    }

    /*
     * Pass task to a thread.
     */
    public void passTask(Task t) {
        if (t == null){
        }
        else {
            if (t.busy == false) {
                t.busy = true;
                executor.execute(t);
            }

        }
    }

    public void interruptTasks() {
        executor.shutdownNow();
    }

    public Task getTask() {
        // Add functionality to detect if threads are active, adjust index
        int k = 0;
        if (queue.size() == 0) return null;
        else {
            try {
                Task task = queue.get(0);
                if (task.busy == true) {
                    task = queue.get(k++);
                }
                return task;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("error");
                return null;
            }
        }
    }


    public void runNewSimulation(final long totalSimulationTimeMs,
        final int numThreads, final int numTasks,
        final long minBurstTimeMs, final long maxBurstTimeMs, final long sleepTimeMs) throws InterruptedException {

        reset(numThreads);
        double start_time = getCurrentTimeMs();
        double time_end = start_time + totalSimulationTimeMs;

        // Create tasks
        queue = new ArrayList<Task>();
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
            if (start_time >= time_end) {
                break;
            }
        }
        interruptTasks();
        System.out.println("TOTAL TIME IN SIMULATION: "+(LocalTime.now().toSecondOfDay() - begin_time));
    
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

        System.out.println("Sleeping for 5 seconds..");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Beginning simulations!");

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


    /*
     * Resets all necessary elements to their initial states.
     */
    public void reset(int numThreads) {
        queue.clear();
        completed_tasks.clear();
        interrupted_tasks.clear();
        executor = Executors.newFixedThreadPool(numThreads); // New pool of threads
        threadpool = (ThreadPoolExecutor) executor; // Cast executor to ThreadPoolExecutor to gather pooldata
        //System.out.println("----- RESET DONE -----");
    }

    public void timer(double starting_time) {
        double total_time = getCurrentTimeMs() - starting_time;
        System.out.println("-----\nTime since program start: "+total_time);
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
