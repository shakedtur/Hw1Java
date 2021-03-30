package components;

public class Hub extends Branch implements Node{
    //fields 2.9.1
    public Branch[] ArrayList;

    //Ctor2.9.2
    public Hub(){
        super("HUB");
    };
    //methods 2.9.3


    public void addHubTrucks(int number) {
        this.listTrucks=new Truck[number];
        //TODO Non standr truck
        //listTrucks[0]=new NonStandardTruck();
        for (int i=1;i<listTrucks.length;i++){
            listTrucks[i]=new StandardTruck();
        }
    }

    @Override
    public void collectPackage(Package p) {
        //super.collectPackage(p);
    }

    @Override
    public void deliverPackage(Package p) {
       // super.deliverPackage(p);
    }

    @Override
    public void work() {
       // super.work();
    }


}
