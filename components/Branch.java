package components;

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
    public void addVans(int numV){
        this.listTrucks=new Truck[numV];
        for(int i=0;i<listTrucks.length;i++){
            listTrucks[i]=new Van();
        }
    }

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

    @Override
    public void deliverPackage(Package p) {

        for(int i=0;listPackages[i]!=null && i<listPackages.length;i++){
            if(listPackages[i].equals(p)) {
                //listPackages[i].addTracking(this,Status.DISTRIBUTION);//todo useDISTRIBUTION8
                listPackages[i] = null;

            }
        }
    }

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


    //add standart truck
    public void addTrucks(int number){
        this.listTrucks=new Truck[number];
        for (int i=0;i<listTrucks.length;i++){
            listTrucks[i]=new StandardTruck();
        }
    }

    @Override
    public String toString() {
        return "Branch " +
                 + branchId +
                ", branchName:" + branchName +
                ", packages:" + listPackages.length +
                ", trucks:" + listTrucks.length ;
    }

    public int getBranchId() {
        return branchId;
    }

//    public void setBranchId(int branchId) {
//        this.branchId = branchId;
//    }

    public /*static*/ String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    //edited
    public Truck getTruck(int i) {
        return listTrucks[i];
    }

    public void setListTrucks(Truck[] listTrucks) {
        this.listTrucks = listTrucks;
    }

    public Truck[] getListTrucks() {
        return listTrucks;
    }

    public Package[] getListPackages() {
        return listPackages;
    }

    //edited
    public Package getPack(int i) {
        return listPackages[i];
    }

    public void setListPackages(Package[] listPackages) {
        this.listPackages = listPackages;
    }
}
