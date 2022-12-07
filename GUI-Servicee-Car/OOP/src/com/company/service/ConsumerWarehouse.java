package com.company.service;

import com.company.Items;
import com.company.Person;
import com.company.exception.TooManyThingsException;

import java.time.LocalDate;
import java.util.*;

public class ConsumerWarehouse extends Warehouse implements Rent{

    public final Map<ConsumerWarehouse, List<Person>> consumerWarehouseListPersonMap = new HashMap<>();
    public final Map<Warehouse, List<Items>> warehouseListItemsMap = new HashMap<>();
    public final Map<Person, Warehouse> typRentWarehouse = new HashMap<>();
    public final List<Person> personList = new ArrayList<>();
    public final List<Items> itemsList = new ArrayList<>();
    public final List<Warehouse> warehouseList = new ArrayList<>();
    private double volumeOfItems = 0;

    public ConsumerWarehouse(String id, double cubicMeter, double costRent) {
        super(id, cubicMeter, costRent);
    }

    public ConsumerWarehouse(String id, double height, double width, double length, double costRent) {
        super(id, height, length, width, costRent);
    }

    // dodawanie przedmiotow
    @Override
    public void addItems(Person person, Items items) throws TooManyThingsException {
        volumeOfItems += items.volumeForItem();
        if(cubicMeter < volumeOfItems && volumeForWarehouse() < volumeOfItems){
            throw new TooManyThingsException();
        } else {
            if(personList.contains(person)) {
                itemsList.add(items);
                warehouseListItemsMap.put(this, itemsList);
                System.out.println("Przedmiot jest wlożony");
            } else {
                System.out.println("Osoba nie ma uprawnienia");
            }
        }
    }

    // wynajęcie przedmiotow
    @Override
    public void takeOutItems(Person person, Items items) {
        if (personList.contains(person)) {
            if (itemsList.contains(items)) {
                itemsList.remove(items);
                warehouseListItemsMap.put(this, itemsList);
                System.out.println("Przedmiot wynajenty");
            } else
                System.out.println("Przedmiota nie ma w magazynie");
        } else {
            System.out.println("Nie ma uprawnienia na wynajęcie przedmiota");
        }
    }

    // rent magazyna
    @Override
    public void rentWarehouse(Person person, LocalDate starRent) {
        do {
            starRent = starRent.plusMonths(1);
            if(person.getMoney() >= costRent){
                person.subtractionOfMoney(costRent);
                mapPersonAndWarehouse.put(person, warehouseList);
                System.out.println("Magazyn jest wynajęty");
            } else {
                System.out.println("Nie wystarcza koszta dla wynajmu magazyna");
            }
        } while (person.getMoney() < costRent);
    }

    // nadanie uprawnienia
    @Override
    public void grandPermission(Person person) {
        if(personList.contains(person)){
            System.out.println("Osoba już ma uprawnienia");
        } else {
            personList.add(person);
            consumerWarehouseListPersonMap.put(this, personList);
        }
    }

    // odbier uprawnienia
    @Override
    public void changePermission(Person person) {
        if(personList.contains(person)) {
            personList.remove(person);
            consumerWarehouseListPersonMap.put(this, personList);
        } else {
            System.out.println("Osoba nie miala uprawnienia");
        }
    }

    @Override
    public Map<Warehouse, List<Items>> personListItemsMap() {
        return warehouseListItemsMap;
    }

    @Override
    public Map<Person, Warehouse> personWarehouseHashMap() {
        return typRentWarehouse;
    }

    @Override
    public String toString() {
        if(cubicMeter == 0.0)
        return "Magazyn " +
                "numer id: " + getId() +
                ", wysokosc: " + height +
                ", szerokosz: " + width +
                ", dlugosc: " + length +
                ", m^3:" + volumeForWarehouse() +
                ", plata za wynajem: " + costRent;
        else
            return "Magazyn " +
                    "numer id:" + getId() +
                    ", m^3: " + cubicMeter +
                    ", plata za wynajem: " + costRent;
    }


}
