package com.company.transport;

public class TransportOffRoadCar extends Transport{
    private double crossClearance;

    public TransportOffRoadCar(String name, double height, double width, double length, double crossClearance) {
        super(name, height, width, length);
        this.crossClearance = crossClearance;
    }

    public double getCrossClearance() {
        return crossClearance;
    }

    public void setCrossClearance(double crossClearance) {
        this.crossClearance = crossClearance;
    }

    @Override
    public String toString() {
        return "Samochód terenowy " +
                "nazwa: '" + name + '\'' +
                ", wysokosc: " + height +
                ", szerokosz: " + width +
                ", dlugosc: " + length +
                ", prześwit: " + crossClearance + "mm";
    }
}
