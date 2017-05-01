package VehicleRoutingProblem;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.jfree.ui.RefineryUtilities;
import GraphVisualization.GraphUI;
import GraphVisualization.ParticlesLineGraph;
import ParticleSwarmOptimization.Swarm;

public class Simulator {

	public final static int S = 5;  // no of stores
	public final static int[] Q = {12,15,20}; // capacity of each vehicle
	public final static int K = 3;  // no of vehicles
	
	private final static int MAX_STORE_DEMAND = 10;
	private final static int MAX_STORE_RECYCLABLES = 5;
	
	public final static int N = 3;  // no of particles in swarm
	public final static int T = 12;  // iteration count
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//to log in a file
		/*PrintStream	out = new PrintStream(new FileOutputStream("pso_N_"+N+"T_"+T+"_log.txt",true));
		System.setOut(out);*/
				
		//Initializations
		System.out.println("---------------------------------------------------------");	
		System.out.println("Medicines Distribution Model");
		System.out.println("---------------------------------------------------------");		
		
		//Design the distribution model for the problem
		DistributionModel dm = new DistributionModel(S, Q, K, MAX_STORE_DEMAND, MAX_STORE_RECYCLABLES);
		dm.printModelDetails();
		System.out.println("---------------------------------------------------------");
		
		System.out.println("Swarm Model");
		System.out.println("---------------------------------------------------------");
		//Initialize the swarm for the distribution model
		Swarm swarm = new Swarm(N, dm);
		swarm.printSwarmDetails();
		System.out.println("---------------------------------------------------------");
		
		System.out.println("Iterations Details");
		System.out.println("---------------------------------------------------------");
		Map<String, Map<Double, Double>> particleProgress = new HashMap<String, Map<Double,Double>>();
		
		//print iteration 0 results
		System.out.print("Iteration\t");
		for(int i=0; i<swarm.getParticles().length; i++)
			System.out.print("f(x:"+(i+1)+") f(pBest:"+(i+1)+")\t");		
		
		System.out.println("f(gBest)");
		swarm.printIterationResults(0, particleProgress);
		
				
		//Optimize the solution and return the best solution after the iterations terminate
		for(int t=1; t<=T;t++){
			swarm.optimizeSolutions();	
			swarm.printIterationResults(t, particleProgress);			
		}
		System.out.println("---------------------------------------------------------");
		
	
		ParticlesLineGraph particleGraph = new ParticlesLineGraph("Particles Progress", particleProgress);
		particleGraph.pack();        
        RefineryUtilities.centerFrameOnScreen(particleGraph);
        particleGraph.setVisible(true);
		
        
        System.out.println("Continue with decoding the best solution... ? Y or N");
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
        	if(sc.nextLine().equals("Y")){
        		
        		System.out.println("Decode gBest Solution");
        		System.out.println("---------------------------------------------------------");
        		//Decode the gBest Solution
        		int[] optimalRoute =  swarm.decodeOptimalSolution();
        		System.out.println("Optimal Route : " + Arrays.toString(optimalRoute) );
        		
        		System.out.println("---------------------------------------------------------");
        		//Print analysis for optimal route for the distribution model
        		System.out.println("Analysis of Optimal Route for dropOff Only: ");
        		System.out.println("---------------------------------------------------------");
        		GraphUI gui = new GraphUI();
        		Map<String, List<Integer>> distributionMap =  dm.analyzeOptimalRoute(optimalRoute);		
        		        		
        		for (Map.Entry<String, List<Integer>> entry : distributionMap.entrySet()) {
        		    System.out.println(entry.getKey()+" -> "+entry.getValue());		    
        			gui.displayGraph("Graph"+entry.getKey(),entry.getValue());
        		}
        		
        		System.out.println("---------------------------------------------------------");        		
        		System.out.println("Analysis of Optimal Route for simultaneously pickup and dropOff: ");
        		System.out.println("---------------------------------------------------------");
        		distributionMap = dm.analyzeOptimalRouteSimultaneous(optimalRoute);
        		for (Map.Entry<String, List<Integer>> entry : distributionMap.entrySet()) {
        		    System.out.println(entry.getKey()+" -> "+entry.getValue());
        		}
        		
        	}        	
        }
        
        //particleGraph.dispose();
        System.out.println("---------------------------------------------------------");
        System.out.println("PSO implementation for CVRP is done !! :) ");
		sc.close();
		
		
		
	}

}
