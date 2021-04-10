package components;

/**
 * Describes a local branch. 
 * Maintains a list of packages stored at the branch or intended for collection from the sender's address to this branch
 * and a list of vehicles that collect the packages from the sending customers and deliver the packages to the receiving customers.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Node
 */

public class Branch implements Node {
    //Fields 2.8.1
    private static int numBranch=-1;
    public final int branchId;
    public /*static*/ String branchName;
    public Truck[] listTrucks;
    public Package[] listPackages;
    private int currenTruck=0;
    private  int currentPack=0;

    //Ctor 2.8.2
    
    /**
     * Ctor - calculates the serial number of the branch and creates the name of the branch
     * the two remaining fields are initialized to blank collections.
     * @param: 
     *        branchId - id of the branch
     *        numBranch - Continuous number of branch
     *        branchName - name of the branch
     *        listTrucks - list of trucks in arrey
     *        listPackages - list of packages which are in the branch and packages to be collected are shipped by this branch in arrey.
     * @see: Truck, Package
     */

    public Branch(){
        branchId=numBranch++;
        branchName=("branch"+branchId);
        listTrucks=new Truck[0];
        listPackages=new Package[0];
//        for (int i=0;i<listPackages.length;i++){
//            listPackages[i]=null;
//        }
        System.out.println();
        System.out.println("Creating "+this.toString());
    }
    
    /**
     * Ctor gets a branch name, calculates the serial number of the branch, the two remaining fields are initialized to empty collections.
     * @param: 
     *        branchId - id of the branch
     *        numBranch - Continuous number of branch
     *        branchName - name of the branch
     *        listTrucks - list of trucks in arrey
     *        listPackages - list of packages which are in the branch and packages to be collected are shipped by this branch in arrey.
     *        @see: Truck, Package
     */
    
    public Branch(String branchName){
        branchId=numBranch++;
        this.branchName=(branchName);
        listTrucks=new Truck[0];
        listPackages=new Package[0];
//        for (int i=0;i<listPackages.length;i++){
//            listPackages[i]=new Package();//לשנות לnull
//        }
        System.out.println("");
        System.out.println("Creating "+this.toString());

    }
    //methods 2.8.3
    
    /**
     * add van to the list
     * @param: 
     *        listTrucks - list of trucks in arrey
     * @see: Truck, Van
     */
    
    public void addVans(int numV){
        this.listTrucks=new Truck[numV];
        for(int i=0;i<listTrucks.length;i++){
            listTrucks[i]=new Van();
        }
    }
    
    /**
     * A method that handles the collection / receipt of a package by the implementing department.
     * @param: 
     *        listTrucks - list of trucks in arrey
     *        listPackages - list of packages which are in the branch and packages to be collected are shipped by this branch in arrey.
     * @see: Package
     */

    @Override
    public void collectPackage(Package p) {
        for(int m=0;m<=listPackages.length;m++) {
            if(m==listPackages.length) {//in case the memory of package arr done
                Package[] temp = new Package[listPackages.length + 5];
                for (int i = 0; i < temp.length; i++) {
                    if (i < listPackages.length)
                        temp[i] = listPackages[i];
//                    else if (i == listPackages.length)
//                        temp[i] = p;
                    else
                        temp[i] = null;
                }
                listPackages = temp;
            }
            if (listPackages[m]==null) {
                listPackages[m]=p;
                break;
            }

        }
       // System.out.println("Pass");
        System.out.println("Van "+ (listTrucks[currenTruck].getTruckID())+ " is collecting package"+p.getPackageID()+", time to arrive: "+this.listTrucks[currentPack].getTimeLeft());
    }
    
    /**
     * A method that handles the delivery of the package to the next person in the transfer chain.
     * @param: 
     *        listPackages - list of packages which are in the branch and packages to be collected are shipped by this branch in arrey.
     * @see: Package
     */

    @Override
    public void deliverPackage(Package p) {

        for(int i=0;listPackages[i]!=null && i<listPackages.length;i++){
            if(listPackages[i].equals(p)) {
                //listPackages[i].addTracking(this,Status.DISTRIBUTION);//todo useDISTRIBUTION8
                listPackages[i] = null;

            }
        }
    }
    
    /**
     * A work unit performed by a branch every time the system clock beats.
     * For each package that is in the branch, if it is in the status of waiting for collection from a customer, an attempt is made to collect
     * if there is a vehicle available, he goes out to collect the package. Travel time is calculated as follows: the street number of the sender is divided by ten, for the remaining remainder one is added.
     * The value obtained is updated in the vehicle in the timeLeft field and the condition of the vehicle changes to "not available".
     * @param: 
     *        listPackages - list of packages which are in the branch and packages to be collected are shipped by this branch in arrey.
     *        listTrucks - list of trucks in arrey
     *  @see:  status, Branch, Package, van, Truck
     */

