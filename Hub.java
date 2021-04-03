package components;

public class Hub extends Branch implements Node{
    //fields 2.9.1
//    private static int numBranch=0;
//    public int branchId;
//    public static String branchName;
//    public Truck[] listTrucks;
//    public Package[] listPackages;
    public Branch[] ArrayList;


    //Ctor2.9.2
    public Hub(){
        super("HUB");
    };
    //methods 2.9.3

    public void addHubTrucks(int number) {
        this.listTrucks=new Truck[number];
        for (int i=0;i<listTrucks.length-1;i++){
            listTrucks[i]=new StandardTruck();
        }
        listTrucks[number-1]=new NonStandardTruck();
    }

    public void addHubBranchs(int numB,int numT){
        this.ArrayList=new Branch[numB];
        for (int i=0;i<ArrayList.length;i++){
            ArrayList[i]=new Branch();
            ArrayList[i].addVans(numT);
        }

    }
    @Override
    public void collectPackage(Package p) {
        //super.collectPackage(p);
    }

    @Override
    public void deliverPackage(Package p) {
       // super.deliverPackage(p);
    }

    public boolean matchbrunch(int b,Package p){
        return b==p.getDestinationAddress().getZip();
    }

    @Override
    public void work() {
       // super.work();
        int brunckorder=0;
        for (int i=0;i<listTrucks.length;i++){
            if(listTrucks[i]instanceof StandardTruck){
                if(listTrucks[i].available) {
                    ((StandardTruck) listTrucks[i]).setDestination(ArrayList[brunckorder % ArrayList.length]);
                    brunckorder++;
                    int totalW=0;
                    int j=0;
                    //הנסיעה המשאית תטען אליה את כל החבילות שממתינות להעברה לסניף שאליו היא נוסעת, כל עוד משקל החבילות יהיה קטן מהמשקל המירבי אותו היא יכולה לשאת.
                    while (j<listPackages.length && totalW<=((StandardTruck) listTrucks[i]).getMaxWeight()){
                        if(matchbrunch((brunckorder%ArrayList.length-1),listPackages[j])){
                            if (listPackages[j]instanceof SmallPackage){
                                totalW++;
                                listTrucks[i].collectPackage(listPackages[j]);
                                this.deliverPackage(listPackages[j]);
                                j++;
                            }
                            if ((listPackages[j]instanceof StandardPackage)){
                                totalW+=((StandardPackage) listPackages[j]).getWeight();
                                listTrucks[i].collectPackage(listPackages[j]);
                                this.deliverPackage(listPackages[j]);
                                j++;
                            }
                            listPackages[j].setStatus(Status.BRANCH_TRANSPORT);
                        }
                    }
                    int drivetime=((int)Math.random()*11)+1;
                    listTrucks[i].setTimeLeft(drivetime);//random drive time to truck 0-10
                }
            }
        }
    //TODO משאית לא סטדנטית פנויה


    }


}
