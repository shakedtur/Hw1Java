package components;

import java.util.Random;

public class Hub extends Branch implements Node{
    //fields 2.9.1
//    private static int numBranch=0;
//    public int branchId;
//    public static String branchName;
//    public Truck[] listTrucks;
//    public Package[] listPackages;
    public Branch[] ArrayList;
    private int brunchqueue=0;

    //Ctor2.9.2
    public Hub(){
        super("HUB");
        //ArrayList=new Branch[0];

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
    @Override
    public void collectPackage(Package p) {
        super.collectPackage(p);
    }

    @Override
    public void deliverPackage(Package p) {
       super.deliverPackage(p);
    }

    public boolean matchbrunch(int b,Package p){
        return b==p.getDestinationAddress().getZip();
    }

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
