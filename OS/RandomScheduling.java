import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * File:	RandomScheduling.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	*State your name and LNU student ID here! (e.g., ab222cd)*
 * Date: 	November 2020
 */


// You can implement additional fields and methods in code below, but
// you are not allowed to rename or remove any of it!

// Additionally, please remember that you are not allowed to use any third-party libraries


public class RandomScheduling {       // Fix typo in name

	Random rng;
  int clearedProcesses;
  ArrayList<ScheduledProcess> container = new ArrayList<ScheduledProcess>();
  ArrayList<ScheduledProcess> cleared = new ArrayList<ScheduledProcess>();

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
		
		// ... add further fields and methods, if necessary
	}
		
	// Random number generator that must be used for the simulation

	// ... add further fields and methods, if necessary
  public int generateBurst(int max, int min) {
    return rng.nextInt(max-min);
  }



	public RandomScheduling(long rngSeed) {
		this.rng = new Random(rngSeed);
	}


	public void reset() {
    container = new ArrayList<ScheduledProcess>();
    cleared = new ArrayList<ScheduledProcess>();
    clearedProcesses = 0;
	}



  int calculateTime(long time) {
    long time_now = System.nanoTime();
    int t = (int)(time_now - time);
    return t;
  }


  void addWaitTime(ScheduledProcess p, long start_time) {
    int t = (int)(System.nanoTime() - start_time);
    p.totalWaitingTime += t; 
  }


  /* Handles the potential generation of new processes per tick */
  void tickProcess(double probArrival, int maxArrivals, long start_time, int maxBurst, int minBurst) {
    double rand_num = rng.nextDouble();
    for (int k=0 ; k<maxArrivals ; k++) { 
      if (rand_num <= probArrival) {
        int arrival = (int)(System.nanoTime() - start_time);
        int pid = container.size() + cleared.size();
        ScheduledProcess p = new ScheduledProcess(pid, generateBurst(maxBurst, minBurst), arrival); 
        container.add(p);
    }
      
    }
  }

  Double convertNanoSeconds(int ns) {
    double seconds = Double.parseDouble(String.valueOf(ns));
    seconds = seconds * Math.pow(10, -9);
    return seconds;
  }

	
	public void runNewSimulation(final boolean isPreemptive, 
    final int timeQuantum,
	  final int numProcesses,
		final int minBurstTime, 
    final int maxBurstTime,
		final int maxArrivalsPerTick, 
    final double probArrival) {
    int ticks;
	  reset();

    long time_start = System.nanoTime();
    // Creates processes
    for (int k=0 ; k<=numProcesses ; k++) {
      int t = calculateTime(time_start);
      ScheduledProcess new_p = new ScheduledProcess(k+1, generateBurst(maxBurstTime, minBurstTime), t);
      container.add(new_p);

    }
    

    ScheduledProcess p;

    if (isPreemptive) { 
      while (cleared.size() != numProcesses) {

        try {
          p = container.get(rng.nextInt(0, (numProcesses-clearedProcesses)));
        } catch (IndexOutOfBoundsException e) {
          p = container.get(0); }

        ticks = 0;
        addWaitTime(p, time_start);

        while (ticks != timeQuantum) {
          p.burstTime--;
          ticks++;
          tickProcess(probArrival, maxArrivalsPerTick, time_start, maxBurstTime, minBurstTime);
          if (p.burstTime <= 0) {
            clearedProcesses++;
            container.remove(p);              // pid-1 equals index
            cleared.add(p);
            break;
          }
        }
    }


    if (!isPreemptive) {
      for (int k=0 ; k<numProcesses ; k++) {

        tickProcess(probArrival, maxArrivalsPerTick, time_start, maxBurstTime, minBurstTime);
        p = container.get(rng.nextInt(0, (numProcesses-clearedProcesses) + 1)); 
         
      }

      // Gather results from simulations
        long time_end = System.nanoTime();
        long cpu_time = (time_end - time_start);
        double nano_num = cpu_time * Math.pow(10,-9);
        System.out.println("TOTAL TIME SPENT: "+nano_num+"s\n");
      }

    }
  }
    
	
	public void printResults(int numProcesses) {
		// 1. For each process, print its ID, burst time, arrival time, and total waiting time
		// 2. Afterwards, print the complete execution time of the simulation
		// and the average process waiting time
    ScheduledProcess p;
    Iterator<ScheduledProcess> iter = cleared.iterator();
    while (iter.hasNext()) {
      p = iter.next();
      System.out.println("pid:     "+p.processId+"\nburst:   "
      +p.burst+"ns\narrival: "+p.arrivalMoment+"\ntotal wait time: "
      +convertNanoSeconds(p.totalWaitingTime)+"ns\ntime on cpu: "+convertNanoSeconds(p.allocatedCpuTime)+"s\n----------------");
    }

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

			for (int i = 0; i < numSimulations; i++){
				System.out.println("Running " + ((isPreemptive) ? "preemptive" : "non-preemptive")
					+ " simulation #" + i);

				scheduler.runNewSimulation(isPreemptive, timeQuantum, numProcesses, minBurstTime, maxBurstTime,maxArrivalsPerTick, probArrival);

				System.out.println("Simulation results:"
					+ "\n" + "----------------------");	
				scheduler.printResults(numProcesses);

				System.out.println("\n");
			}
		}		
		
	}
	
}


