package com.solvd.farm.animals;

public class Sheep extends Animal {
    private long idSheep;
    private String color;
    private int idBelongsFarm;


    public Sheep() {

    }

    public Sheep(int id, int age, String name, double weight, String color, int idBelongsFarm) {
        super(age, name, weight);
        this.idSheep = id;
        this.color = color;
        this.idBelongsFarm = idBelongsFarm;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "age=" + this.getAge() +
                ", name='" + this.getName() + '\'' +
                ", weight=" + this.getWeight() + ", color=" + this.getColor() +
                '}';
    }

    public long getIdSheep() {
        return idSheep;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdBelongsFarm() {
        return idBelongsFarm;
    }
}
