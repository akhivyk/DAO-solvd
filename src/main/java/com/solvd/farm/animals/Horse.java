package com.solvd.farm.animals;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Horse extends Animal {
    @JsonProperty("idHorse")
    private long idHorse;
    @JsonProperty("idBelongsFarm")
    private int idBelongsFarm;

    public Horse() {

    }

    public Horse(int id, int age, String name, double weight, int idBelongsFarm) {
        super(age, name, weight);
        this.idHorse = id;
        this.idBelongsFarm = idBelongsFarm;
    }

    public Horse(int age, String name, double weight) {
        super(age, name, weight);
    }

    @Override
    public String toString() {
        return "Horse{" +
                "age=" + this.getAge() +
                ", name='" + this.getName() + '\'' +
                ", weight=" + this.getWeight() +
                '}';
    }

    public long getIdHorse() {
        return idHorse;
    }

    public int getIdBelongsFarm() {
        return idBelongsFarm;
    }
}
