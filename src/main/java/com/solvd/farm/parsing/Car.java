package com.solvd.farm.parsing;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private String carBrand;
    @XmlElement
    private int maxSpeed;
    @XmlElement
    private int countPlace;

    public Car() {

    }

    public Car(String carBrand, int maxSpeed, int countPlace) {
        this.carBrand = carBrand;
        this.maxSpeed = maxSpeed;
        this.countPlace = countPlace;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getCountPlace() {
        return countPlace;
    }

    public void setCountPlace(int countPlace) {
        this.countPlace = countPlace;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carBrand='" + carBrand + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", countPlace=" + countPlace +
                '}';
    }
}

