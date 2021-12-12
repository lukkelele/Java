

import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * File:	Scheduling.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	Lukas Gunnarsson, lg222xf@student.lnu.se
 * Date: 	November 2020
 */


// You can implement additional fields and methods in code below, but
// you are not allowed to rename or remove any of it!

// Additionally, please remember that you are not allowed to use any third-party libraries


public class Scheduling {       // Fix typo in name

	Random rng;
  int totalProcesses;
  int global_ticks;
  ArrayList<ScheduledProcess> queue;
  ArrayList<ScheduledProcess> finished_processes;


	public static class ScheduledProcess {
		int processId;
		int burstTime;
		int burst;
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
	

	public int[] runNewSimulation(final boolean isPreemptive, 
    final int timeQuantum,
	  final int numProcesses,
		final int minBurstTime, 
    final int maxBurstTime,
		final int maxArrivalsPerTick, 
    final double probArrival) throws InterruptedException {
    ScheduledProcess p;
    queue = new ArrayList<ScheduledProcess>();
    finished_processes = new ArrayList<ScheduledProcess>();
    global_ticks = 0;
    int ticks = 0;
    totalProcesses = 0;
    queue.clear();
    finished_processes.clear();

    // Creates the initial processes
    for (int k=0 ; k<numProcesses ; k++) {
      int generatedBurst = generateBurst(maxBurstTime, minBurstTime);
      ScheduledProcess process = new ScheduledProcess(k+1, generatedBurst, global_ticks);
      queue.add(process);
      global_ticks++;
    }
  
    
    if (isPreemptive) { 
      while (queue.size() > 0) {      // If the queue is empty, stop 

        p = getRandomProcess();
        ticks = 0;                    // Whenever time quantum is reached, or the process has executed fully, reset ticks

        while (ticks < timeQuantum) {
          global_ticks++;
          p.allocatedCpuTime++;       // Increments the allocated cpu time for the current process
          tickProcess(numProcesses, probArrival, maxArrivalsPerTick);     // Arrival method 
          waitTimeTick(p);            // Increments the wait time for processes waiting in the queue
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
      while (queue.size() > 0) {      // If the queue is empty, stop
        p = getRandomProcess();
        while (p.burstTime > 0) {
          global_ticks++;
          p.allocatedCpuTime++;       // Increments the allocated cpu time for the current process
          tickProcess(numProcesses, probArrival, maxArrivalsPerTick);  
          waitTimeTick(p);            // Increments the wait time for processes waiting in the queue
          ticks++;
          p.burstTime--;
        }
        queue.remove(p);
        finished_processes.add(p);
      }
    }
    // Show all results
    printResults();
    int timeSpent = global_ticks;                               // Total amount of ticks
    int processes = finished_processes.size();                  // Process count
    int averageTime = (int)calcWaitTime(finished_processes);    // Average time spent waiting
    int[] result = {timeSpent, processes, averageTime};
    return result;
  }
  

 /**
  * Reads the queue and rolls a random index for the next process to get scheduled.
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
      if (p == ignored_process) { /* do nothing */ } 
      else { p.totalWaitingTime += 1; } // If process is in queue, increment its total wait time
    }   
  }


  /**
   * Returns an integer between min and max.
   */
  public int generateBurst(int max, int min) {
    return rng.nextInt((max-min))+min;
  }


	public Scheduling(long rngSeed) {
		this.rng = new Random(rngSeed);
	}


  void tickProcess(int numProcesses, double probArrival, int maxArrival) {
    ScheduledProcess p;
    double rand;
    double probability = 1 - probArrival;     // 25% 
    int queueSize = queue.size();
    if (queueSize < (numProcesses-maxArrival)) { // If queue is larger than accepted amount
      maxArrival = 1; // OVERWRITING here because otherwise the amount of processes go above +20k
      for (int k=0 ; k<maxArrival ; k++) {
        rand = rng.nextDouble();      // 0 - 1.0 , if within the accepted range, then add new processes  
        if (rand < probability) {
          addNewProcess(totalProcesses, generateBurst(8,2), global_ticks);
        }
      }
    }
    else { /* do nothing  */ }
  }

  void addNewProcess(int pid, int burst, int arrival) {
    ScheduledProcess p = new ScheduledProcess(totalProcesses, burst, arrival);
    queue.add(p);
    totalProcesses++;
  }

	public void printResults() {
		// 1. For each process, print its ID, burst time, arrival time, and total waiting time
		// 2. Afterwards, print the complete execution time of the simulation
		// and the average process waiting time
    ScheduledProcess p;
    Iterator<ScheduledProcess> iter = finished_processes.iterator();
    while (iter.hasNext()) {
      p = iter.next();
      printProcessResult(p);
    }
    System.out.println("\n TOTAL TIME: "+global_ticks+"\t| NUMBER OF PROCESSES: "+finished_processes.size()+" \t | \t AVERAGE WAITING TIME: "+calcWaitTime(finished_processes)+" |");
	}


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
    System.out.println("PID:\t"+p.processId+"\t|\tBURST:"+p.burst+"\t|\tARRIVAL:"+p.arrivalMoment+"\t|\tWAIT:"+p.totalWaitingTime+"\t|\tCPU TIME:"+p.allocatedCpuTime);
  }


  static void printTotalAverage(ArrayList<int[]> results, int simulations, boolean isPreemptive) {
    // Sum the values 
    // int[3] = [ TIME,  PROCESSES,   WAIT ]
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
    if (isPreemptive) preemptive = "PREEMPTIVE";
    System.out.println(">------ TOTAL AVERAGE FOR "+preemptive+" ------<\ntime: "+time+"\nprocesses: "+processes+"\nwait: "+wait+"\n--------------------------------------------\n");
  }





  // MAIN METHOD


	public static void main(String args[]) throws InterruptedException {

    ArrayList<int[]> results = new ArrayList<int[]>();

    final long rngSeed = 19990520;
		Scheduling scheduler = new Scheduling(rngSeed);
		
		final int numSimulations = 5;
		final int numProcesses = 10;
		final int minBurstTime = 2;
		final int maxBurstTime = 10;
		final int maxArrivalsPerTick = 2;
		final double probArrival = 0.75;
		final int timeQuantum = 2;

		boolean[] preemptionOptions = {false, true};

		for (boolean isPreemptive: preemptionOptions) {
			for (int i = 0; i < numSimulations; i++){
				System.out.println("Running " + ((isPreemptive) ? "preemptive" : "non-preemptive")
					+ " simulation #" + i);

				results.add(scheduler.runNewSimulation(isPreemptive, timeQuantum, numProcesses, minBurstTime, maxBurstTime,maxArrivalsPerTick, probArrival));

				System.out.println("\n");
			}
    printTotalAverage(results, numSimulations, isPreemptive);
    results.clear();  // Clear before running the rest of the simulations with the second preemption option
		}		
		
	}





  // Vault

  /**
   * Im uncertain about the "new arrival" per tick implemenetation,
   * therefore I had this approach in mind as well
   */
  ScheduledProcess processSwap(ScheduledProcess p, double probArrival) {
    double rand = rng.nextDouble(); 
    double probability = 1 - probArrival; 
    if (rand < probability) {
      // CODE TO PREEMPT CURRENT PROCESS AND RETURN ANOTHER  } 
    }
    return p;
  }


}
