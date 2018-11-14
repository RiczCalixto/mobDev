package br.edu.infnet.travelapp;

public class Place {
    String name;
    String latLong;

    public Place(String name, String latLong) {
        this.name = name;
        this.latLong = latLong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    @Override
    public String toString() {
        return name;
    }
}
