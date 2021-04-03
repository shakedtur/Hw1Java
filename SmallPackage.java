package components;

public class SmallPackage extends Package {
    //fields2.2.1
    public boolean acknowledge;
    //Ctor
    public SmallPackage(){}
    public SmallPackage(Priority priority, Address senderAddress, Address destinationAdress, boolean acknowledge){
        super(priority,senderAddress,destinationAdress);
        this.acknowledge=acknowledge;
    }
    //2.2.3 methods
    public boolean getacknowledge(){return acknowledge;}
    public void setAcknowledge(boolean fitback){
        this.acknowledge=fitback;
    }
}
