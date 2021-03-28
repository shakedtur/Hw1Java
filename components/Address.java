package components;

public class Address {

    //Fields:
    //1.5.1
    public int zip;
    public int street;
    //Ctor
    //1.5.2
    public Address(int zip,int street){
        this.zip=Math.abs(zip);
        if(0<street && street<1000000)
            this.street=street;
        else
            street=111111;//defult address;
    }

    //setters/getters
    public int getZip() {
        return zip;
    }
    public void setZip(int zip) {
        this.zip = zip;
    }
    public int getStreet() {
        return street;
    }
    public void setStreet(int street) {
        this.street = street;
    }
}
