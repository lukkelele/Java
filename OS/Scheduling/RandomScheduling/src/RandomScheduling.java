





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
  int global_ticks;
  ArrayList<ScheduledProcess> queue = new ArrayList<ScheduledProcess>();
  ArrayList<ScheduledProcess> finished_processes = new ArrayList<ScheduledProcess>();


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

    int global_ticks = 0;
    // Creates processes
    for (int k=0 ; k<=numProcesses ; k++) {
      int generatedBurst = generateBurst(maxBurstTime, minBurstTime);
      ScheduledProcess process = new ScheduledProcess(k+1, generatedBurst, global_ticks);
      queue.add(process);
      global_ticks++;
    }
  
    finished_processes.clear();
    
    if (isPreemptive) { 
      while (finished_processes.size() < numProcesses) {

        p = queue.get(rng.nextInt(0, (numProcesses-finished_processes.size())+1));
        
        ticks = 0;
        while (ticks < timeQuantum) {
          global_ticks++;
          ticks++;
          addTimeCPU(p, 1);
          waitTimeTick(p, queue);
          p.burstTime--;      // Complete process

          if (p.burstTime <= 0) {
            queue.remove(p);              // pid-1 equals index
            finished_processes.add(p);
            ticks = 0;
            System.out.println("PROCESS DONE --> "+p.processId+"\t|BURST --> "+p.burst+" \t| ARRIVAL -->"+p.arrivalMoment+" \t| WAIT--> "+p.totalWaitingTime+" |\t CPU TIME --> "+p.allocatedCpuTime);
            break;
          }
        }
    
      }
    System.out.println("\n TOTAL TIME: "+global_ticks+"\t | NUMBER OF PROCESSES: "+finished_processes.size()+" \t | \t AVERAGE WAITING TIME: "+calcWaitTime(finished_processes)+" |");
    }
    //global_ticks = 0;
    finished_processes.clear();

    if (!isPreemptive) {
      while (finished_processes.size() < numProcesses) {

        p = queue.get(rng.nextInt(0, (numProcesses-finished_processes.size())+1));
        
        long start_process = global_ticks;
        while (p.burstTime > 0) {
          global_ticks++;
          addTimeCPU(p, 1);
          waitTimeTick(p, queue);
          p.burstTime--;      // Complete process
          //tickProcess(queue, probArrival, maxArrivalsPerTick, time_start, maxBurstTime, minBurstTime);
        } 
        System.out.println("PROCESS DONE --> "+p.processId+"\t|BURST --> "+p.burst+" \t| ARRIVAL -->"+p.arrivalMoment+" \t| WAIT--> "+p.totalWaitingTime+" |\t CPU TIME --> "+p.allocatedCpuTime);
        queue.remove(p);
        finished_processes.add(p);
        addTimeCPU(p, 1);

      }
    System.out.println("\n TOTAL TIME: "+global_ticks+"\t | NUMBER OF PROCESSES: "+finished_processes.size()+" \t | \t AVERAGE WAITING TIME: "+calcWaitTime(finished_processes)+" |");
    }
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

    
  int[] timeSpent(ScheduledProcess p) {
    int[] time_spent = new int[2];
    time_spent[0] = p.allocatedCpuTime;   // CPU time
    time_spent[1] = p.totalWaitingTime;   // Wait time
    return time_spent;
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
      +(p.totalWaitingTime)+"ns\ntime on cpu: "+(p.allocatedCpuTime)+"\n----------------");
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

		/*	System.out.println("Simulation results:"
					+ "\n" + "----------------------");	
				scheduler.printResults(numProcesses);
    */
				System.out.println("\n");
			}
		}		
		
	}

  void reset() {
   
  }

  void waitTimeTick(ScheduledProcess ignored_process, ArrayList<ScheduledProcess> queue) {
    for (ScheduledProcess p : queue) {
      if (p == ignored_process) {  } 
      else { p.totalWaitingTime += 1; }
    }   
  }


  public int generateBurst(int max, int min) {
    return rng.nextInt((max-min))+2;
  }



	public RandomScheduling(long rngSeed) {
		this.rng = new Random(rngSeed);
	}



  void addTimeCPU(ScheduledProcess p, int ticks) {
    p.allocatedCpuTime += ticks; 
  }

  void addWaitTime(ScheduledProcess p, int ticks) {
    p.totalWaitingTime += ticks; 
  }



  void tickSwap(double probArrival, int maxArrivals, long start_time, ScheduledProcess p, int numProcesses) {
    double rand_num = rng.nextDouble();
    for (int k=0 ; k<maxArrivals ; k++) { 
      if (rand_num <= probArrival) {
          ScheduledProcess arrived_p = queue.get(rng.nextInt(0, (numProcesses-finished_processes.size())));
          queue.add(arrived_p);
      }
    }
  }


  // Is prob wrong, shall new processes be swapped or created?
  /* Handles the potential generation of new processes per tick */
  void tickProcess(ArrayList<RandomScheduling.ScheduledProcess> container, double probArrival, int maxArrivals, int maxBurst, int minBurst) {
    double rand_num = rng.nextDouble();
    for (int k=0 ; k<maxArrivals ; k++) { 
      if (rand_num <= probArrival) {
        int arrival = global_ticks; 
        int pid = container.size() + finished_processes.size();
        ScheduledProcess p = new ScheduledProcess(pid, generateBurst(maxBurst, minBurst), arrival); 
        container.add(p);
  
      }
    }
  }

}


