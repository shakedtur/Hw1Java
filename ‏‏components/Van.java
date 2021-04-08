package components;

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
    public Van(){
        super();
        System.out.println("Creating "+this.toString());

    }
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

    @Override
    public void collectPackage(Package p) {
        p.setStatus(Status.BRANCH_STORAGE3);
        p.addTracking(this,Status.COLLECTION2);//todo  use Status.BRANCH_STORAGE
        System.out.printf("Van"+ getTruckID()+"has collecting package"+p.getPackageID()+ "and arrived back to branch"+p.getSenderAddress().getZip());
        //ArratList[packNum]=p;
        super.collectPackage(p);
    }

    @Override
    public void deliverPackage(Package p) {
        p.addTracking(null,Status.DELIVERED9);//todo  use Status.DELIVERED
        p.setStatus(Status.DELIVERED9);
        if(ArratList[packNum].equals(p)) {
            System.out.printf("Van %d has delivered package %d to the destination\n", getTruckID(), p.getPackageID());
            ArratList[packNum] = null;
        }
        else
            System.out.println("Van deliver package error!!!!!!!!!!!!!!!!!!!!!!!!");
        this.setAvailable(true);
    }

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
                if(ArratList[packNum].getStatus()==Status.DISTRIBUTION8){
                    if(ArratList[packNum] instanceof SmallPackage){
                        if(((SmallPackage) ArratList[packNum]).getacknowledge()){
                            System.out.printf("Small package"+ArratList[packNum].getPackageID()+" with notification arrived to it's destination");
                        }
                    }
                    ArratList[packNum].addTracking(this,Status.DISTRIBUTION8);//TODO may need to delete use Status.DISTRIBUTION
                    //ArratList[packNum].setStatus(Status.DELIVERED);
                    this.deliverPackage(ArratList[packNum]);//remove pack from pack list
                    //System.out.println("Van"+this.getTruckID()+ "is delivering package"+this.ArratList[packNum].packageID+", time left:"+ this.timeLeft);
                }

            }


        }
    }

    @Override
    public String toString() {
        return "Van" +super.toString()+"]";
    }
}
