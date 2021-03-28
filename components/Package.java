package components;

//abstauct class!!!

public abstract class Package {
    //Fields
    //2.1.1
    public int packageID;
    public Priority priority;
    public Status.stat status;
    public Address senderAddress;
    public Address destinationAddress;

    private static int counterpackage=999;

    //Ctor
    //2.1.2
    public Package(Priority priority,Address senderAdd,Address destinationAdd){
        this.packageID=++counterpackage;
        this.priority=priority;
        this.status=Status.stat.CREATION;
        this.senderAddress=senderAdd;
        this.destinationAddress=destinationAdd;
    }
    //Methods 2.1.3
    public void addTracking(Node node,Status status){
        //todo

    }
    public void printTracking(){
    //todo
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
    public Status.stat getStatus() {
        return status;
    }
    public void setStatus(Status.stat status) {
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
