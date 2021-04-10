package components;

/**
 * Vehicle for transporting packages from the sorting center to the branches and back.
 * All vehicles of this type are in the sorting center.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Truck
 * @see     Node
 */

public class StandardTruck extends Truck implements Node {
    //Fields 2.7.1
    public int maxWeight;
    public Branch destination;
    private int loadingWeight=0;
    //Ctor 2.7.2
    /**
     * Ctor
     * The super keyword refers to superclass (parent) objects.
     */
    public StandardTruck(){
        super();
        System.out.println("Creating "+toString());

    }
    
    /**
     * Ctor receive as arguments: license plate number, vehicle model and maximum weight.
     * The super keyword refers to superclass (parent) objects.
     * @param:
     * 			licensePlate - license plate number
     * 			truckModel - vehicle model
     * 			maxWeight - maximum weight
     */

    public StandardTruck(String licensePlate,String truckModel,int maxWeight){
        super(licensePlate,truckModel);
        setMaxWeight(maxWeight);
        destination=null;
        System.out.println("Creating "+toString());
    }

    //methods 2.7.3

    /**
     * A method that handles the collection / receipt of a package by the implementing department.
     * The super keyword refers to superclass (parent) objects.
     * @param:
     * 			p - Package class
     */
    @Override
    public void collectPackage(Package p) {
        super.collectPackage(p);
//        ArratList[packNum]=p;
        packNum++;
    }
    
    /**
     *A method that handles the delivery of the package to the next person in the transfer chain.
     * The super keyword refers to superclass (parent) objects.
     * @param:
     * 			p - Package class
     */

    @Override
    public void deliverPackage(Package p) {

        super.deliverPackage(p);
        packNum--;

    }
    
    /**
     * funcation that return string
     * The super keyword refers to superclass (parent) objects.
     * @return: StandartTruck, maxWeight, destination
     */

    @Override
    public String toString() {
        return "StandartTruck "+
                super.toString() +
                ","+
                "maxWeight=" + maxWeight +
                ", destination=" + destination +
                ']';
    }
    
    /**
     * A method that performs a work unit.
     * checking timeleft
     * making local brunch
     * check destination
     * The super keyword refers to superclass (parent) objects.
     * @see: Status, Branch, Package, Hub
     */

    @Override
    public void work() {
//TODO להשלים משקל כולל את חבילות בהעמסה
        super.work();// timeleft-- and checking if available
        if(!available){
            super.work();
            if(timeLeft==0){
                System.out.println("StandardTruck"+getTruckID()+"arrived to Brunch"+getDestination().getBranchId());
                for (int i=0;i<ArratList.length;i++){//finish at HUB
                    if(ArratList[i].getStatus()==Status.BRANCH_STORAGE3 /*destination.getBranchName().equals("HUB"*/){
                        ArratList[i].addTracking(this,Status.HUB_TRANSPORT4);//TODO use HUB_TRAN4
                        ArratList[i].setStatus(Status.HUB_STORAGE5);//TODO use HUB_STOR5
                        destination.collectPackage(ArratList[i]);
                        deliverPackage(ArratList[i]);
                        //if(destination instanceof hub)
                            //destination.deliverPackage(ArratList[i]);
                        System.out.println("StandardTruck"+getTruckID()+" UNloaded packages at "+destination.getBranchName());

                        //this.setAvailable(true);

                        loadingWeight=0;

                    }
                    else if(ArratList[i].getStatus()==Status.HUB_STORAGE5) {//finish at local brunch
                        for(int k=0;k<destination.getListPackages().length && loadingPossible(loadingWeight, destination.getPack(k));k++){
                            this.collectPackage(destination.getPack(k));
                            this.ArratList[i].setStatus(Status.DELIVERY7);
                            this.ArratList[i].addTracking(this,Status.BRANCH_TRANSPORT6);
                            destination.deliverPackage(destination.getPack(k));
                            //System.out.println("StandardTruck"+getTruckID()+"arrived to Brunch"+getDestination().getBranchId());

                            System.out.println("StandardTruck"+getTruckID()+" loaded packages at "+destination.getBranchName());
                        }
                    }


                }

                if(getDestination()instanceof Hub){
                    setAvailable(true);
                }
                else{
                    for(int b=0;b<destination.getListPackages().length && loadingPossible(loadingWeight, destination.getPack(b));b++){
                        if(this.ArratList[b].getStatus()==Status.BRANCH_STORAGE3){
                            collectPackage(destination.getPack(b));
                            ArratList[b].setStatus(Status.HUB_TRANSPORT4);//todo use HUB_TRANSPORT4
                            ArratList[b].addTracking(this,Status.HUB_TRANSPORT4);
                            destination.deliverPackage(destination.getPack(b));//remove from brunch
                        }
                    }
                    System.out.println("StandardTruck"+getTruckID()+" loaded packages at "+destination.getBranchName());
                    setDestination(MainOffice.hub);

                    setTimeLeft(((int)Math.random())*7+1);
                    //System.out.println("StandardTruck!!!!!"+getTruckID()+" is on it's way to the "+destination.getBranchName()+", time to arrive:"+this.getTimeLeft());

                }

            }


        }
    }
    
    /**
     * boolean function
     * A method that checking possible loading
     * checking if the size of pack is for standard truck
     * @param:
     * 			currWeight - (int) - carey weight
     * 			pack - Package class
     * @return: true or false
     */

    public boolean loadingPossible(int currWeight,Package pack){
        if(pack instanceof StandardPackage){
            if(currWeight+((StandardPackage)pack).getWeight()<=this.maxWeight) {
                loadingWeight+=((StandardPackage)pack).getWeight();
                return true;
            }
            else
                return false;
        }
        else if(pack instanceof SmallPackage){
            if(currWeight+1<=this.maxWeight) {
                loadingWeight+=1;
                return true;
            }
            else
                return false;
        }
        System.out.println("Error loading possible pack");
        return false;
    }
    
    /**
     * get function for max Weight
     * @return: maxWeight
     */

    public int getMaxWeight() {
        return maxWeight;
    }
    
    /**
     * set function for weight
     * Math.abs(maxWeight) returns the absolute value of a given argument.
     * @param: maxWeight = maxWeight
     */

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = Math.abs(maxWeight);
    }
    
    /**
     * get function for destination
     * @return: destination
     */

    public Branch getDestination() {
        return destination;
    }
    
    /**
     * set function for destination
     * @param: destination = destination
     */

    public void setDestination(Branch destination) {
        this.destination = destination;
    }


}
