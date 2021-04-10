package components;

import java.util.Random;

/**
 * Main branch for sorting packages. Packages arrive to it from branches and are sent to other branches as required.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Node
 * @see     Branch
 */

public class Hub extends Branch implements Node{
    //fields 2.9.1
//    private static int numBranch=0;
//    public int branchId;
//    public static String branchName;
//    public Truck[] listTrucks;
//    public Package[] listPackages;
    public Branch[] ArrayList;
    private int brunchqueue=0;
    
    /**
     * Ctor of Hub
     * The super keyword refers to superclass (parent) objects.
     */

    //Ctor2.9.2
    public Hub(){
        super("HUB");
        //ArrayList=new Branch[0];

    };
    
    /**
     * add Hub Trucks
     *      @param: 
     *        listTrucks - list of trucks in arrey
     * @see     Branch, Truck, StandardTruck
     */

    //methods 2.9.3

    public void addHubTrucks(int number) {
        this.listTrucks=new Truck[number];
        for (int i=0;i<listTrucks.length-1;i++){
            listTrucks[i]=new StandardTruck();
        }
        listTrucks[number-1]=new NonStandardTruck();
    }
    
    /**
     * add Hub Branchs
     *      @param: 
     *        Branchs - Class Branchs
     * @see     Branch
     */

    public void addHubBranchs(int numB,int numT){
        this.ArrayList=new Branch[numB];
        for (int i=0;i<ArrayList.length;i++){
            ArrayList[i]=new Branch();
            ArrayList[i].addVans(numT);
        }
    }
    
    /**
     * add more Branchs
     *      @param: 
     *        Branchs - Class Branchs
     * @see     Branch
     * @return: 0
     */
    
    public int nextBrunch() {
        int next  =(brunchqueue)%ArrayList.length;
        brunchqueue++;
        if (next < ArrayList.length)
            return next;
        else{
            System.out.println("Error nextbruch");
        return 0;
        }
    }
    
    /**
     * funcation for collect Package
     * The super keyword refers to superclass (parent) objects.
     */
    
    @Override
    public void collectPackage(Package p) {
        super.collectPackage(p);
    }
    
    /**
     * funcation for deliver Package
     * The super keyword refers to superclass (parent) objects.
     */

    @Override
    public void deliverPackage(Package p) {
       super.deliverPackage(p);
    }
    
    /**
     * funcation for deliver Package
     * @return: True or Flase
     */

    public boolean matchbrunch(int b,Package p){
        return b==p.getDestinationAddress().getZip();
    }
    
    /**
     * funcation that scanning all packages do delivery and remove from HUB
     *       @param:
     *       			StandardTruck - represents packages with varying weights over one kilogram.
     * 		@return:
     * 					listPackages[i], null
     * @see: Package
     */

    public void Loading(StandardTruck Struck){
        int totaLoadingW=0;
        for (int i=0;i<listPackages.length;i++){//scanning all packages do delivery
            Package p=listPackages[i];

            if(p!=null && p.getDestinationAddress().getZip()==Struck.getDestination().getBranchId()){
                if(Struck.loadingPossible(totaLoadingW,p)){
                    totaLoadingW+=p.Weightpack();
                    Struck.collectPackage(p);
                    p.setStatus(Status.HUB_TRANSPORT4);//todo use BRANCH_TRANSPORT6 or4444
                    p.addTracking(Struck,Status.HUB_TRANSPORT4);
                    this.deliverPackage(p);//remove from HUB
                }

            }
        }
        System.out.println("StandardTruck:"+Struck.getTruckID()+" 2000 loaded packages at HUB");
    }
    public Package exsisNonStandPack(){
        for(int i=0;i<listPackages.length;i++){
            if(listPackages[i]instanceof NonStandardPackage)
                return listPackages[i];
        }
        return null;
    }

    /**
     * A work unit that is performed in one clock.
     * For each standard truck of the sorting center, if the truck is available, it is sent to a local branch. For the purpose of the trip, the truck will load all the packages waiting to be transferred to the branch to which it travels.
     * As long as the weight of the packages is less than the maximum weight it can carry. The weight of the standard packages appears in each package, and the weight of a small package is one kilogram. The status of the packages and their history are updated accordingly
     * A message is printed stating that the truck is leaving for this branch, travel time is a lottery value between 1 and 10.
     * If the non-standard truck is available, it will be checked whether there is a non-standard package in the sorting center that is waiting to be collected and that its dimensions fit the truck. If so, the truck will be sent to pick up the package.
     * The collection of the non-standard package from a customer is carried out according to exactly the same rules as the other packages, only by a non-standard truck.
     * Packages left in the sorting center due to the weight restriction in the truck will be handled in the following rounds
     * Non-standard packages that do not fit into a non-standard truck due to size restrictions will remain in the sorting center until the end of the run.
     * 
     *       @see:	truck, StandardTruck
     * 		@return:
     * 					listPackages[i], null
     */

