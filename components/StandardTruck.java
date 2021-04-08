package components;

public class StandardTruck extends Truck implements Node {
    //Fields 2.7.1
    public int maxWeight;
    public Branch destination;
    private int loadingWeight=0;
    //Ctor 2.7.2
    public StandardTruck(){
        super();
        System.out.println("Creating "+toString());

    }

    public StandardTruck(String licensePlate,String truckModel,int maxWeight){
        super(licensePlate,truckModel);
        setMaxWeight(maxWeight);
        destination=null;
        System.out.println("Creating "+toString());
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

        super.deliverPackage(p);
        packNum--;

    }

    @Override
    public String toString() {
        return "StandartTruck "+
                super.toString() +
                ","+
                "maxWeight=" + maxWeight +
                ", destination=" + destination +
                ']';
    }

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
