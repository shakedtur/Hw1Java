package components;

import java.util.Arrays;

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
    }
    public Van(String licensePlate,String truckModel){

        super(licensePlate,truckModel);
        ArratList=new Package[1];
        packNum=1;
    }
    //methods 2.6.3

    @Override
    public void collectPackage(Package p) {
        ArratList[packNum]=p;
    }

    @Override
    public void deliverPackage(Package p) {
        //Todo deliver pack
    }

    @Override
    //need to undersend
    public void work() {
        if(!available){
            timeLeft--;
            if (timeLeft==0){
                //TODO הרוב נעשה..צריך לבדוק באגים
                if(ArratList[packNum].getStatus()== Status.COLLECTION){
                    ArratList[packNum].addTracking(this,Status.COLLECTION);//updat tracking list
                    this.deliverPackage(ArratList[packNum]);
                    //collection by brunch
                    ArratList[packNum].setStatus(Status.BRANCH_STORAGE);
                    System.out.println("Van"+this.getTruckID()+"has collected package"+ArratList[packNum].getPackageID()+"and arrived back to branch"/*+Branch.getBranchName()*/ );
                    this.setAvailable(true);
                }
                if(ArratList[packNum].getStatus()==Status.DISTRIBUTION){
                    if(ArratList[packNum] instanceof SmallPackage){
                        if(((SmallPackage) ArratList[packNum]).getacknowledge()){
                            System.out.println(ArratList[packNum].getPackageID()+"is deliver to customer successfuly");//אישור מסירה
                        }
                    }
                    ArratList[packNum].addTracking(this,Status.DISTRIBUTION);
                    ArratList[packNum].setStatus(Status.DELIVERED);
                    this.deliverPackage(ArratList[packNum]);//remove pack from pack list
                    System.out.println("Van"+this.getTruckID()+ "is delivering package"+this.ArratList[packNum].packageID+", time left:"+ this.timeLeft);
                }

            }


        }
    }

    @Override
    public String toString() {
        return "Van{" +
                "truckID=" + truckID +
                ", licensePlate='" + licensePlate + '\'' +
                ", truckModel='" + truckModel + '\'' +
                ", available=" + available +
                ", timeLeft=" + timeLeft +
                ", ArratList=" + Arrays.toString(ArratList) +
                '}';
    }
}
