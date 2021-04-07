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

//       Address sender=new Address(2,123456);
//     Address dester=new Address(3,98765);
//
//       Branch br=new Branch();
//       //Truck v1=new StandardTruck();
//       Package p=new SmallPackage(Priority.HIGHT,sender,dester,true);
//        Package p2=new SmallPackage(Priority.LOW,sender,dester,true);
//        Package p3=new SmallPackage(Priority.STANDARD,sender,dester,true);
//        Package p4=new SmallPackage(Priority.STANDARD,sender,dester,true);




        //v1.setTimeLeft(3);
        //v1.setAvailable(false);
//        br.collectPackage(p);
//        br.collectPackage(p2);
//        br.collectPackage(p3);
//        br.collectPackage(p4);

//        for (int l=0;l<8;l++){
//            br.work();
//
//        }

        MainOffice game=new MainOffice(5, 4);
        game.play(60);

      //  System.out.println(br);




    }

}
