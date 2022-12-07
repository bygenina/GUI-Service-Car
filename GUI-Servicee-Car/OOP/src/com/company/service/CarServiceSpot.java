package com.company.service;


public class CarServiceSpot extends ServiceWarehouse {

    public CarServiceSpot(String id, double cubicMeter, double costRent) {
        super(id, cubicMeter, costRent);
    }

    public CarServiceSpot(String id, double height, double width, double length, double costRent) {
        super(id, height, length, width, costRent);
    }

    @Override
    public String toString() {
        if(cubicMeter == 0.0) {
            return "Specjalizowane miejsce po naprawie " +
                    "numer id: " + getId() +
                    ", wysokosc: " + height +
                    ", szerokosz: " + width +
                    ", dlugosc: " + length +
                    ", m^3:" + volumeForWarehouse() +
                    ", plata za wynajem: " + costRent;
        } else
            return "Specjalizowane miejsce po naprawie " +
                    "numer id: " + getId() +
                    ", m^3: " + cubicMeter +
                    ", plata za wynajem: " + costRent;

    }
}
