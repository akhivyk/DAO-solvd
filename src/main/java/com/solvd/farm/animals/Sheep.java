package com.solvd.farm.animals;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sheep extends Animal {
    @JsonProperty("idSheep")
    private long idSheep;
    @JsonProperty("color")
    private String color;
    @JsonProperty("idBelongsFarm")
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
