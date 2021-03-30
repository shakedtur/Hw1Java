package components;

import java.util.Arrays;

public class NonStandardTruck extends Truck implements Node  {
    //fields:2.8.1
    public int width;
    public int length;
    public int height;
    //ctor2.8.2
    public NonStandardTruck() {
    }

    public NonStandardTruck(String licensePlate, String truckModel, int length, int width, int height) {
        super(licensePlate,truckModel);
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void collectPackage(Package p) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deliverPackage(Package p) {
        // TODO Auto-generated method stub

    }

    @Override
    public void work() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString() {
        return "NonStandardTruck{" +
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                ", truckID=" + truckID +
                ", licensePlate='" + licensePlate + '\'' +
                ", truckModel='" + truckModel + '\'' +
                ", available=" + available +
                ", timeLeft=" + timeLeft +
                ", ArratList=" + Arrays.toString(ArratList) +
                '}';
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

