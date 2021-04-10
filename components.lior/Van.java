package components;

/**
 * A vehicle that collects a package from the address of the sender to the local branch
 * and delivers the package from the destination branch to the address that receives only one package per trip.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Node
 * @see     Truck
 */

public class Van extends Truck implements Node{
    //Ctor2.6.2

//    private static int numTruck=2000;
//    public int truckID;
//    public String licensePlate;
//    public String truckModel;
//    public boolean available;
//    public int timeLeft;
//    public Package[] ArratList;
//    private int packNum=0;
	
    /**
     * Ctor that produces a random object according to the same rules as in the parent class.
     * The super keyword refers to superclass (parent) objects.
     */
	
    public Van(){
        super();
        System.out.println("Creating "+this.toString());

    }
    
    /**
     * Ctor that Produces an object with data parameters
     * The super keyword refers to superclass (parent) objects.
     * @param:
     * 			licensePlate - number license Plate
     * 			truckModel - truck Model
     * @see: Package
     */
    
    public Van(String licensePlate,String truckModel){

        super(licensePlate,truckModel);
        this.ArratList=new Package[1];
        for(int i=0;i<ArratList.length;i++){
            ArratList[i]=new Package();
        }
        packNum=0;
        System.out.println("Creating "+this.toString());
    }
    //methods 2.6.3

    /**
     * A method that handles the collection / receipt of a package by the implementing department.
     * The super keyword refers to superclass (parent) objects.
     * @param: p - Package class
     * @see: Status, Packag, Truck	 		
     */
    
    @Override
    public void collectPackage(Package p) {
        p.setStatus(Status.BRANCH_STORAGE3);
        p.addTracking(this,Status.COLLECTION2);//todo  use Status.BRANCH_STORAGE
        System.out.println("Van "+ getTruckID()+" has collecting package "+p.getPackageID()+ " and arrived back to branch "+(int)(p.getSenderAddress().getZip()));
        //ArratList[packNum]=p;
        super.collectPackage(p);
    }
    
    /**
     * A method that handles the delivery of the package to the next person in the transfer chain.
     * @param: p - Package class
     * @see: Status, Packag, Truck		
     */

    @Override
    public void deliverPackage(Package p) {
        p.addTracking(null,Status.DISTRIBUTION8);//todo  use Status.DELIVERED
        p.setStatus(Status.DELIVERED9);
        if(ArratList[packNum].equals(p)) {
            System.out.printf("Van %d has delivered package %d to the destination\n", getTruckID(), p.getPackageID());
            ArratList[packNum] = null;
        }
        else
            System.out.println("Van deliver package error!!!!!!!!!!!!!!!!!!!!!!!!");
        this.setAvailable(true);
    }
    
    /**
     * Performs a work unit at each beat
     * An available vehicle does nothing.
     * A vehicle that is in the middle of a trip reduces the time left to end the trip (timeLeft) by 1.
     * If after the reduction the time value is equal to zero, then the trip is over and a vehicle has performed the task for which it was sent.
     * If the purpose of the trip was to collect a package from a sending customer (COLLECTION)
     * The package at this stage will be transferred from the vehicle to the branch, the status of the package will be updated, a suitable listing will be added to the tracking list of the package.
     * will be printed that the vehicle has picked up the package and arrived back at the branch
     * The vehicle will change its condition to available
     * If the purpose of the trip was to deliver the package to the customer (DISTRIBUTION)
     * The package will be removed from the list of packages in the vehicle, the status of the package and the transfer history will be updated accordingly
     * will be printed that the package has been delivered to the customer
     * In the case of a small package with the option to send a delivery confirmation, a notification of sending a delivery confirmation will be printed.
     * @see: SmallPackage, Status, Tracking, Packag, Truck			
     */

    @Override
    //need to undersend
    public void work() {
        super.work();
        if(!available){
            //timeLeft--;
            if (timeLeft==0){
                //TODO הרוב נעשה..צריך לבדוק באגים
                if(ArratList[packNum].getStatus()== Status.COLLECTION2){
                    ArratList[packNum].addTracking(this,Status.COLLECTION2);//updat tracking list todo  use Status.COLLECTION
                    //collection by brunch
                   // ArratList[packNum].setStatus(Status.BRANCH_STORAGE);
                    //System.out.println("Van "+this.getTruckID()+" has collected package "+ArratList[packNum].getPackageID()+" and arrived back to branch"/*+Branch.getBranchName()*/ );
                    this.collectPackage(ArratList[packNum]);
                    //this.setAvailable(true);
                }
                else if(ArratList[packNum].getStatus()==Status.DISTRIBUTION8){
                    if(ArratList[packNum] instanceof SmallPackage){
                        if(((SmallPackage) ArratList[packNum]).getacknowledge()){
                            System.out.println("Small package"+ArratList[packNum].getPackageID()+" with notification arrived to it's destination");
                        }
                    }
                    ArratList[packNum].addTracking(this,Status.DISTRIBUTION8);//TODO may need to delete use Status.DISTRIBUTION
                    ArratList[packNum].addTracking(null,Status.DELIVERED9);//TODO may need to delete use Status.DISTRIBUTION

                    ArratList[packNum].setStatus(Status.DELIVERED9);
                    this.deliverPackage(ArratList[packNum]);//remove pack from pack list
                    //System.out.println("Van"+this.getTruckID()+ "is delivering package"+this.ArratList[packNum].packageID+", time left:"+ this.timeLeft);
                }

            }


        }
    }
    
    /**
     * funcation that return string
     * The super keyword refers to superclass (parent) objects.
     * @return: Van
     */

    @Override
    public String toString() {
        return "Van" +super.toString()+"]";
    }
}
