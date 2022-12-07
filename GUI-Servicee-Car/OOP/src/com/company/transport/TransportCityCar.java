package com.company.transport;

public class TransportCityCar extends Transport{
    private int combustionLevel;

    public TransportCityCar(String name, double height, double width, double length, int combustionLevel) {
        super(name, height, width, length);
        this.combustionLevel = combustionLevel;
    }

    @Override
    public String toString() {
        return "Samochód miejski " +
                "nazwa: '" + name + '\'' +
                ", wysokosc: " + height +
                ", szerokosz: " + width +
                ", dlugosc: " + length +
                ", moc spalania: " + combustionLevel + "/100 km";
    }
}
