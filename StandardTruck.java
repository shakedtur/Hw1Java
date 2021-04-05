package components;

public class StandardTruck extends Truck implements Node {
    //Fields 2.7.1
    public int maxWeight;
    public Branch destination;
    //Ctor 2.7.2
    public StandardTruck(){
        super();
    }

    public StandardTruck(String licensePlate,String truckModel,int maxWeight){
        super(licensePlate,truckModel);
        setMaxWeight(maxWeight);
    }

    //methods 2.7.3


    @Override
    public void collectPackage(Package p) {
        super.collectPackage(p);
//        ArratList[packNum]=p;
        packNum++;
    }

    @Override
    public void deliverPackage(Package p) {

        //TODO
        super.deliverPackage(p);
    }

    @Override
    public void work() {
//TODO להשלים משקל כולל את חבילות בהעמסה
        super.work();// timeleft-- and checking if available
        if(!available){
            if(timeLeft==0){
               System.out.println("StandardTruck"+getTruckID()+"arrived to Brunch"+getDestination().getBranchId());
                for (int i=0;i<ArratList.length;i++){
                    if(destination.getBranchName().equals("HUB")){
                        ArratList[i].addTracking(this,Status.BRANCH_TRANSPORT);
                        ArratList[i].setStatus(Status.HUB_STORAGE);
                        deliverPackage(ArratList[i]);
                        this.setAvailable(true);
                    }
                    else {
                        for(int k=0;k<destination.getListPackages().length;k++){
                            this.collectPackage(destination.getPack(k));
                            this.ArratList[i].setStatus(Status.HUB_TRANSPORT);
                            this.ArratList[i].addTracking(this,Status.HUB_TRANSPORT);
                            destination.deliverPackage(destination.getPack(k));

                        }
                        setTimeLeft((int)Math.random()*7+1);
                        System.out.println("StandardTruck"+getTruckID()+"is on it's way to the HUB, time to arrive:"+this.getTimeLeft());

                    }

                }
            }
        }
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = Math.abs(maxWeight);
    }

    public Branch getDestination() {
        return destination;
    }

    public void setDestination(Branch destination) {
        this.destination = destination;
    }


}
