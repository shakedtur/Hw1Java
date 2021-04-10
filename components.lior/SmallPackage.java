package components;

/**
 * Represents small packages
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Package
 */

public class SmallPackage extends Package {
    //fields2.2.1
    public boolean acknowledge;
    //Ctor
    /**
     * Ctor
     */
    public SmallPackage(){}
    
    /**
     * Ctor receives as arguments and priority addresses sends and receives the package, and if delivery confirmation as required.
     * The super keyword refers to superclass (parent) objects.
     * @param:	
     * 			priority - priority
     *  		senderAddress - addresses sends
     *   		destinationAdress - addresses receives
     *    		acknowledge - delivery confirmation as required
     */
    
    public SmallPackage(Priority priority, Address senderAddress, Address destinationAdress, boolean acknowledge){
        super(priority,senderAddress,destinationAdress);
        this.acknowledge=acknowledge;
    }
    //2.2.3 methods
    
    /**
     * function to return acknowledge
     * @return:	acknowledge - True or False
     */
    
    public boolean getacknowledge(){return acknowledge;}
    
    /**
     * set function for acknowledge
     * @param: fitback = acknowledge
     */
    
    public void setAcknowledge(boolean fitback){
        this.acknowledge=fitback;
    }
    
    /**
     * funcation that return string
     * The super keyword refers to superclass (parent) objects.
     * @return: acknowledge
     */

    @Override
    public String toString() {
        return super.toString() +
                ","+
                "acknowledge=" + acknowledge +
                ']';
    }
}
