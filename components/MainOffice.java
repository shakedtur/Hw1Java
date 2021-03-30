package components;

public class MainOffice {
    //fields 2.9.4
    public int clock;
    public Hub hub;
    public Package[] ArrayList;
    //Ctor2.9.5


    public MainOffice(int branches,int truckForBranch) {
        this.hub=new Hub();
        this.hub.addHubTrucks(truckForBranch+1);


    }
}
