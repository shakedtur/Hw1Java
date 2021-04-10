package components;

import java.util.Random;

/**
 * A vehicle for transporting packages of non-standard size (exceptional cargo). All vehicles of this type are in the sorting center.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Truck   
 * @see     Node   
 */


public class NonStandardTruck extends Truck implements Node  {
    //fields:2.8.1
    public int width;
    public int length;
    public int height;
    //ctor2.8.2
    
    /**
     * Ctor which produces an object with a vehicle ID number and model at random.
     * The super keyword refers to superclass (parent) objects.
     */
    
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
    
    /**
     * Ctor gets:  plate number, vehicle model and maximum length / width / height of cargo that the vehicle can carry.
     * The super keyword refers to superclass (parent) objects.
     * @param:	
     * 			licensePlate - plate number
     * 			truckModel - vehicle model
     * 			width, length, height - cargo dimensions
     */

    public NonStandardTruck(String licensePlate, String truckModel, int length, int width, int height) {
        super(licensePlate,truckModel);
        setLength(length);
        setWidth(width);
        setHeight(height);
        System.out.println("Creating "+toString());
    }
    
    /**
     * A method that handles the collection / receipt of a package by the implementing department
     * The super keyword refers to superclass (parent) objects.
     * @param: Package - Package class
     * @see: Package , Status
     */

    @Override
    public void collectPackage(Package p) {

        p.addTracking(this,Status.DISTRIBUTION8);//todo use Status.DISTRIBUTION
        p.setStatus(Status.DISTRIBUTION8);
        int drivetime=Math.abs((p.senderAddress.getStreet()-(p.destinationAddress.getStreet())/10)%10)+1;
        this.setTimeLeft(drivetime);
        super.collectPackage(p);
        System.out.println("NonStandardTruck "+ getTruckID()+" has collecting package"+p.getPackageID()+ ", time to arrive: "+drivetime);

    }
    
    /**
     * A method that handles the delivery of the package to the next person in the transfer chain.
     * The super keyword refers to superclass (parent) objects.
     * @param: Package - Package class
     * @see: Package , Status
     */

    @Override
    public void deliverPackage(Package p) {
        p.setStatus(Status.DELIVERED9);
        p.addTracking(null,Status.DELIVERED9);//todo use Status.DELIVERED
        System.out.println("NonStandartTruck"+this.getTruckID()+"has delivered package "+ArratList[packNum].getPackageID()+" to the destination");
        super.deliverPackage(p);//remove pack from the truck
        this.setAvailable(true);
        super.deliverPackage(p);
    }
    
    /**
     * A method that performs a work unit
     * The super keyword refers to superclass (parent) objects.
     * @see: Package , Status
     */

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
    
    /**
     * boolean function checking Non Standard Package
     * @param: p - NonStandardPackage
     * @see: NonStandardPackage
     * @return: return true or false in accordance p
     */

    public boolean possibleLoad(NonStandardPackage p){
        return (p.getHeight()<=this.getHeight()&&p.getLength()<=this.getLength()&&p.getWidth()<=getWidth());

    }

    /**
     * funcation that return string
     * The super keyword refers to superclass (parent) objects.
     * @return: width, length, height - package dimensions
     */
    @Override
    public String toString() {
        return "NonStandardTruck" +
                super.toString()+
                ", length=" + length +
                "width=" + width +
                ", height=" + height +
                ']';
    }
    
    /**
     * funcation get width
     * @return: width
     */

    public int getWidth() {
        return width;
    }
    
    /**
     * funcation set width
     * @param width = width
     */

    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * funcation get length
     * @return: length
     */

    public int getLength() {
        return length;
    }
    
    /**
     * funcation set length
     * @param length = length
     */

    public void setLength(int length) {
        this.length = length;
    }
    
    /**
     * funcation get length
     * @return: height
     */

    public int getHeight() {
        return height;
    }
    
    /**
     * funcation set height
     * @param height = height
     */

    public void setHeight(int height) {
        this.height = height;
    }
}

