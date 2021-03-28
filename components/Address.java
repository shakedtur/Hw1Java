package components;

public class Address {

    //Fields:
    //1.5.1
    public int zip;
    public int street;
    //Ctor
    //1.5.2
    public Address(){};

    public Address(int zip,int street){
        this.setZip(zip);
        this.setStreet(street);
    }

    @Override
    public String toString() {
        return "Address"+"zip=" + zip +"-"+ street;
    }

    //setters/getters
    public int getZip() {
        return zip;
    }
    public void setZip(int zip) {
        this.zip=Math.abs(zip);
    }
    public int getStreet() {
        return street;
    }
    public void setStreet(int street) {
        if(100000<street && street<1000000)
            this.street=street;
        else
            this.street=111111;//defult address;
    }
}
