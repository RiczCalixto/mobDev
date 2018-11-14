package io.github.mobileteacher.carviewer;

public class Car {

    String make;
    String model;
    int year;
    double price;

    public Car(String manufacturer, String model, int year, double price) {
        this.make = manufacturer;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("#\n%s\n%s\n%d\n%f\n", make, model, year, price);
    }
}
