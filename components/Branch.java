package components;

public class Branch implements Node {
    //Fields 2.8.1
    private static int numBranch=0;
    public int branchId;
    public String branchName;
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
        branchName=(branchName);
        listTrucks=null;
        listPackages=null;
    }
    //methods 2.8.3


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
        //todo
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    //edited
    public Truck getListTrucks(int i) {
        return listTrucks[i];
    }

    public void setListTrucks(Truck[] listTrucks) {
        this.listTrucks = listTrucks;
    }
    //edited
    public Package getListPackages(int i) {
        return listPackages[i];
    }

    public void setListPackages(Package[] listPackages) {
        this.listPackages = listPackages;
    }
}
