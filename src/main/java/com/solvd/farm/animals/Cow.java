package com.solvd.farm.animals;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cow extends Animal {
    @JsonProperty("idCow")
    private long idCow;
    @JsonProperty("idBelongsFarm")
    private int idBelongsFarm;

    public Cow() {

    }

    public Cow(int id, int age, String name, double weight, int idBelongsFarm) {
        super(age, name, weight);
        this.idCow = id;
        this.idBelongsFarm = idBelongsFarm;
    }

    @Override
    public String toString() {
        return "Cow{" +
                "age=" + this.getAge() +
                ", name='" + this.getName() + '\'' +
                ", weight=" + this.getWeight() +
                '}';
    }

    public long getIdCow() {
        return idCow;
    }

    public int getIdBelongsFarm() {
        return idBelongsFarm;
    }
}
