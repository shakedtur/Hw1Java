package components;

import java.util.ArrayList;
import java.util.Random;


public  abstract class Truck implements Node {
	private static int countID=2000;
	final private int truckID;
	private String licensePlate;
	private String truckModel;
	private boolean available=true;
	private int timeLeft=0;
	private ArrayList<Package> packages=new ArrayList<Package>();
		
	
	//default random constructor
	public Truck() {
		truckID=countID++;
		Random r= new Random();
		licensePlate=(r.nextInt(900)+100)+"-"+(r.nextInt(90)+10)+"-"+(r.nextInt(900)+100);
		truckModel="M"+r.nextInt(5);
		System.out.print("Creating ");
	}

	
	public Truck(String licensePlate,String truckModel) {
		truckID=countID++;
		this.licensePlate=licensePlate;
		this.truckModel=truckModel;
		System.out.print("Creating ");
	}
	
	
	public ArrayList<Package> getPackages() {
		return packages;
	}


	public int getTimeLeft() {
		return timeLeft;
	}

	
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}


	@Override
	public String toString() {
		return "truckID=" + truckID + ", licensePlate=" + licensePlate + ", truckModel=" + truckModel + ", available= " + available ;
	}


	@Override
	public void collectPackage(Package p) {
		setAvailable(false);
		int time=p.getSenderAddress().getStreet()%10+1;
		this.setTimeLeft(time);
		this.packages.add(p);
		p.setStatus(Status.COLLECTION);
		p.addTracking(new Tracking(MainOffice.getClock(), this, p.getStatus()));
		System.out.println(getName() + " is collecting package " + p.getPackageID() + ", time to arrive: "+ getTimeLeft()  );
	}


	public boolean isAvailable() {
		return available;
	}
	

	public int getTruckID() {
		return truckID;
	}

	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	public void addPackage(Package p) {
		this.packages.add(p);
	}
	
	
	public String getName() {
		return this.getClass().getSimpleName()+" "+ getTruckID();
	}
	
}
