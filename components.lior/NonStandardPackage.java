package components;

/**
 * Represents the non-standard size packages.
 * @version 1.10 09 April 2011
 * @author  Shaked Turgeman , Lior Daichman
 * @see     Package   
 */

public class NonStandardPackage extends Package{
    //Fields2.4.1
    public int width;
    public int length;
    public int height;

    //Ctor2.4.2
    /**
     * Ctor
     */
    public NonStandardPackage(){};
    
    /**
     * Ctor which receives as priority arguments, sender and recipient addresses, and package dimensions.
     * The super keyword refers to superclass (parent) objects.
     * @param:
     * 			priority - priorities: LOW, STANDARD, HIGHT
     *  		senderAddress - Address who send the package
     *  		destinationAdress - Destination address who send the package
     *   		width, length, height - package dimensions
     */
    
    public NonStandardPackage(Priority priority, Address senderAddress,Address destinationAdress,int width, int length, int height){
        super(priority,senderAddress,destinationAdress);
        setWidth(width);
        setLength(length);
        setHeight(height);
    }
    //methods 2.4.3

    /**
     * funcation that return string
     * The super keyword refers to superclass (parent) objects.
     * @return: width, length, height - package dimensions
     */
    @Override
    public String toString() {
        return super.toString()+
                ","+
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                ']';
    }
    
    /**
     * funcation get Height
     * @return: height
     */

    public int getHeight() {
        return height;
    }
    
    /**
     * funcation set Height
     * Math.abs(h) returns the absolute value of a given argument.
     * @param height = h
     */
    
    public void setHeight(int h){
        this.height=Math.abs(h);
    }
    
    /**
     * funcation get width
     * @return: width
     */
    
    public int getWidth(){
        return width;
    }
    
    /**
     * funcation set width
     * Math.abs(w) returns the absolute value of a given argument.
     * @param width = w
     */
    
    public void setWidth(int w){
        width=Math.abs(w);
    }
    
    /**
     * funcation get length
     * @return: length
     */
    
    public int getLength() {
        return length;
    }
    
    /**
     * funcation set length
     * Math.abs(l) returns the absolute value of a given argument.
     * @param length = l
     */
    
    public void setLength(int l){
        length=Math.abs(l);
    }
}
