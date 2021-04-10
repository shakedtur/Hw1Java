package components;

import java.util.Random;

/**
 * An object of this department manages the entire system, operates a clock, the branches and vehicles
 * creates the packages (simulates customers) and transfers them to the appropriate branches.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 */

public class MainOffice {
    //fields 2.9.4
    public static int clock;
    public static Hub hub;
    public Package[] ArrayList;
    private int amountPack;
    //Ctor2.9.5

    /**
     * Ctor Which receives the number of branches that will be in the game and the number of vehicles per branch
     * Ctor creates a sorting center (Hub) and adds standard trucks in the quantity in the trucksForBranch parameter
     * Ctor Adds to the sorting center one non-standard truck.
     * Ctor creates branches (Branch) in the amount that appears in the branches parameter and to each such branch adds Van trucks in an amount that matches the trucksForBranch parameter.
     * @param :
     * 			branches - Creates branches in the quantity that appears in the parameter
     * 			truckForBranch - add Standard trucks in the quantity in the parameter
     *  		hub - creates a sorting center
     *  @see : Trucks, Branchs, Package
     */

    public MainOffice(int branches,int truckForBranch) {
        this.hub=new Hub();
        this.hub.addHubTrucks(truckForBranch+1);
        this.hub.addHubBranchs(branches,truckForBranch);
        this.amountPack=0;
//        for(int i=0;i<branches;i++){
//            hub.ArrayList[i]=new Branch();
//        }
        this.ArrayList=new Package[(60/5+1)];
    }

    //methods2.9.6
    
    /**
     * A function takes as an argument the number of beats that the system will perform and activates the beat (tick) this number of times.
     * @param :
     * 			playTime -  the number of beats that the system will perform and activates the beat
     */

    public void play(int playTime){
        this.ArrayList=new Package[(playTime/5+1)];
        for(int i=0;i<playTime;i++){
            if(i==0)
                System.out.println("========================== START ==========================");
            tick();
        }
        System.out.println("========================== STOP ==========================");
        this.printReport();
    }
    
    /**
     * Prints a tracking report for all packages that exist in the system
     * For each package prints the entire contents of the collection collection tracking
     * @see: Package , StandardPackage
     */
    
    public void printReport(){
        //TODO
        for(Package pack: this.ArrayList){
            if(pack!=null) {
                if(pack instanceof SmallPackage) {
                    System.out.println("Tracking Small" + pack.toString());
                    pack.printTracking();
                }
                else if(pack instanceof StandardPackage) {
                    System.out.println("Tracking Standard" + pack.toString());
                    pack.printTracking();
                }
                if(pack instanceof NonStandardPackage) {
                    System.out.println("Tracking NonStandard" + pack.toString());
                    pack.printTracking();
                }
            }
        }
    }
    
    /**
     * The clock is printed and promoted at one.
     * All branches, sorting center and vehicles perform one work unit.
     * Every 5 beats a random new package is created.
     * After the last beat, a notice of termination is printed ("STOP") and then a transfer history report is printed for all packages created during the run
     * For each package prints the entire contents of the collection collection tracking
     * @see: Package , StandardPackage
     */

    public void tick(){
        System.out.println(clockString());
//        for (int i=0;i<hub.ArrayList.length;i++){
//
//            //כל הסניפים תמרכ\ מיון ורכבים מבצעים יחידת עבודת אחת work
//        }
        hub.work();//todo need to change sec create new pack
        if(getClock()==0 ||(this.clock%5==0)){
            addPackage();
        }
        this.clock++;
        
        /**
         * Activated every 5 beats.
         * lottery package type, priority, sender and recipient addresses.
         * For a small package the value of acknowledge is drawn.
         * For a standard package the weight is drawn - an actual number between 1 and 10.
         * For a non-standard package of grills up to an integer up to 400.
         * Small or standard packages are delivered to a local branch.
         * Non-standard packages are transferred to the sorting center.
         * @see: Address , StandardPackage , NonStandardPackage
         */

    }
    public void addPackage(){
        Random rand=new Random();
        Priority[] allpri=Priority.values();
        int packtype=rand.nextInt(3);
        int packprri= rand.nextInt(3);
        int zipnum= rand.nextInt(hub.ArrayList.length-1);
        int streetnum= rand.nextInt(1000000);

        Address send=new Address(zipnum,streetnum);

        int zipnum2= rand.nextInt(hub.ArrayList.length);
        int streetnum2= rand.nextInt(1000000);

        Address dest=new Address(zipnum2,streetnum2);
        boolean ack=false;
        int TorF=rand.nextInt(2);//random true or false
        if (TorF==1)
            ack=true;
        //switchcase
        switch (packtype){
            // להשלים העברת חבילות לסניפים (שורות אחרונת בדף הוראות)
            case 0://samll package
                SmallPackage newpackage=new SmallPackage(allpri[packprri],send,dest,ack);
                System.out.println("Creating Small "+newpackage);
                this.ArrayList[amountPack++]=newpackage;
                hub.ArrayList[send.getZip()].collectPackage(newpackage);//belonging new package to loacl brunch by zip number

                break;
            case 1://standar package
                double wieghrand= rand.nextDouble()+rand.nextInt(10);
                StandardPackage newstand= new StandardPackage(allpri[packprri],send,dest,wieghrand);
                System.out.println("Creating Standard " +newstand);

                this.ArrayList[amountPack++]=newstand;
                hub.ArrayList[send.getZip()].collectPackage(newstand);//belonging new pack to local brunch by zip number

                break;
            case 2://non standat package
                int h=rand.nextInt(400);
                int w=rand.nextInt(500);
                int l=rand.nextInt(1000);
                NonStandardPackage newnonstand=new NonStandardPackage(allpri[packprri],send,dest,w,l,h);
                System.out.println("Creating NonStandard" +newnonstand);

                this.ArrayList[amountPack++]=newnonstand;
                hub.collectPackage(newnonstand);//העברה למכרז מיון חבילות לא סטדנרטיות
                break;
        }

    }

//    public int findBrunchnum(int sendzip){
//        for (int i=0;i<hub.ArrayList.length;i++){
//            if(hub.ArrayList[i].getBranchId()==sendzip){
//                return i;
//            }
//        }
//        return -1;
//    }
    
    /**
     * Prints the value of the watch in MM: SS format
     * @return: string in format MM: SS
     */

    public String clockString(){
//        int minuts=this.clock/60;
//        int sec=this.clock%60;
//        return (minuts+":"+sec);
       return String.format("%02d:%02d",clock/60,clock%60);
    }
    
    /**
     * get Clock
     * @return: clock
     */

    public static int getClock() {
        return clock;
    }
    
    /**
     * set Clock
     * @param : after geting clock the funcation set the clock in MainOffice 
     */

    public static void setClock(int clock) {
        MainOffice.clock = clock;
    }
    
    /**
     * Hub funcation
     * @return: hub
     * @see: hub
     */

    public Hub getHub() {
        return hub;
    }
    
    /**
     * Set Hub funcation
     * @param: set Hub
     * @see: Hub
     */

    public void setHub(Hub hub) {
        this.hub = hub;
    }
}
