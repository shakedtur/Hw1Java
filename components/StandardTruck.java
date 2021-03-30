package components;

public class StandardTruck extends Truck implements Node {
    //Fields 2.7.1
    public int maxWeight;
    public Branch destination;
    //Ctor 2.7.2
    public StandardTruck(){
        super();
    }

    public StandardTruck(String licensePlate,String truckModel,int maxWeight){
        super(licensePlate,truckModel);
        setMaxWeight(maxWeight);
    }

    //methods 2.7.3


    @Override
    public void collectPackage(Package p) {
        //TODO
    }

    @Override
    public void deliverPackage(Package p) {
//TODO
    }

    @Override
    public void work() {
//TODO
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = Math.abs(maxWeight);
    }

    public Branch getDestination() {
        return destination;
    }

    public void setDestination(Branch destination) {
        this.destination = destination;
    }


}
