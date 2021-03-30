package components;

public class NonStandardTruck extends Truck implements Node  {

	public int width;
	public int length;
	public int height;
	
	public NonStandardTruck() {
		// TODO Auto-generated constructor stub
	}
	
	public NonStandardTruck(String licensePlate, String truckModel, int length, int width, int height) {
        this.licensePlate=licensePlate;
        this.truckModel=truckModel;
        this.length=length;
        this.width=width;
        this.height = height;
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
