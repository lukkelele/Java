/*
 * File:	RandomScheduling.java
 * Course: 	21HT - Operating Systems - 1DV512
 * Author: 	Lukas Gunnarsson, lg222xf@student.lnu.se
 * Date: 	10/1-2022
 */

package cpu_scheduler;

import java.util.Random;
import java.util.ArrayList;

// TODO: put this source code file into a new Java package with meaningful name (e.g., dv512.YourStudentID)!

// You can implement additional fields and methods in code below, but
// you are not allowed to rename or remove any of it!

// Additionally, please remember that you are not allowed to use any third-party libraries

public class RandomScheduling {
	
    ArrayList<ScheduledProcess> queue;
    ArrayList<ScheduledProcess> finishedProcesses;
	int idleTime;

	public static class ScheduledProcess {
		int processId;
		int burstTime;
		int arrivalMoment;
		// The total time the process has waited since its arrival
		int totalWaitingTime;
		// The total CPU time the process has used so far
		// (when equal to burstTime -> the process is complete!)
		int allocatedCpuTime;
		// Time when the process has ended
		int ended;

		public ScheduledProcess(int processId, int burstTime, int arrivalMoment) {
			this.processId = processId;
			this.burstTime = burstTime;
			this.arrivalMoment = arrivalMoment;
			this.ended = 0;
		}
		// ... add further fields and methods, if necessary
	}
	Random rng;

	// ... add further fields and methods, if necessary
		
	public RandomScheduling(long rngSeed) {
		this.rng = new Random(rngSeed);
	}
	
	public void reset() {
		// TODO - remove any information from the previous simulation, if necessary
        queue = new ArrayList<ScheduledProcess>();
        finishedProcesses = new ArrayList<ScheduledProcess>();
        queue.clear();
        finishedProcesses.clear();
		idleTime = 0;
	}

	/**
	 * Get a process from the queue.
	 * Might add a new process to the queue with the help of 'tickProcess'.
	 * 
	 * @param ticks is the total amount of ticks that the simulation has been going on for
	 * @param preemptive is used for using tickProcess or not. The preemptive version runs
	 * 					 tickProcess inside its own simulation loop so it is not needed here.
	 * @return a random process in the queue if possible, else null
	 */
    ScheduledProcess getProcess(int ticks, boolean preemptive) {
		if (!preemptive) {
			tickProcess(0.75, 10, 2, ticks);
		}
		if (queue.size() > 0) {
			int rand = rng.nextInt(queue.size());
			ScheduledProcess p = queue.get(rand);
			return p;
		}
		return null;
    }

	/**
	 * Each tick might produce a new process if the randomly generated value is 
	 * within the accepted range. 
	 *
	 * @param probArrival is the probability of a new process to be created
	 * @param maxBurstTime is the maximum amount of burst a new process can have
	 * @param minBurstTime is the minimum amount of burst a new process can have
	 * @param ticks is the total amount of ticks 
	 */
	void tickProcess(double probArrival, int maxBurstTime, int minBurstTime, int ticks) {
		double rand = rng.nextDouble();
		if (rand < probArrival) {
			int id = queue.size() + finishedProcesses.size() + 1;
			int generatedBurst = this.rng.nextInt((maxBurstTime - minBurstTime)) + minBurstTime;
			ScheduledProcess newProcess = new ScheduledProcess(id, generatedBurst, ticks);
			this.queue.add(newProcess);
		}
	}

	/**
	 * Update the wait time for all processes except one.
	 * 
	 * @param p is the process who is currently being executed
	 */
	public void updateWaitTime(ScheduledProcess p) {
		for (int i = 0; i < queue.size(); i++) {
			ScheduledProcess currentProcess = queue.get(i);
			if (currentProcess != p) 
				currentProcess.totalWaitingTime++;
		}
	}

	public void runNewSimulation(final boolean isPreemptive,
        final int timeQuantum,
	    final int numProcesses,
		final int minBurstTime,
        final int maxBurstTime,
		final int maxArrivalsPerTick,
        final double probArrival) {
		reset();
			
        if (!isPreemptive) {
            int ticks = 0;
            while (finishedProcesses.size() < 10) {
                ScheduledProcess p = getProcess(ticks, false);
				ticks++;
				updateWaitTime(p);
				if (p != null) {
					p.allocatedCpuTime++;

					if (p.allocatedCpuTime >= p.burstTime) {
						p.ended = ticks;
						finishedProcesses.add(p);
						queue.remove(p);
					}
				} else {
					this.idleTime++;
				}
			} 
        }

		if (isPreemptive) {
            int ticks = 0;
            while (finishedProcesses.size() < 10) {
                ScheduledProcess p = getProcess(ticks, true);
				if (p != null) {

					int localTicks = 0;
					while (p.allocatedCpuTime < p.burstTime) {
						ticks++;
						localTicks++;
						p.allocatedCpuTime++;
						updateWaitTime(p);

						tickProcess(probArrival, maxBurstTime, minBurstTime, ticks);
						if (localTicks == timeQuantum) 
							break;
					}
					if (p.allocatedCpuTime == p.burstTime) {
						p.ended = ticks;
						finishedProcesses.add(p);
						queue.remove(p);
					}
				} else {
					ticks++;
					this.idleTime++;
					tickProcess(probArrival, maxBurstTime, minBurstTime, ticks);
				}
            }
        }

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
        int waitingTime = 0;
		System.out.println("======================================");
        for (ScheduledProcess p : this.finishedProcesses) {
            System.out.println("> Process " + (finishedProcesses.indexOf(p) + 1) + " \n"
            + "id: " + p.processId + "\n"
            + "burst: " + p.burstTime + "\n"
            + "arrival: " + p.arrivalMoment + "\n"
			+ "ended: " + p.ended + "\n"
            + "waiting time: " + p.totalWaitingTime + "\n"
            + "======================================");
            waitingTime += p.totalWaitingTime;
        }
        int averageWaitingTime = waitingTime / processCount;
		// Total time spent is the ending tick value for the last process
		int timeSpent = finishedProcesses.get(finishedProcesses.size() - 1).ended;
        System.out.println("| Average waiting time for all processes: " + averageWaitingTime
		+ "  (" + waitingTime + " / " + processCount + " = " + averageWaitingTime + ") \n"
		+ "| Simulation done in: " + timeSpent + " ticks\n" 
		+ "| Idle time: " + this.idleTime + " ticks");
	}
		
	public static void main(String args[]) {
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

				System.out.println("\nSimulation results:"
					+ "\n" + "----------------------");	
				scheduler.printResults();

				System.out.println("\n");
			}
		}		
		
	}
	
}
