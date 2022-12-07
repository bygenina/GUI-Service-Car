package com.company.service;

import com.company.Items;
import com.company.Person;
import com.company.exception.TooManyThingsException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TooManyListenersException;

public interface Rent{

    void addItems (Person person, Items items) throws TooManyThingsException;

    void takeOutItems (Person person, Items items);

    void rentWarehouse (Person person, LocalDate startRent);

    void grandPermission (Person person);

    void changePermission (Person person);

    Map<Warehouse, List<Items>> personListItemsMap ();

    Map<Person, Warehouse> personWarehouseHashMap ();


}
