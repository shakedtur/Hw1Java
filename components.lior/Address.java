package components;

/**
 * Address (of sender or recipient). The address consists of two integers, separated by a line. 
 * The first number (zip) determines the branch to which the address belongs, the second number (street) the street number.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 */

public class Address {

    //Fields:
    //1.5.1
    public int zip;
    public int street;
    //Ctor
    //1.5.2
    public Address(){};
    
    /**
     * The constructor accepts variables and inserts them into variables in the class.
     * @param :
     * 			Zip - The branch to which the address belongs
     * 			Street - Street number.
     */

    public Address(int zip,int street){
        this.setZip(zip);
        this.setStreet(street);
    }
    
    /**
     * funcation to string
     * @return : string of zip and street
     */

    @Override
    public String toString() {
        return zip +"-"+ street;
    }
    
    /**
     * @return : zip
     */

    //setters/getters
    public int getZip() {
        return zip;
    }
    
    /**
     *  Math.abs(zip) returns the absolute value of a given argument.
     * @param : zip
     */
    
    public void setZip(int zip) {
        this.zip=Math.abs(zip);
    }
    
    /**
     * @return : street
     */
    
    public int getStreet() {
        return street;
    }
    
    /**
     *  checking size of street and if its not in range make its defult 111111
     * @param : street
     */
    
    public void setStreet(int street) {
        if(100000<street && street<1000000)
            this.street=street;
        else
            this.street=111111;//defult address;
    }
}
