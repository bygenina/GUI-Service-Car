package com.company.service;

import com.company.Person;
import com.company.transport.TransportAmfibia;
import com.company.transport.Transport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceWarehouse extends Warehouse{

    private double volumeOfTransport = 0;
    public LocalDate startRent;

    public List<ServiceWarehouse> serviceWarehouseList;
    public final Map<ServiceWarehouse, List<Transport>> serviceWarehouseListTransportMap = new HashMap<>();
    public final Map<Person, List<ParkingSpot>> mapPersonAndParkingSpot= new HashMap<>();
    public final Map<Person, Transport> personTransportMap = new HashMap<>();


    public ServiceWarehouse(String id, double cubicMeter, double costRent) {
        super(id, cubicMeter, costRent);
    }

    public ServiceWarehouse(String id, double height, double width, double length, double costRent) {
        super(id, height, length, width, costRent);
    }

    public void rentService (Person person, Transport transport, LocalDate starRent) {
        this.startRent = starRent;
        int day = (int) (Math.random() * 5);
        volumeOfTransport += transport.volumeForTransport();

        if(!mapPersonAndWarehouse.containsKey(this)){
            if(volumeOfTransport < volumeForWarehouse() || volumeOfTransport < cubicMeter) {
                if(person.getMoney() >= costRent) {
                    person.subtractionOfMoney(costRent);
                    mapPersonAndWarehouse.put(person, warehouseList);
                    addServiceWarehouse();
                    serviceWarehouseListTransportMap.put(this, transport.getTransportList());
                    personTransportMap.put(person, transport);
                    System.out.println("Miejsce wolne. Zapraszamy!");
                    for(int i = starRent.getDayOfMonth(); i < starRent.plusDays(day).getDayOfMonth(); i++) {
                        starRent = starRent.plusDays(i);
                    }
                    System.out.println("Pojazd jest naprawiony z dnia: " + starRent + " dzisiaj możecie oddebrac swoj pojazd: " + starRent.plusDays(day) + ", lub my możemy postawic tymczasowo na parking i możliwosc odebrania pojazdu będzie do: " + starRent.plusDays(day + 14) + ". Dziękujemy!");
            } else {
                    System.out.println("Nie wystarcza koszta dla naprawy servisowej");
                }
            } else {
                System.out.println("Nie wystarcza miejsca w serwisie dla pojazda. Możemy postawic tymczasowo na parking, do zwolnienia miejsca");
            }
        } else {
            System.out.println("Miejsce zajente. Możemy postawic pojazd na parking, do zwolnienia miejsca");
            }
    }

    public void addServiceWarehouse () {
        if(serviceWarehouseList == null) {
            serviceWarehouseList = new ArrayList<>();
        }
        serviceWarehouseList.add(this);
    }

    public void removalOfRent (Person person) {
        personTransportMap.remove(person);
        System.out.println("Pojazd odebrany");
    }

    @Override
    public String toString() {
        if(cubicMeter == 0.0) {
            return "ServiceWarehouse{" +
                    "id=" + getId() +
                    ",cubicMeter=" + cubicMeter +
                    '}';
        } else
            return "ServiceWarehouse{" +
                    "id=" + getId() +
                    ", height=" + height +
                    ", width=" + width +
                    ", length=" + length +
                    ", volumeForService" + volumeForWarehouse() +
                    '}';
    }


}
