package components;

public class StandardTruck extends Truck implements Node {
	
	public int maxWeight;
	public Branch destination;
	
	public StandardTruck() {
		// TODO Auto-generated constructor stub
	}

	public StandardTruck(String licensePlate,String truckModel,int maxWeight) {
        this.licensePlate=licensePlate;
        this.truckModel=truckModel;
        this.maxWeight = maxWeight;
	}
	
	@Override
	public void collectPackage(Package p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliverPackage(Package p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void work() {
		// TODO Auto-generated method stub
		
	}
}
