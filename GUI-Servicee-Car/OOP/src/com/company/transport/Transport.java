package com.company.transport;

import java.util.LinkedList;
import java.util.List;

public abstract class Transport {
    public String name;
    public double width;
    public double height;
    public double length;
    private List<Transport> transportList;

    public Transport(String name, double height, double width, double length) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.length = length;
        addTransportInList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public List<Transport> getTransportList() {
        return transportList;
    }

    public void setTransportList(List<Transport> transportList) {
        this.transportList = transportList;
    }

    public double volumeForTransport() {
        return getHeight() * getLength() * getWidth();
    }

    public void addTransportInList () {
        if (transportList == null){
            transportList = new LinkedList<>();
        }
        transportList.add(this);
    }


}
