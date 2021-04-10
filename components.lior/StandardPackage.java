package components;

/**
 * Represents Standard Package
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Package
 */

public class StandardPackage extends Package{
    //Fields2.3.1
    public double weight;
    //Ctor2.3.2
    /**
     * Ctor
     */
    public StandardPackage(){}
    
    /**
     * Ctor Which receives as priority arguments, sender and recipient addresses, package weight
     * The super keyword refers to superclass (parent) objects.
     * @param:
     * 			priority - priority
     * 			senderAddress - sender Address
     * 			destinationAdress - destination Adress
     * 			weight -  package weight
     */
    public StandardPackage (Priority priority, Address senderAddress, Address destinationAdress,double weight){
        super(priority,senderAddress,destinationAdress);
        this.weight=weight;

    }
    //methos2.3.3

    @Override
    /**
     * funcation that return string
     * The super keyword refers to superclass (parent) objects.
     * @return: weight
     */
    public String toString() {
        return super.toString()+
                ","+
                "weight=" + weight +
                ']';
    }

    /**
     * get function for weight
     * @return: weight
     */
    
    public double getWeight() {
        return weight;
    }

    /**
     * set function for weight
     * Math.abs(weight) returns the absolute value of a given argument.
     * @param: weight = weight
     */
    
    public void setWeight(double weight) {
        this.weight = Math.abs(weight);
    }
}