    @Override
    public void work(){
        for (int i=0;i<listTrucks.length;i++){//action all trucks
            listTrucks[i].work();
            if(listTrucks[i].isAvailable()){
                if(listTrucks[i] instanceof StandardTruck){
                    StandardTruck standTck= ((StandardTruck) listTrucks[i]);
                    Branch b=ArrayList[nextBrunch()];
                    standTck.setDestination(b);
                    Loading((StandardTruck)standTck);
                    Random r=new Random();
                    standTck.setTimeLeft(r.nextInt(10)+1);
                    standTck.setAvailable(false);
                    System.out.println("StandardTruck " +standTck.getTruckID()+ " is on it's way to "+ standTck.getDestination().getBranchName()+", time to arrive:"+standTck.getTimeLeft());
                }
                else if(listTrucks[i]instanceof NonStandardTruck){
                   NonStandardTruck Ntrck=((NonStandardTruck)listTrucks[i]);
                   if(exsisNonStandPack()instanceof NonStandardPackage){
                       NonStandardPackage Nonpack=(NonStandardPackage)this.exsisNonStandPack();
                       if (Ntrck.possibleLoad(Nonpack)){
                           Nonpack.setStatus(Status.COLLECTION2);
                           Nonpack.addTracking(Ntrck,Status.COLLECTION2);
                           Ntrck.collectPackage(Nonpack);
                           Random r=new Random();
                           Ntrck.setTimeLeft(r.nextInt(10)+1);
                           Ntrck.setAvailable(false);
                           this.deliverPackage(Nonpack);
                           //System.out.println("NonStandartTruck  "+Ntrck.getTruckID()+"has delivered package"+Nonpack.getPackageID()+" to the destination");

                       }
                   }
                }
            }
        }
        //TODO remove notes
        for(int b=0;b<ArrayList.length;b++){
            ArrayList[b].work();
        }



    }


//work
//    public void work() {
//       // super.work();
//
//        int brunckorder=0;
//        for (int i=0;i<listTrucks.length;i++){
//            listTrucks[i].work();
//            if(listTrucks[i]instanceof StandardTruck){
//                if(listTrucks[i].available) {
//                    ((StandardTruck) listTrucks[i]).setDestination(ArrayList[brunckorder % ArrayList.length]);
//                    brunckorder++;
//                    int totalW=0;
//                    int j=0;
//                    //הנסיעה המשאית תטען אליה את כל החבילות שממתינות להעברה לסניף שאליו היא נוסעת, כל עוד משקל החבילות יהיה קטן מהמשקל המירבי אותו היא יכולה לשאת.
//                    while (j<listPackages.length && totalW<=((StandardTruck) listTrucks[i]).getMaxWeight()){
//                        if(matchbrunch((brunckorder%ArrayList.length-1),listPackages[j])){
//                            if (listPackages[j]instanceof SmallPackage){
//                                totalW++;
//                                listTrucks[i].collectPackage(listPackages[j]);
//                                this.deliverPackage(listPackages[j]);
//                                j++;
//                            }
//                            if ((listPackages[j]instanceof StandardPackage)){
//                                totalW+=((StandardPackage) listPackages[j]).getWeight();
//                                listTrucks[i].collectPackage(listPackages[j]);
//                                this.deliverPackage(listPackages[j]);
//                                j++;
//                            }
//                            listPackages[j].setStatus(Status.BRANCH_TRANSPORT);
//                        }
////                        else {
////                            j++;
////                        }
//                    }
//                    //int drivetime=((int)Math.random()*11)+1;
//                    Random ra=new Random();
//                    int drivetime=ra.nextInt(10)+1;
//                    listTrucks[i].setTimeLeft(drivetime);//random drive time to truck 0-10
//                }
//            }
//        }
//    //TODO משאית לא סטדנטית פנויה
//
//
//    }
//
//

}
