/*
 * File:	RandomScheduling.java
 * Course: 	21HT - Operating Systems - 1DV512
 * Author: 	Lukas Gunnarsson, lg222xf@student.lnu.se
 * Date: 	  28/12-2022
 */

package cpu_scheduler;

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class RandomScheduling {

    Random rng;
    int totalProcesses;
    int global_ticks;
    int ticks;
    ArrayList<ScheduledProcess> queue;
    ArrayList<ScheduledProcess> finished_processes;

    public static class ScheduledProcess {
        int processId;
        int burstTime;
        int burst; // Holds the initial given burst value and is not decremented
        int arrivalMoment;
        int totalWaitingTime;
        int allocatedCpuTime;

        public ScheduledProcess(int processId, int burstTime, int arrivalMoment) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.burst = burstTime;
            this.arrivalMoment = arrivalMoment;
        }
    }

    /**
     * Returns an integer array that is used to calculate average based on all
     * simulations.
     */
    public int[] runNewSimulation(final boolean isPreemptive,
                                  final int timeQuantum,
                                  final int numProcesses,
                                  final int minBurstTime,
                                  final int maxBurstTime,
                                  final int maxArrivalsPerTick,
                                  final double probArrival) throws InterruptedException {
                                    ScheduledProcess p;
        reset();

        // Creates the initial processes
        for (int k = 0; k < numProcesses; k++) {
            int generatedBurst = generateBurst(maxBurstTime, minBurstTime);
            ScheduledProcess process = new ScheduledProcess(k + 1, generatedBurst, global_ticks);
            queue.add(process);
            global_ticks++;
        }

        if (isPreemptive) {
            while (queue.size() > 0) { 
                p = getRandomProcess();
                ticks = 0; 

                while (ticks < timeQuantum) {
                    global_ticks++;
                    p.allocatedCpuTime++; 
                    tickProcess(numProcesses, probArrival, maxArrivalsPerTick); 
                    waitTimeTick(p); 
                    p.burstTime--;
                    ticks++;
                    if (p.burstTime == 0) { 
                        queue.remove(p);
                        finished_processes.add(p);
                        break;
                    }
                }
            }
        }
        if (!isPreemptive) {
            while (queue.size() > 0) { 
                p = getRandomProcess();
                while (p.burstTime > 0) {
                    global_ticks++;
                    p.allocatedCpuTime++; 
                    tickProcess(numProcesses, probArrival, maxArrivalsPerTick);
                    waitTimeTick(p); 
                    ticks++;
                    p.burstTime--;
                }
                queue.remove(p);
                finished_processes.add(p);
            }
        }
        // Show all results
        printResults();
        int timeSpent = global_ticks; 
        int processes = finished_processes.size();
        int averageTime = (int) calcWaitTime(finished_processes);
        int[] result = { timeSpent, processes, averageTime };
        return result;
    }

    void reset() {
        queue = new ArrayList<ScheduledProcess>();
        finished_processes = new ArrayList<ScheduledProcess>();
        global_ticks = 0;
        ticks = 0;
        totalProcesses = 0;
        queue.clear();
        finished_processes.clear();
    }

    /**
     * Reads the queue and rolls a random index for the next process to get
     * scheduled.
     */
    ScheduledProcess getRandomProcess() {
        ScheduledProcess p;
        int queueSize = queue.size();
        int randomIndex = rng.nextInt(queueSize);
        p = queue.get(randomIndex);
        return p;
    }

    void waitTimeTick(ScheduledProcess ignored_process) {
        for (ScheduledProcess p : queue) {
            if (p == ignored_process) {
                /* do nothing */
            } else {
                p.totalWaitingTime += 1;
            } 
        }
    }

    /**
     * Returns an integer between min and max.
     */
    public int generateBurst(int max, int min) {
        return rng.nextInt((max - min)) + min;
    }

    public RandomScheduling(long rngSeed) {
		this.rng = new Random(rngSeed);
	}

    void tickProcess(int numProcesses, double probArrival, int maxArrival) {
        double rand;
        double probability = 1 - probArrival; // 25%, to reduce the creation for testing
        // double probability = probArrival; // Default value but it is causing serious
        // lag
        int queueSize = queue.size();
        if (queueSize < (numProcesses - maxArrival)) { // If queue is larger than accepted amount
            // maxArrival = 1; // OVERWRITING here because otherwise the amount of processes
            // go crazy
            for (int k = 0; k < maxArrival; k++) {
                rand = rng.nextDouble(); // 0 - 1.0 , if within the accepted range, then add new processes
                if (rand < probability) {
                    addNewProcess(totalProcesses, generateBurst(8, 2), global_ticks); // Better approach to include the
                                                                                      // maxBurstTime and minBurstTime
                                                                                      // in the passed parameters to be
                                                                                      // able to change burstrange
                }
            }
        } else {
            /* do nothing */ }
    }

    /* Creates a new process and adds it in to the queue */
    void addNewProcess(int pid, int burst, int arrival) {
        ScheduledProcess p = new ScheduledProcess(totalProcesses, burst, arrival);
        queue.add(p);
        totalProcesses++;
    }

    /* Prints the results for each indivial process plus the totals */
    public void printResults() {
        ScheduledProcess p;
        Iterator<ScheduledProcess> iter = finished_processes.iterator();
        while (iter.hasNext()) {
            p = iter.next();
            printProcessResult(p); // Comment this if individual processor output is to be removed
        }
        System.out.println("\nTOTAL TIME: " + global_ticks + "\t| NUMBER OF PROCESSES: " + finished_processes.size()
                + " \t|\t AVERAGE WAITING TIME: " + calcWaitTime(finished_processes) + " |");
    }

    /* Calculates the average time spent waiting for all processes */
    double calcWaitTime(ArrayList<ScheduledProcess> processes) {
        int totalWait = 0;
        int totalCpu = 0;
        int processAmount = processes.size();
        for (ScheduledProcess p : processes) {
            totalWait += p.totalWaitingTime;
        }
        double average = totalWait / processAmount;
        return average;
    }

    /**
     * Prints a process and its attributes.
     */
    void printProcessResult(ScheduledProcess p) {
        System.out.println("PID:\t" + p.processId + "\t|\tBURST:" + p.burst + "\t|\tARRIVAL:" + p.arrivalMoment
                + "\t|\tWAIT:" + p.totalWaitingTime + "\t|\tCPU TIME:" + p.allocatedCpuTime);
    }

    /* Calculates the total average for the total run of simulations */
    static void printTotalAverage(ArrayList<int[]> results, int simulations, boolean isPreemptive) {
        // Sum the values
        // int[3] = [ TIME, PROCESSES, WAIT ]
        double time = 0;
        double processes = 0;
        double wait = 0;
        String preemptive = "NON-PREEMPTIVE";
        for (int[] a : results) {
            time += a[0];
            processes += a[1];
            wait += a[2];
        }
        time /= simulations;
        processes /= simulations;
        wait /= simulations;
        if (isPreemptive)
            preemptive = "PREEMPTIVE";
        System.out.println(">------ TOTAL AVERAGE FOR " + preemptive + " ------<\ntime: " + time + "\nprocesses: "
                + processes + "\nwait: " + wait + "\n--------------------------------------------\n");
    }

    // ===========================

    // MAIN METHOD

    public static void main(String args[]) throws InterruptedException {

        ArrayList<int[]> results = new ArrayList<int[]>();

        final long rngSeed = 990520;
        RandomScheduling scheduler = new RandomScheduling(rngSeed);

        final int numSimulations = 5;
        final int numProcesses = 10;
        final int minBurstTime = 2;
        final int maxBurstTime = 10;
        final int maxArrivalsPerTick = 2;
        final double probArrival = 0.75;
        final int timeQuantum = 2;

        boolean[] preemptionOptions = { false, true };

        for (boolean isPreemptive : preemptionOptions) {
            for (int i = 0; i < numSimulations; i++) {
                System.out.println("Running " + ((isPreemptive) ? "preemptive" : "non-preemptive")
                        + " simulation #" + i);

                results.add(scheduler.runNewSimulation(isPreemptive, timeQuantum, numProcesses, minBurstTime,
                        maxBurstTime, maxArrivalsPerTick, probArrival));

                System.out.println("\n");
            }
            printTotalAverage(results, numSimulations, isPreemptive);
            results.clear(); // Clear before running the rest of the simulations with the second preemption
                             // option
        }

    }

}
