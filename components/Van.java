package components;

public class Van extends Truck implements Node{
    //Ctor2.6.2
    public Van(){}
    public Van(String licensePlate,String truckModel){
        super(licensePlate,truckModel);
    }
    //methods 2.6.3

    @Override
    public void collectPackage(Package p) {

    }

    @Override
    public void deliverPackage(Package p) {

    }

    @Override
    //need to undersend
    public void work() {
        if(available!=true){
            timeLeft--;
            if (timeLeft==0){
                //TODO
            }

        }
    }
}
