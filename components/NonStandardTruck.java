package components;

import java.util.Random;


public class NonStandardTruck extends Truck implements Node  {
    //fields:2.8.1
    public int width;
    public int length;
    public int height;
    //ctor2.8.2
    public NonStandardTruck() {
        super();
        Random rand=new Random();
        int h=(rand.nextInt(4)+3)*100;
        int w=(rand.nextInt(5)+3)*100;
        int l=(rand.nextInt(10)+3)*100;
        setHeight(h);
        setWidth(w);
        setLength(l);
        System.out.println("Creating "+toString());

    }

    public NonStandardTruck(String licensePlate, String truckModel, int length, int width, int height) {
        super(licensePlate,truckModel);
        setLength(length);
        setWidth(width);
        setHeight(height);
        System.out.println("Creating "+toString());
    }

    @Override
    public void collectPackage(Package p) {

        p.addTracking(this,Status.DISTRIBUTION8);//todo use Status.DISTRIBUTION
        p.setStatus(Status.DISTRIBUTION8);
        int drivetime=Math.abs((p.senderAddress.getStreet()-(p.destinationAddress.getStreet())/10)%10)+1;
        this.setTimeLeft(drivetime);
        super.collectPackage(p);
        System.out.println("NonStandardTruck "+ getTruckID()+" has collecting package"+p.getPackageID()+ ", time to arrive: "+drivetime);

    }

    @Override
    public void deliverPackage(Package p) {
        p.setStatus(Status.DELIVERED9);
        p.addTracking(null,Status.DELIVERED9);//todo use Status.DELIVERED
        System.out.println("NonStandartTruck"+this.getTruckID()+"has delivered package "+ArratList[packNum].getPackageID()+" to the destination");
        super.deliverPackage(p);//remove pack from the truck
        this.setAvailable(true);
        super.deliverPackage(p);


    }

    @Override
    public void work() {
        // TODO צריך לבדוק באגים והדפסות לפי הקובץ
        super.work();
        if(!available){

            if(timeLeft==0){
                if(this.ArratList[packNum].getStatus().equals(Status.COLLECTION2)){//deivery MODE
//                    int drivetime=Math.abs(ArratList[packNum].senderAddress.getStreet()-ArratList[packNum].destinationAddress.getStreet()%10)+1;
//                    timeLeft=drivetime;
                    //ArratList[packNum].setStatus(Status.DISTRIBUTION);
                    //ArratList[packNum].addTracking(this,Status.DISTRIBUTION);
                    this.collectPackage(this.ArratList[packNum]);

                }
                else if (this.ArratList[packNum].getStatus().equals(Status.DISTRIBUTION8)){
                    this.deliverPackage(ArratList[packNum]);
//                    System.out.println("NonStandartTruck"+this.getTruckID()+"has delivered package"+ArratList[packNum].getPackageID()+"to the destination");
//                    ArratList[packNum].setStatus(Status.DELIVERED);
//                    ArratList[packNum].addTracking(this,Status.DELIVERED);
//                    this.setAvailable(true);
                }

            }
            else if (this.ArratList[packNum].getStatus().equals(Status.DISTRIBUTION8)){
                System.out.println("NonStandardTruck "+ getTruckID()+" delivering package"+ArratList[packNum].getPackageID()+ ", time to arrive: "+getTimeLeft());
            }

        }



    }

    public boolean possibleLoad(NonStandardPackage p){
        return (p.getHeight()<=this.getHeight()&&p.getLength()<=this.getLength()&&p.getWidth()<=getWidth());

    }

    @Override
    public String toString() {
        return "NonStandardTruck" +
                super.toString()+
                ", length=" + length +
                "width=" + width +
                ", height=" + height +
                ']';
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

