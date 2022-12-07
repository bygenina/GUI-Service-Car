package com.company.service;

import com.company.Items;
import com.company.Person;
import com.company.transport.Transport;

import java.time.LocalDate;
import java.util.*;

public class ParkingSpot extends ServiceWarehouse{

    public List<ParkingSpot> parkingSpotList;

    public ParkingSpot(String id, double cubicMeter, double costRent) {
        super(id, cubicMeter, costRent);
        addParkingSpotInList();
    }

    public ParkingSpot(String id, double width, double length, double height, double costRent) {
        super(id, height, length, width, costRent);
        addParkingSpotInList();
    }

    public void addParkingSpotInList () {
        if(parkingSpotList == null) {
            parkingSpotList = new ArrayList<>();
        }
        parkingSpotList.add(this);
    }

    public void rentParkingSpot (Person person, Transport transport) {
            if(!mapPersonAndParkingSpot.containsKey(person)) {
                    parkingSpotList.add(this);
                    mapPersonAndParkingSpot.put(person, parkingSpotList);
                    System.out.println("Pojazd na parkingu");
            } else {
                System.out.println("Pojazd nie zarejestrowany w serwisie. Kożystac z parkingu możemy tylko po naprawie");
            }
    }

    @Override
    public String toString() {
        if(cubicMeter == 0.0) {
            return "Parking " +
                    "numer id: " + getId() +
                    ", wysokosc: " + height +
                    ", szerokosz: " + width +
                    ", dlugosc: " + length +
                    ", m^3:" + volumeForWarehouse() +
                    ", plata za wynajem: " + costRent;
        } else
            return "Parking " +
                    "numer id: " + getId() +
                    ", m^3: " + cubicMeter +
                    ", plata za wynajem: " + costRent;
    }
}
