package com.company;

import com.company.exception.NeverRentException;
import com.company.service.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Person {
    private String id;
    public String name;
    public String firstName;
    private String pesel;
    private String adress;
    private String birthDay;
    private LocalDate firstRent;
    private List<Warehouse> warehouseList = new ArrayList<>();
    private List<Person> personList;
    public double money = 1250;


    public Person (String id, String name, String firstName, String pesel, String adress, String birthDay, LocalDate firstRent) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.pesel = pesel;
        this.adress = adress;
        this.birthDay = birthDay;
        this.firstRent = firstRent;
        addPersonInList();
    }

    public void auditRent () throws NeverRentException {
        if (firstRent == null)
            throw new NeverRentException();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public void addPersonInList () {
        if (personList == null){
            personList = new LinkedList<>();
        }
        personList.add(this);
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        this.warehouseList = warehouseList;
    }

    public void addWarehouse (Warehouse warehouse) {
        warehouseList.add(warehouse);
    }

    public double getMoney() {
        return money;
    }

    public void subtractionOfMoney (double costRent) {
        money -= costRent;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Osoba " +
                "numer id: " + id +
                ", imię: '" + name + '\'' +
                ", nazwisko: '" + firstName + '\'' +
                ", PESEL: " + pesel +
                ", address: '" + adress + '\'' +
                ", urodziny: " + birthDay +
                ", pierwsa renta: " + firstRent;
    }
}
