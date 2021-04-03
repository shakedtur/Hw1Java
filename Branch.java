package components;

public class Branch implements Node {
    //Fields 2.8.1
    private static int numBranch=0;
    public int branchId;
    public /*static*/ String branchName;
    public Truck[] listTrucks;
    public Package[] listPackages;

    //Ctor 2.8.2
    public Branch(){
        branchId=numBranch++;
        branchName=("branch"+branchId);
        listTrucks=null;
        listPackages=null;
    }
    public Branch(String branchName){
        branchId=numBranch++;
        this.branchName=(branchName);
        listTrucks=null;
        listPackages=null;
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
        //TODO
    }

    @Override
    public void deliverPackage(Package p) {
        //TODO
    }

    @Override
    public void work() {
        //todo check bugs
        for(int i=0;i<listPackages.length;i++) {
            if (listPackages[i].getStatus() == Status.CREATION) {
                int j = 0;
                while (listTrucks[j].available == false) {
                    j++;
                }
                if (j == listTrucks.length)
                    System.out.println("there is no avilavbe trucks");
                listTrucks[j].collectPackage(listPackages[i]);
                this.deliverPackage(listPackages[i]);//מסירה מהסניף

                listPackages[i].setStatus(Status.COLLECTION);
                listPackages[i].addTracking(this,Status.COLLECTION);
                int calctime = (listPackages[i].getSenderAddress().getStreet() / 10) + 1;
                listTrucks[j].setTimeLeft(calctime);
                listTrucks[j].setAvailable(false);


            }
        }
        for(int n=0;n<listPackages.length;n++){
            if(listPackages[n].getStatus()==Status.DELIVERY){
                int m=0;
                while (listTrucks[m].available == false) {
                    m++;
                }
                if (m == listTrucks.length)
                    System.out.println("there is no avilavbe trucks");

                listTrucks[m].collectPackage(listPackages[n]);
                this.deliverPackage(listPackages[m]);
                // change status package
                listPackages[n].setStatus(Status.DISTRIBUTION);
                listPackages[n].addTracking(this,Status.DISTRIBUTION);

                int cltime=(listPackages[n].getDestinationAddress().getStreet()/10)+1;
                listTrucks[m].setTimeLeft(cltime);
                listTrucks[m].setAvailable(false);
            }

        }


    }
    //add standart truck
    public void addTrucks(int number){
        this.listTrucks=new Truck[number];
        for (int i=0;i<listTrucks.length;i++){
            listTrucks[i]=new StandardTruck();
        }
    }


    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

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
