package com.solvd.farm.transport;

public class Tractor extends Vehicle {
    private String color;
    private long idTractor;

    public Tractor() {

    }

    public Tractor(int id, String carBrand, int maxSpeed, String color) {
        super(carBrand, maxSpeed);
        this.color = color;
        this.idTractor = id;
    }

    @Override
    public String toString() {
        return "Tractor{" +
                "color='" + color + '\'' +
                '}';
    }

    public String getColor() {
        return color;
    }

    public long getIdTractor() {
        return idTractor;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
