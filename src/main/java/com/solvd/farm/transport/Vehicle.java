package com.solvd.farm.transport;

public abstract class Vehicle {
    private String carBrand;
    private int maxSpeed;

    public Vehicle() {

    }

    public Vehicle(String carBrand, int maxSpeed) {
        this.carBrand = carBrand;
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
}
