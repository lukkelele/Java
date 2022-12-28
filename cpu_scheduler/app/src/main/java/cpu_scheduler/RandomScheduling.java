/*
 * File:	RandomScheduling.java
 * Course: 	21HT - Operating Systems - 1DV512
 * Author: 	Lukas Gunnarsson, lg222xf@student.lnu.se
 * Date: 	28/12-2022
 */

package cpu_scheduler;

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

// TODO: put this source code file into a new Java package with meaningful name (e.g., dv512.YourStudentID)!

// You can implement additional fields and methods in code below, but
// you are not allowed to rename or remove any of it!

// Additionally, please remember that you are not allowed to use any third-party libraries

public class RandomScheduling {
	
    ArrayList<ScheduledProcess> queue;
    ArrayList<ScheduledProcess> finishedProcesses;

	public static class ScheduledProcess {
		int processId;
		int burstTime;
		int arrivalMoment;
		
		// The total time the process has waited since its arrival
		int totalWaitingTime;
		
		// The total CPU time the process has used so far
		// (when equal to burstTime -> the process is complete!)
		int allocatedCpuTime;

		public ScheduledProcess(int processId, int burstTime, int arrivalMoment) {
			this.processId = processId;
			this.burstTime = burstTime;
			this.arrivalMoment = arrivalMoment;
		}
		
		// ... add further fields and methods, if necessary
	}
		
	// Random number generator that must be used for the simulation
	Random rng;

	// ... add further fields and methods, if necessary
		
	public RandomScheduling(long rngSeed) {
		this.rng = new Random(rngSeed);
	}
	
	public void reset() {
		// TODO - remove any information from the previous simulation, if necessary
	}
	
	public void runNewSimulation(final boolean isPreemptive, final int timeQuantum,
	    final int numProcesses,
		final int minBurstTime, final int maxBurstTime,
		final int maxArrivalsPerTick, final double probArrival) {
		reset();
		
		// TODO:
		// 1. Run a simulation as a loop, with one iteration considered as one unit of time (tick)
		// 2. The simulation should involve the provided number of processes "numProcesses"
		// 3. The simulation loop should finish after the all of the processes have completed
		// 4. On each tick, a new process might arrive with the given probability (chance)
		// 5. However, the max number of new processes per tick is limited
		// by the given value "maxArrivalsPerTick"
		// 6. The burst time of the new process is chosen randomly in the interval
		// between the values "minBurstTime" and "maxBurstTime" (inclusive)

		// 7. When the CPU is idle and no process is running, the scheduler
		// should pick one of the available processes *at random* and start its execution
		// 8. If the preemptive version of the scheduling algorithm is invoked, then it should 
		// allow up to "timeQuantum" time units (loop iterations) to the process,
		// and then preempt the process (pause its execution and return it to the queue)
		
		// If necessary, add additional fields (and methods) to this class to
		// accomodate your solution

		// Note: for all of the random number generation purposes in this method,
		// use "this.rng" !
	}
	
	public void printResults() {
		// TODO:
		// 1. For each process, print its ID, burst time, arrival time, and total waiting time
		// 2. Afterwards, print the complete execution time of the simulation
		// and the average process waiting time
        int processCount = this.finishedProcesses.size();
        int averageWaitingTime = 0;
        for (ScheduledProcess p : this.finishedProcesses) {
            System.out.println("id: " + p.processId + "\n" 
            + "burst: " + p.burstTime + "\n"
            + "arrival: " + p.arrivalMoment + "\n"
            + "waiting time: " + p.totalWaitingTime + "\n"
            + "======================================");
            averageWaitingTime += p.totalWaitingTime;
        }
        averageWaitingTime /= processCount;
        System.out.println("| Average waiting time for all processes: " + averageWaitingTime);
	}
		
	
	public static void main(String args[]) {
		// TODO: replace the seed value below with your birth date, e.g., "20001001"
		final long rngSeed = 19990520;  
		
		
		// Do not modify the code below — instead, complete the implementation
		// of other methods!
		RandomScheduling scheduler = new RandomScheduling(rngSeed);
		
		final int numSimulations = 5;
		
		final int numProcesses = 10;
		final int minBurstTime = 2;
		final int maxBurstTime = 10;
		final int maxArrivalsPerTick = 2;
		final double probArrival = 0.75;
		
		final int timeQuantum = 2;

		boolean[] preemptionOptions = {false, true};

		for (boolean isPreemptive: preemptionOptions) {

			for (int i = 0; i < numSimulations; i++) {
				System.out.println("Running " + ((isPreemptive) ? "preemptive" : "non-preemptive")
					+ " simulation #" + i);

				scheduler.runNewSimulation(
					isPreemptive, timeQuantum,
					numProcesses,
					minBurstTime, maxBurstTime,
					maxArrivalsPerTick, probArrival);

				System.out.println("Simulation results:"
					+ "\n" + "----------------------");	
				scheduler.printResults();

				System.out.println("\n");
			}
		}		
		
	}
	
}