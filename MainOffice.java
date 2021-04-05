package components;

import java.util.Random;

public class MainOffice {
    //fields 2.9.4
    public static int clock;
    public Hub hub;
    public Package[] ArrayList;
    private int amountPack;
    //Ctor2.9.5


    public MainOffice(int branches,int truckForBranch) {
        this.hub=new Hub();
        this.hub.addHubTrucks(truckForBranch+1);
        this.hub.addHubBranchs(branches,truckForBranch);
        this.amountPack=0;
        this.ArrayList=new Package[(60/5+1)];
    }

    //methods2.9.6

    public void play(int playTime){
        this.ArrayList=new Package[(playTime/5+1)];
        for(int i=0;i<playTime;i++){
            tick();
        }
        System.out.println("==========STOP==============");
        this.printReport();
    }
    public void printReport(){
        //TODO
        System.out.println("TODO printReport");
        for(Package pack: this.ArrayList){
            if(pack!=null) {
                System.out.println("Tracking" + pack.toString());
                pack.printTracking();
            }
        }
    }

    public void tick(){
        System.out.println(clockString());
        this.clock++;
        for (int i=0;i<hub.ArrayList.length;i++){
            //TODO
            //כל הסניפים תמרכ\ מיון ורכבים מבצעים יחידת עבודת אחת work
        }
        if(this.clock%5==0){
            addPackage();
        }

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
                int wieghrand= rand.nextInt(10)+1;
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

    public String clockString(){
        int minuts=this.clock/60;
        int sec=this.clock%60;
        return (minuts+":"+sec);
    }

    public static int getClock() {
        return clock;
    }

    public static void setClock(int clock) {
        MainOffice.clock = clock;
    }

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }
}
