package components;

import java.util.Arrays;

public class Van extends Truck implements Node{
    //Ctor2.6.2
    public int test;

    public Van(){
    }
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

    @Override
    public String toString() {
        return "Van{" +
                "truckID=" + truckID +
                ", licensePlate='" + licensePlate + '\'' +
                ", truckModel='" + truckModel + '\'' +
                ", available=" + available +
                ", timeLeft=" + timeLeft +
                ", ArratList=" + Arrays.toString(ArratList) +
                ", test=" + test +
                '}';
    }
}
