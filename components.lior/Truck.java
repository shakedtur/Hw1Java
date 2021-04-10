package components;

import java.util.Arrays;
import java.util.Random;
//abstracts class

/**
 * Abstracts department representing the vehicles for transporting packages.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Node
 */

public abstract class Truck implements Node {
    //Fields 2.5.1
    private static int numTruck=2000;
    public int truckID;
    public String licensePlate;
    public String truckModel;
    public boolean available;
    public int timeLeft;
    public Package[] ArratList;
    protected int packNum=0;

    //Ctor 2.5.2
    /**
     * Ctor - A random default builder that produces an object with a license plate and model of a vehicle at random.
     * A model consists of hundreds of M and a number between 0 and 4.
     * A license plate consists of three numbers separated by a line, according to the pattern xxx-xx-xxx.
     * @see: Package
     */
    public Truck(){
//        Random r=new Random();
//        truckModel="M"+(String)((int)(Math.random()*4));
//        int first,middle,last;
        truckID=(numTruck++);
        Random r=new Random();
        int n=r.nextInt(5);
        truckModel=("M"+n);
        int f,m,l;
        f=r.nextInt(1000);//first
        m=r.nextInt(100);//middle
        l=r.nextInt(1000);//last
        licensePlate=(f+"-"+m+"-"+l);
        available=true;
        timeLeft=0;
        packNum=5;
        ArratList=new Package[packNum];
        for (int i=0;i<ArratList.length;i++){
            ArratList[i]=new Package();
        }
        packNum=0;

    }
    
    /**
     * Ctor which receives as arguments a license plate number and model of the vehicle and produces an object
     * @param:
     * 			licensePlate - license plate number
     * 			truckModel - model of the vehicle
     */
    
    public Truck(String licensePlate,String truckModel){
        truckID=numTruck++;
        this.licensePlate=licensePlate;
        this.truckModel=truckModel;
        available=true;
        timeLeft=0;
        packNum=5;
        ArratList=new Package[packNum];
        packNum=0;

    }
    
    /**
     * funcation that return string
     * @return: 
     * 			truckID - number truckID
     * 			licensePlate - number license Plate
     * 			truckModel - truck Model
     * 			available - if truck available
     */
    
    //methods 2.5.3
    @Override
    public String toString() {
        return "["+
                "truckID=" + truckID +
                ", licensePlate='" + licensePlate + '\'' +
                ", truckModel='" + truckModel + '\'' +
                ", available=" + available ;
                //", timeLeft=" + timeLeft +
                //", ArratList=" + Arrays.toString(ArratList) +
                //']';

    }
    
    /**
     * A method that handles the collection / receipt of a package by the implementing department.
     * @param: p - Package class
     * @see: Package
     */

    @Override
    public void collectPackage(Package p) {
        if(packNum<ArratList.length) {
            ArratList[packNum] = p;
        }
        else{
            Package[] temp=new Package[ArratList.length+7];
            for (int i=0;i<temp.length;i++){
                if(i<ArratList.length)
                    temp[i]=ArratList[i];
                else if(i==ArratList.length)
                    temp[i]=p;
                else
                    temp[i]=new Package();
            }
            ArratList=temp;
        }

    }
    
    /**
     * A method that handles the delivery of the package to the next person in the transfer chain.
     * @param: p - Package class
     */

    @Override
    public void deliverPackage(Package p) {
//        p.addTracking(this.timeLeft,this,Status.DELIVERED);
//        this.available=true;
        for(int i=0;i<ArratList.length && ArratList[i]!=null  ;i++){
            if(ArratList[i].equals(p)) {
                ArratList[i] = null;
                if(this instanceof StandardTruck)
                    packNum--;
            }
        }
    }
    
    /**
     * A method that performs a work unit.
     * void function for time
     */

    @Override
    public void work() {
        if(!available && timeLeft>0){
            timeLeft--;
        }
    }
    
    /**
     * get function for truckID
     * @return: truckID
     */

    public int getTruckID() {
        return truckID;
    }
    
    /**
     * get function for licensePlate
     * @return: licensePlate
     */

    public String getLicensePlate() {
        return licensePlate;
    }
    
    /**
     * get function for truckModel
     * @return: truckModel
     */

    public String getTruckModel() {
        return truckModel;
    }
    
    /**
     * get boolean function for available
     * @return: available
     */

    public boolean isAvailable() {
        return available;
    }
    
    /**
     * get function for timeLeft
     * @return: timeLeft
     */

    public int getTimeLeft() {
        return timeLeft;
    }
    
    /**
     * get function for ArratList
     * @return: ArratList
     */

    public Package[] getArratList() {
        return ArratList;
    }
    
    /**
     * get function for truckID
     * @return: truckID
     */

    public void setTruckID(int truckID) {
        truckID = truckID;
    }
    
    /**
     * set function for licensePlate
     * @param: licensePlate = licensePlate
     */

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    /**
     * set function for truckModel
     * @param: truckModel = truckModel
     */

    public void setTruckModel(String truckModel) {
        this.truckModel = truckModel;
    }
    
    /**
     * set boolean function for available
     * @param: available = available
     */

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    /**
     * set function for timeLeft
     * @param: timeLeft = timeLeft
     */

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
    
    /**
     * set function for arratList
     * @param: arratList = arratList
     */

    public void setArratList(Package[] arratList) {
        ArratList = arratList;
    }

}
