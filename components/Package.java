package components;

//abstauct class!!!

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
    public Package(){};

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
    public void addTracking(Node node,Status status){
        int time=MainOffice.getClock();
        if(hopTrcking<ArrayList.length)
            if(ArrayList[hopTrcking]!=null) {
                ArrayList[hopTrcking++] = new Tracking(time, node, status);
            }
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
        public boolean equals(Object other) {
        if(!(other instanceof Package))
            return false;
        Package p=(Package) other;
        return this.getPackageID()==((Package) other).getPackageID();
    }

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