    @Override
    public void work() {
        //todo check bugs
        for (int k=0;k<listTrucks.length;k++){//passing all trucks
            listTrucks[k].work();
        }

        for(int i=0;i<listPackages.length && listPackages[i]!=null;i++) {
            if (listPackages[i].getStatus() == Status.CREATION1) {
                int j = 0;
                while (j<listTrucks.length && listTrucks[j].available == false) {//search non busy van
                    j++;
                }
                if (j == listTrucks.length) {
                    //TODO erase printing
                    System.out.println("there is no avilavbe trucks" + getBranchName());
                    break;
                }
                currenTruck=j;
                listTrucks[j].collectPackage(listPackages[i]);
                System.out.println("Van "+ (listTrucks[currenTruck].getTruckID())+ "is collecting package"+listPackages[i].getPackageID()+", time to arrive: "+this.listTrucks[currentPack].getTimeLeft());
                listPackages[i].setStatus(Status.COLLECTION2);//TODO use COLLECTION2
                listPackages[i].addTracking(this,Status.BRANCH_STORAGE3);//TODO use BRANCH_STORAGE3
                int calctime = (listPackages[i].getSenderAddress().getStreet() / 10)%10 + 1;
                listTrucks[j].setTimeLeft(calctime);
                listTrucks[j].setAvailable(false);

                this.deliverPackage(listPackages[i]);//מסירה מהסניף




            }
            //change to minimize loops
            else if(listPackages[i].getStatus()==Status.DELIVERY7){
                int m=0;
                while (listTrucks[m].available == false) {
                    m++;
                }
                if (m == listTrucks.length)
                    System.out.println("there is no avilavbe trucks");

                listTrucks[m].collectPackage(listPackages[i]);
                this.deliverPackage(listPackages[m]);
                // change status package
                listPackages[i].setStatus(Status.DISTRIBUTION8);//Todo use Status.DISTRIBUTION8
                listPackages[i].addTracking(this,Status.DISTRIBUTION8);

                int cltime=(listPackages[i].getDestinationAddress().getStreet()/10)+1;
                listTrucks[m].setTimeLeft(cltime);
                listTrucks[m].setAvailable(false);
            }


        }
//        for(int n=0;listPackages[n]!=null &&n<listPackages.length;n++){
//            if(listPackages[n].getStatus()==Status.DELIVERY){
//                int m=0;
//                while (listTrucks[m].available == false) {
//                    m++;
//                }
//                if (m == listTrucks.length)
//                    System.out.println("there is no avilavbe trucks");
//
//                listTrucks[m].collectPackage(listPackages[n]);
//                this.deliverPackage(listPackages[m]);
//                // change status package
//                listPackages[n].setStatus(Status.DISTRIBUTION);
//                listPackages[n].addTracking(this,Status.DISTRIBUTION);
//
//                int cltime=(listPackages[n].getDestinationAddress().getStreet()/10)+1;
//                listTrucks[m].setTimeLeft(cltime);
//                listTrucks[m].setAvailable(false);
//            }
//
//        }


    }
    
    /**
     * add standart truck
     * @param: 
     *        listTrucks - list of trucks in arrey
     */

    //add standart truck
    public void addTrucks(int number){
        this.listTrucks=new Truck[number];
        for (int i=0;i<listTrucks.length;i++){
            listTrucks[i]=new StandardTruck();
        }
    }
    
    /**
     * return: String of Branch, branchId, branchName, packages, trucks
     */

    @Override
    public String toString() {
        return "Branch " +
                 + branchId +
                ", branchName:" + branchName +
                ", packages:" + listPackages.length +
                ", trucks:" + listTrucks.length ;
    }
    
    /**
     * @return : branchId
     */

    public int getBranchId() {
        return branchId;
    }

//    public void setBranchId(int branchId) {
//        this.branchId = branchId;
//    }
    
    /**
     * @return : String branchName
     */

    public /*static*/ String getBranchName() {
        return branchName;
    }
    
    /**
     * @param : branchName
     */

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    /**
     * return variable listTrucks in index i 
     * @return : listTrucks[i]
     */
    
    //edited
    public Truck getTruck(int i) {
        return listTrucks[i];
    }
    
    /**
     * @param : listTrucks
     */

    public void setListTrucks(Truck[] listTrucks) {
        this.listTrucks = listTrucks;
    }
    
    /**
     * @return : listTrucks
     */

    public Truck[] getListTrucks() {
        return listTrucks;
    }
    
    /**
     * @return : listPackages
     */

    public Package[] getListPackages() {
        return listPackages;
    }
    
    /**
     * return variable listPackages in index i 
     * @return : listPackages[i]
     */

    //edited
    public Package getPack(int i) {
        return listPackages[i];
    }
    
    /**
     * @param : listPackages
     */

    public void setListPackages(Package[] listPackages) {
        this.listPackages = listPackages;
    }
}
