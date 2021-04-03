package components;

import components.Node;
import components.Status;
import components.Tracking;

import java.util.Random;


public class HW1 {

    public static void main(String[] args){

//        System.out.println("hi");
//        Tracking t=new Tracking();
//        System.out.println(t.toString());
//        t.setTime(5);
//        t.setStatus(Status.HUB_STORAGE);
//        Address send= new Address();
//        send.setZip(3);
//        send.setStreet(123456);
//        Address target=new Address();
//        target.setZip(10);
//        target.setStreet(123);
//        Package test =new Package(Priority.LOW,send,target);
//        Package test2=new Package(Priority.HIGHT,send,target);
//
//        System.out.println(test);
//        test.printTracking();
//        System.out.println(test2);
        //test.printTracking();
//        Truck t1=new Truck();
//        Truck t2=new Truck();


        NonStandardTruck tr=new NonStandardTruck();
        Van v1=new Van("234-56-678","A5");

        System.out.println(v1);




    }

}
