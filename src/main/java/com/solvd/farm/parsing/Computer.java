package com.solvd.farm.parsing;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "computer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {
    @XmlAttribute
    private String model;
    @XmlElement
    private int RAM;
    @XmlElement
    private double screenSize;

    public Computer() {

    }

    public Computer(String model, int RAM, double screenSize) {
        this.model = model;
        this.RAM = RAM;
        this.screenSize = screenSize;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "model='" + model + '\'' +
                ", RAM=" + RAM +
                ", screenSize=" + screenSize +
                '}';
    }
}
