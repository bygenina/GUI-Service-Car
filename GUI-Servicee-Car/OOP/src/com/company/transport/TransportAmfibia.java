package com.company.transport;

public class TransportAmfibia extends Transport{
    private double enginePower;

    public TransportAmfibia(String name, double height, double width, double length, double enginePower) {
        super(name, height, width, length);
        this.enginePower = enginePower;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public String toString() {
        return "Amfibia " +
                "nazwa: '" + name + '\'' +
                ", wysokosc: " + height +
                ", szerokosz: " + width +
                ", dlugosc: " + length +
                ", moc silnika: " + enginePower + "km";
    }
}
