package VehicleRoutingProblem;

public class Store {
	
	int demand;
	int recyclables;
	
	public Store(int demand){
		this.demand = demand;
	}
	
	public Store(int demand, int recycables){
		this.demand = demand;
		this.recyclables = recycables;
	}

	@Override
	public String toString() {
		return "Store [demand=" + demand + ", recyclables=" + recyclables + "]";
	}

	
	
	
}
