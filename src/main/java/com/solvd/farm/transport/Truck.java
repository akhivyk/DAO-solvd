package com.solvd.farm.transport;

public class Truck extends Vehicle {
    private double liftingCapacity;
    private long idTruck;

    public Truck() {

    }

    public Truck(int id, String carBrand, int maxSpeed, double liftingCapacity) {
        super(carBrand, maxSpeed);
        this.liftingCapacity = liftingCapacity;
        this.idTruck = id;
    }

    @Override
    public String toString() {
        return "Truck{" + "carBrand = '" + this.getCarBrand() + '\'' + ", maxSpeed = " + this.getMaxSpeed() + '\'' + ", liftingCapacity = " + liftingCapacity + '}';
    }

    public double getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(double liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }

    public long getIdTruck() {
        return idTruck;
    }
}
