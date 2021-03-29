package components;

import java.util.Arrays;
import java.util.Random;
//abstracts class
public abstract class Truck {
    //Fields 2.5.1
    private static int numTruck=2000;
    public int truckID;
    public String licensePlate;
    public String truckModel;
    public boolean available;
    public int timeLeft;
    public Package[] ArratList;

    //Ctor 2.5.2
    public Truck(){
//        Random r=new Random();
//        truckModel="M"+(String)((int)(Math.random()*4));
//        int first,middle,last;
        truckID=numTruck++;
        Random r=new Random();
        int n=r.nextInt(4);
        truckModel=("M"+n);
        int f,m,l;
        f=r.nextInt(1000);//first
        m=r.nextInt(100);//middle
        l=r.nextInt(1000);//last
        licensePlate=(f+"-"+m+"-"+l);
        available=true;
        timeLeft=0;
        ArratList=null;
    }
    public Truck(String licensePlate,String truckModel){
        truckID=numTruck++;
        this.licensePlate=licensePlate;
        this.truckModel=truckModel;
        available=true;
        timeLeft=0;
        ArratList=null;
    }
    //methods 2.5.3
    @Override
    public String toString() {
        return "Truck[" +
                "truckID=" + truckID +
                ", licensePlate='" + licensePlate + '\'' +
                ", truckModel='" + truckModel + '\'' +
                ", available=" + available +
                ", timeLeft=" + timeLeft +
                ", ArratList=" + Arrays.toString(ArratList) +
                ']';
    }

    public int getTruckID() {
        return truckID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getTruckModel() {
        return truckModel;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public Package[] getArratList() {
        return ArratList;
    }

    public void setTruckID(int truckID) {
        truckID = truckID;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setTruckModel(String truckModel) {
        this.truckModel = truckModel;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setArratList(Package[] arratList) {
        ArratList = arratList;
    }

}
