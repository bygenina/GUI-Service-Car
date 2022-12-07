package com.company;

public class Items {
    private String id;
    public String name;
    public double width;
    public double height;
    public double length;

    public Items(String id, String name, double height, double width, double length) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double volumeForItem () {
        return getHeight()*getLength()*getWidth();
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", volume=" + volumeForItem() +
                '}';
    }
}
