package VehicleRoutingProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DistributionModel {
	
	int noOfStores;
	int noOfVehicles;
	
	double[][] distanceMatrix;
	Store[] stores;
	Vehicle[] vehicles;
	
	private static Random rand = new Random();
	
	public DistributionModel(int storeCount, int[] vehicleCapacity, int vehicleCount,int msd, int msr){	
		noOfStores = storeCount;
		noOfVehicles = vehicleCount;
		distanceMatrix = new double[storeCount+1][storeCount+1];
		stores = new Store[storeCount];
		vehicles = new Vehicle[vehicleCount];
		
		//initialize the distance between stores and store demands 
		for(int i=0; i<noOfStores+1; i++){
			for(int j=i; j<noOfStores+1; j++){
				if(i==j)
					distanceMatrix[i][j] = 0;
				else
					distanceMatrix[i][j] = distanceMatrix[j][i] = getRandomDistance(50);
			}			
		}
		
		for(int i=0; i<noOfStores; i++){
			int demand = getRandomDemand(msd);
			int rec = getRandomDemand(msr);
			if( rec > demand){
			    int temp = demand;
				demand = rec;
				rec = temp;
			}
			else if(rec == demand)
				rec--;
			stores[i] = new Store(demand, rec);
		}
		
		//initialize capacity of each Vehicle
		for(int i=0; i<noOfVehicles; i++){
			vehicles[i] = new Vehicle(vehicleCapacity[i]);
		}
	}	
	
	
	public void printModelDetails(){
		
		System.out.println("No Of Stores : " + noOfStores);		
		System.out.println("Store Details : ");
		for(Store s: stores)
			System.out.println(s);
		System.out.println();
		
		System.out.println("No Of Vehicles : " + noOfVehicles);
		for(Vehicle v: vehicles)
			System.out.println(v);
		System.out.println();
		
		System.out.println("Distance Matrix : ");
		System.out.print("\tDepot\t");
		for(int k=0; k<stores.length;k++)  
			System.out.print("Store"+(k+1)+"\t");
		System.out.println();
		
		for(int i=0; i<distanceMatrix.length; i++){
			System.out.print((i==0?"Depot":"Store"+i)  + "\t");
			for(int j=0; j<distanceMatrix.length; j++){
				System.out.print(distanceMatrix[i][j] + "\t");  	
			}
			System.out.println();
		}
		
		
	}
	
	
	public Map<String, List<Integer>> analyzeOptimalRoute(int[] optimalRoute){
		Map<String, List<Integer>> distribution = new HashMap<String, List<Integer>>();
		List<Integer> route;
		int tripsCount =0;
		int totalDistance = 0;
		for(int v=0; v<noOfVehicles; v++){
			route = new ArrayList<Integer>();
			route.add(0);
			tripsCount=0;
			totalDistance=0;
			int availableCapacity = vehicles[v].capacity; 			
			for(int i=0; i<optimalRoute.length; i++){
				int storeCapacity = stores[optimalRoute[i]-1].demand;
				if(availableCapacity - storeCapacity >= 0){
					route.add(optimalRoute[i]);
					totalDistance+=distanceMatrix[route.get(route.indexOf(optimalRoute[i])-1)][optimalRoute[i]];
					availableCapacity = availableCapacity - storeCapacity;
				}
				else {
					tripsCount++;
					availableCapacity = vehicles[v].capacity; 
					route.add(0);
					totalDistance+=distanceMatrix[optimalRoute[i-1]][0];
					i--;
				}
			}
			route.add(0);			
			tripsCount++;
			distribution.put("VehicleCap:"+ vehicles[v].capacity+",Trips:"+tripsCount+",TotalDistance:"+totalDistance, route);
		}
		return distribution;
		
	}
	
	//with simultaneously pickup and dropoff
	public Map<String, List<Integer>> analyzeOptimalRouteSimultaneous(int[] optimalRoute){
		Map<String, List<Integer>> distribution = new HashMap<String, List<Integer>>();
		List<Integer> route;
		int tripsCount =0;
		int totalDistance = 0;
		for(int v=0; v<noOfVehicles; v++){
			route = new ArrayList<Integer>();
			route.add(0);
			tripsCount=0;
			totalDistance=0;
			int availableCapacity = vehicles[v].capacity; 	
			int availableRecyclables = 0;
									
			for(int i=0; i<optimalRoute.length; i++){
			
				int storeCapacity = stores[optimalRoute[i]-1].demand;
				int storeRecycle = stores[optimalRoute[i]-1].recyclables;
				
				if((storeRecycle + availableCapacity - storeCapacity) < vehicles[v].capacity && storeCapacity < availableCapacity)// && (availableCapacity - storeCapacity + storeRecycle) < vehicles[v].capacity)
				{
					availableCapacity =availableCapacity - storeCapacity;
					availableRecyclables+=storeRecycle;
					route.add(optimalRoute[i]);
					totalDistance+=distanceMatrix[route.get(route.indexOf(optimalRoute[i])-1)][optimalRoute[i]];
					
				}
				else{
					
					tripsCount++;
					availableCapacity = vehicles[v].capacity; 
					availableRecyclables=0;
					route.add(0);
					totalDistance+=distanceMatrix[optimalRoute[i-1]][0];
					i--;
					
				}
				
				
			}
			route.add(0);			
			tripsCount++;
			distribution.put("VehicleCap:"+ vehicles[v].capacity+",Trips:"+tripsCount+",TotalDistance:"+totalDistance, route);
		}
		return distribution;
		
	}
	
	
	private int getRandomDistance(int max){
		return rand.nextInt(max) + 1;		
	}
	
	private int getRandomDemand(int max){
		return rand.nextInt(max) + 1;		
	}



	public int getNoOfStores() {
		return noOfStores;
	}


	public void setNoOfStores(int noOfStores) {
		this.noOfStores = noOfStores;
	}


	public int getNoOfVehicles() {
		return noOfVehicles;
	}


	public void setNoOfVehicles(int noOfVehicles) {
		this.noOfVehicles = noOfVehicles;
	}


	public double[][] getDistanceMatrix() {
		return distanceMatrix;
	}


	public void setDistanceMatrix(double[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}


	public Store[] getStores() {
		return stores;
	}


	public void setStores(Store[] stores) {
		this.stores = stores;
	}


	public Vehicle[] getVehicles() {
		return vehicles;
	}


	public void setVehicles(Vehicle[] vehicles) {
		this.vehicles = vehicles;
	}	
	
	
}
