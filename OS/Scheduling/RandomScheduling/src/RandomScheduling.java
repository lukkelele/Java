

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
  int global_tick;
  ArrayList<ScheduledProcess> preemptive_queue = new ArrayList<ScheduledProcess>();
  ArrayList<ScheduledProcess> non_preemptive_queue = new ArrayList<ScheduledProcess>();
  ArrayList<ScheduledProcess> finished_processes = new ArrayList<ScheduledProcess>();


	public static class ScheduledProcess {
		int processId;
		int burstTime;
		int burst;
    int arrivalMoment;
		int totalWaitingTime;
		int allocatedCpuTime;
    long arrival; // save the time from the pure System.nanoTime()

		public ScheduledProcess(int processId, int burstTime, int arrivalMoment) {
			this.processId = processId;
			this.burstTime = burstTime;
      this.burst = burstTime;
			this.arrivalMoment = arrivalMoment;
		}
		
	}
		
	
	public void runNewSimulation(final boolean isPreemptive, 
    final int timeQuantum,
	  final int numProcesses,
		final int minBurstTime, 
    final int maxBurstTime,
		final int maxArrivalsPerTick, 
    final double probArrival) {
    ScheduledProcess p;
    int ticks;
    long p_time;
	  reset();

    long time_start = System.nanoTime();
    // Creates processes
    for (int k=0 ; k<=numProcesses ; k++) {
      int t = calculateTime(time_start);
      int generatedBurst = generateBurst(maxBurstTime, minBurstTime);
      ScheduledProcess p1 = new ScheduledProcess(k+1, generatedBurst, t);
      ScheduledProcess p2 = new ScheduledProcess(k+1, generatedBurst, t);
      long arrival_t = System.nanoTime();
      p1.arrival = arrival_t;
      p2.arrival = arrival_t;
      preemptive_queue.add(p1);
      non_preemptive_queue.add(p2);

    }

    
    if (isPreemptive) { 
      while (finished_processes.size() != numProcesses) {

        try {
          p = preemptive_queue.get(rng.nextInt(0, (numProcesses-finished_processes.size())));
        } catch (IndexOutOfBoundsException e) {
          p = preemptive_queue.get(0); }
        
        ticks = 0;
        while (ticks != timeQuantum) {
          p_time = System.nanoTime();
          p.burstTime--;
          addTimeCPU(p, p_time); 
          ticks++;
          //tickProcess(preemptive_queue, probArrival, maxArrivalsPerTick, time_start, maxBurstTime, minBurstTime);
          if (p.burstTime <= 0) {
            preemptive_queue.remove(p);              // pid-1 equals index
            finished_processes.add(p);
            addTimeCPU(p, p_time);
            break;
          }
        }
    
      }
    }
    if (!isPreemptive) {
      while (finished_processes.size() != numProcesses) {

        System.out.println("entered");
        try {
          p = non_preemptive_queue.get(rng.nextInt(0, (numProcesses - finished_processes.size()) + 1));
        } catch (IndexOutOfBoundsException e) {
          p = non_preemptive_queue.get(0); }

        long start_process = System.nanoTime();
        while (p.burstTime != 0) {
          p.burstTime--;      // Complete process
          //tickProcess(non_preemptive_queue, probArrival, maxArrivalsPerTick, time_start, maxBurstTime, minBurstTime);
          if (p.burstTime <= 0) {
            non_preemptive_queue.remove(p);
            finished_processes.add(p);
            addTimeCPU(p, start_process);

            break;
          }
        }  
        non_preemptive_queue.remove(p);
        finished_processes.add(p);
        addTimeCPU(p, start_process);

      }
    }
  }
    
	
	public void printResults(int numProcesses) {
		// 1. For each process, print its ID, burst time, arrival time, and total waiting time
		// 2. Afterwards, print the complete execution time of the simulation
		// and the average process waiting time
    ScheduledProcess p;
    Iterator<ScheduledProcess> iter = finished_processes.iterator();
    while (iter.hasNext()) {
      p = iter.next();
      System.out.println("pid:     "+p.processId+"\nburst:   "
      +p.burst+"ns\narrival: "+p.arrivalMoment+"\ntotal wait time: "
      +convertNanoSeconds(p.totalWaitingTime)+"ns\ntime on cpu: "+(p.allocatedCpuTime)+"\n----------------");
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




  public int generateBurst(int max, int min) {
    return rng.nextInt((max-min)+min);
  }



	public RandomScheduling(long rngSeed) {
		this.rng = new Random(rngSeed);
	}


	public void reset() {
    preemptive_queue = new ArrayList<ScheduledProcess>();
    finished_processes = new ArrayList<ScheduledProcess>();
	}



  int calculateTime(long time) {
    long time_now = System.nanoTime();
    int t = (int)(time_now - time);
    return t;
  }

  void addTimeCPU(ScheduledProcess p, long start_time) {
    int t = (int)(System.nanoTime() - start_time);
    p.allocatedCpuTime += t; 
  }

  void addWaitTime(ScheduledProcess p, long start_time) {
    int t = (int)(System.nanoTime() - start_time);
    p.totalWaitingTime += t; 
  }



  RandomScheduling.ScheduledProcess tickSwap(double probArrival, int maxArrivals, long start_time, ScheduledProcess p, int numProcesses) {
    double rand_num = rng.nextDouble();
    for (int k=0 ; k<maxArrivals ; k++) { 
      if (rand_num <= probArrival) {
          ScheduledProcess arrived_p = preemptive_queue.get(rng.nextInt(0, (numProcesses-finished_processes.size())));
          return arrived_p;
      }
    }
    return p;       // If the random number was in the 1/4 percentile then dont change the process
  }


  // Is prob wrong, shall new processes be swapped or created?
  /* Handles the potential generation of new processes per tick */
  void tickProcess(ArrayList<RandomScheduling.ScheduledProcess> container, double probArrival, int maxArrivals, long start_time, int maxBurst, int minBurst) {
    double rand_num = rng.nextDouble();
    for (int k=0 ; k<maxArrivals ; k++) { 
      if (rand_num <= probArrival) {
        int arrival = (int)(System.nanoTime() - start_time);
        int pid = container.size() + finished_processes.size();
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

	
}


