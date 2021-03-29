package components;

public class StandardPackage extends Package{
    //Fields2.3.1
    public double weight;
    //Ctor2.3.2
    public StandardPackage(){}
    public StandardPackage (Priority priority, Address senderAddress, Address destinationAdress,double weight){
        super(priority,senderAddress,destinationAdress);
        this.weight=weight;
    }
    //methos2.3.3
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = Math.abs(weight);
    }
}
