package components;

import java.util.ArrayList;
import java.util.Random;

public class Customer {
    private static int clock=0;
    public MainOffice mainOffice;
    public static int sNumber=1;
    public int seiralNumber;
    public Address myAddress;
    public static int Maxpackage=5;
    private int timetonewpack;
    private ArrayList<Package> packagesOfCustumer =new ArrayList<Package>();


    public Customer(){
        mainOffice=MainOffice.getInstanceExsist();
        seiralNumber=sNumber;
        sNumber++;
        Random r=new Random(5);
        myAddress=new Address(r.nextInt(mainOffice.hub.getBranches().size()), r.nextInt(999999)+100000);
    }

    public void setTimetonewpack(){
        Random r = new Random();
        int min =2;
        int max=5;
        int result = r.nextInt(max-min)+min;
        timetonewpack=result;
    }

    public void addpacakegeByCustomer(){
//        Random r = new Random();
//        int min =2;
//        int max=5;
//        int result = r.nextInt(max-min)+min;

        if(timetonewpack==0){
            Package tempP=mainOffice.addPackage();
            packagesOfCustumer.add(tempP);
        }
//        while (Maxpackage > 0){
//            clock++;
//
//        }

//        if (clock++%5==0 && maxPackages>0) {
//			addPackage();
//			maxPackages--;
//		}
    }

    public static int getClock() {
        return clock;
    }


}
