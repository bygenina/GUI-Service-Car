package com.company.service;

import com.company.Items;
import com.company.Person;

import java.time.LocalDate;
import java.util.*;

public abstract class Warehouse {
    private String id;
    public double cubicMeter;
    public double height;
    public double width;
    public double length;
    public double costRent;
    public boolean busy = true;
    public final List<Warehouse> warehouseList = new LinkedList<>();
    public final Map<Person, List<Warehouse>> mapPersonAndWarehouse= new HashMap<>();
    private double volumeOfService = 0;

    public Warehouse (String id, double cubicMeter, double costRent) {
        this.id = id;
        this.cubicMeter = cubicMeter;
        this.costRent = costRent;
    }

    public Warehouse(String id, double height, double width, double length, double costRent){
        this.id = id;
        this.height = height;
        this.width = width;
        this.length = length;
        this.costRent = costRent;
    }

    public double getCubicMeter() {
        return cubicMeter;
    }

    public void setCubicMeter(double cubicMeter) {
        this.cubicMeter = cubicMeter;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCostRent() {
        return costRent;
    }

    public void setCostRent(double costRent) {
        this.costRent = costRent;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public Map<Person, List<Warehouse>> getMapPersonAndWarehouse() {
        return mapPersonAndWarehouse;
    }

    public double volumeForWarehouse () {
        return getHeight()*getLength()*getWidth();
    }

    public void addWarehouse(Service service) throws Exception{
        volumeOfService = volumeOfService + service.cubicMeter + service.volumeForService();
        if (volumeForWarehouse() > volumeOfService || cubicMeter > volumeOfService) {

            throw new Exception("Duża objętość magazynu dla servisa");
        } else {
            warehouseList.add(this);
        }
    }

}
