package components;

//abstauct class!!!

import java.util.Arrays;

public abstract class Package {
    //Fields
    //2.1.1
    public int packageID;
    public Priority priority;
    public Status status;
    public Address senderAddress;
    public Address destinationAddress;
    public Tracking[] ArrayList;
    private static int counterpackage=999;
    private int hopTrcking;

    //Ctor
    //2.1.2
    public Package(){};

    public Package(Priority priority,Address senderAdd,Address destinationAdd){
        this.packageID=++counterpackage;
        this.priority=priority;
        this.status=Status.CREATION;//may cause problem
        this.senderAddress=senderAdd;
        this.destinationAddress=destinationAdd;
        this.ArrayList=new Tracking[5];
        this.hopTrcking=0;
    }
    //Methods 2.1.3
    //i add time val
    //need to check if work corrently
    public void addTracking(int time,Node node,Status status){
        //todo
        if(hopTrcking<ArrayList.length)
            ArrayList[hopTrcking++]=new Tracking(time,node,status);
        else{
            Tracking[] temp=new Tracking[(ArrayList.length+5)];
            for (int i=0;i<ArrayList.length;i++){
                temp[i]=ArrayList[i];
            }
            temp[ArrayList.length]=new Tracking(time,node,status);
            this.ArrayList=temp;
        }


    }
    public void printTracking(){
    //todo
        for (int i=0;i<ArrayList.length && ArrayList[i]!=null;i++){
            System.out.println(ArrayList[i].toString());
        }


    }

    @Override
    public String toString() {
        return "Package{" +
                "packageID=" + packageID +
                ", priority=" + priority +
                ", status=" + status +
                ", senderAddress=" + senderAddress +
                ", destinationAddress=" + destinationAddress +
                ", ArrayList=" + Arrays.toString(ArrayList) +
                ", hopTrcking=" + hopTrcking +
                '}';
    }

    //setters getterss


    public int getPackageID() {
        return packageID;
    }
    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Address getSenderAddress() {
        return senderAddress;
    }
    public void setSenderAddress(Address senderAddress) {
        this.senderAddress = senderAddress;
    }
    public Address getDestinationAddress() {
        return destinationAddress;
    }
    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

}
