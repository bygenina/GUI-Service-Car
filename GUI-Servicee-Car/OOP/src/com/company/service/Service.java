package com.company.service;

import java.time.LocalDate;
import java.time.LocalTime;

public class Service {
    private String id;
    public String name;
    public String address;
    public double cubicMeter;
    public double height;
    public double width;
    public double length;
    public LocalTime openService;
    public LocalTime closeService;

    public Service(String id, String name, String address, double cubicMeter, LocalTime openService, LocalTime closeService) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cubicMeter = cubicMeter;
        this.openService = openService;
        this.closeService = closeService;
    }

    public Service(String id, String name, String address, double height, double width, double length, LocalTime openService, LocalTime closeService) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.height = height;
        this.width = width;
        this.length = length;
        this.openService = openService;
        this.closeService = closeService;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getOpenService() {
        return openService;
    }

    public void setOpenService(LocalTime openService) {
        this.openService = openService;
    }

    public LocalTime getCloseService() {
        return closeService;
    }

    public void setCloseService(LocalTime closeService) {
        this.closeService = closeService;
    }

    public double volumeForService () {
        return getHeight()*getLength()*getWidth();
    }

    @Override
    public String toString() {
        if(cubicMeter == 0.0) {
            return "Service{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", height=" + height +
                    ", width=" + width +
                    ", length=" + length +
                    ", volumeForService=" + volumeForService() +
                    ", openService=" + openService +
                    ", closeService=" + closeService +
                    '}';
        } else
            return "Service{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", cubicMeter=" + cubicMeter +
                    ", openService=" + openService +
                    ", closeService=" + closeService +
                    '}';
    }
}
