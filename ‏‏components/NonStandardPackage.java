package components;

public class NonStandardPackage extends Package{
    //Fields2.4.1
    public int width;
    public int length;
    public int height;

    //Ctor2.4.2
    public NonStandardPackage(){};
    public NonStandardPackage(Priority priority, Address senderAddress,Address destinationAdress,int width, int length, int height){
        super(priority,senderAddress,destinationAdress);
        setWidth(width);
        setLength(length);
        setHeight(height);
    }
    //methods 2.4.3


    @Override
    public String toString() {
        return super.toString()+
                ","+
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                ']';
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int h){
        this.height=Math.abs(h);
    }
    public int getWidth(){
        return width;
    }
    public void setWidth(int w){
        width=Math.abs(w);
    }
    public int getLength() {
        return length;
    }
    public void setLength(int l){
        length=Math.abs(l);
    }
}
