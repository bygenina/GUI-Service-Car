package com.company.transport;

public class TransportMotorbike extends Transport{

    private double displacement;

    public TransportMotorbike(String name, double height, double width, double length, double displacement) {
        super(name, height, width, length);
        this.displacement = displacement;
    }

    @Override
    public String toString() {
        return "Motorbike " +
                "nazwa: '" + name + '\'' +
                ", wysokosc: " + height +
                ", szerokosz: " + width +
                ", dlugosc: " + length +
                ", pojemność skokowa: " + displacement + "ccm";
    }
}
