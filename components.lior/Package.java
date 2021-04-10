package components;

//abstauct class!!!

/**
 * A abstauct type represents packages.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 */

public /*abstract*/ class Package {
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
    
    /**
     * Ctor
     */
    
    public Package(){};
    
    /**
     * Ctor gets:  addresses the sender and receives a package.
     * The super keyword refers to superclass (parent) objects.
     * @param:	
     * 			licensePlate - plate number
     * 			truckModel - vehicle model
     * 			width, length, height - cargo dimensions
     */

    public Package(Priority priority,Address senderAdd,Address destinationAdd){
        this.packageID=++counterpackage;
        this.priority=priority;
        this.status=Status.CREATION1;//may cause problem
        this.senderAddress=senderAdd;
        this.destinationAddress=destinationAdd;
        this.ArrayList=new Tracking[9];
        this.ArrayList[0]=new Tracking();
//        for(int i=0;i<ArrayList.length;i++){
//            ArrayList[i]=new Tracking();
//        }
        this.hopTrcking=0;
    }
    //Methods 2.1.3
    //i add time val
    //need to check if work corrently
    
    /**
     * Receives an object of type Node (vehicle or branch) and a Status object
     * creates and adds an object of a Tracking class to a tracking collection in the class.
     * @param:	
     * 			Status - Status - Status object
     * 			Node - node - (vehicle or branch) 
     */
    
    public void addTracking(Node node,Status status){
        int time=MainOffice.getClock();
        if(hopTrcking<ArrayList.length)
            if(ArrayList[hopTrcking+1]==null) {//next value is null?
                ArrayList[hopTrcking+1] = new Tracking(time, node, status);
                hopTrcking++;
            }
        else{//creating bigger array
            Tracking[] temp=new Tracking[(ArrayList.length+5)];
            for (int i=0;i<ArrayList.length;i++){
                temp[i]=ArrayList[i];
            }

            temp[ArrayList.length]=new Tracking(time,node,status);
            this.ArrayList=temp;
        }

    }
    
    /**
     * Prints the records stored in the tracking collection, each object in a separate row
     */
    
    public void printTracking(){
    //todo
        for (int i=0;i<ArrayList.length;i++){
            if(ArrayList[i]!=null)
                System.out.println(ArrayList[i].toString());
        }


    }
    
    /**
     * Check Weight pack
     * @return:
     * 			(int)((StandardPackage)this).getWeight() - if its StandardPackage
     * 			return 0 - if its nither
     * 			return 1 - if its SmallPackage
     */

    public int Weightpack(){
        if(this instanceof StandardPackage){
            return (int)((StandardPackage)this).getWeight();
        }
        else if(this instanceof SmallPackage)
            return 1;
        return 0;
    }

    @Override
//    public boolean equals(Object other) {
//        if(!(other instanceof Package))
//            return false;
//        Package p=(Package) other;
//        return this.getStatus()==p.getStatus();
//    }
    
    /**
     * Check if  equals
     * @return:
     * 			false - if its not Package
     * 			true - if its Package
     */
    
        public boolean equals(Object other) {
        if(!(other instanceof Package))
            return false;
        Package p=(Package) other;
        return this.getPackageID()==((Package) other).getPackageID();
    }
    
    /**
     * function that return string
     * @return: Package, packageID, priority, status, senderAddress, destinationAddress
     */

    @Override
    public String toString() {
        return "Package[" +
                "packageID=" + packageID +
                ",priority=" + priority +
                ",status=" + status +
                ",senderAddress=" + senderAddress +
                ",destinationAddress=" + destinationAddress;
                //", ArrayList=" + Arrays.toString(ArrayList) +
                //']';
    }

    //setters getterss

    /**
     * funcation get packageID
     * @return: packageID
     */
    
    public int getPackageID() {
        return packageID;
    }
    
    /**
     * funcation set packageID
     * @param: packageID=packageID
     */
    
    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }
    
    /**
     * funcation get priority
     * @return: priority
     */
    
    public Priority getPriority() {
        return priority;
    }
    
    /**
     * funcation set priority
     * @param priority = priority
     */
    
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    /**
     * funcation get status
     * @return status
     */
    
    public Status getStatus() {
        return status;
    }
    
    /**
     * funcation set status
     * @param status = status
     */
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     * funcation get senderAddress
     * @return senderAddress
     */
    
    public Address getSenderAddress() {
        return senderAddress;
    }
    
    /**
     * funcation set senderAddress
     * @param senderAddress = senderAddress
     */
    
    public void setSenderAddress(Address senderAddress) {
        this.senderAddress = senderAddress;
    }
    
    /**
     * funcation get destinationAddress
     * @return destinationAddress
     */
    
    public Address getDestinationAddress() {
        return destinationAddress;
    }
    
    /**
     * funcation set destinationAddress
     * @param destinationAddress = destinationAddress
     */
    
    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

}
