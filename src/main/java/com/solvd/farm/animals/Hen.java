package com.solvd.farm.animals;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Hen extends Animal {
    @JsonProperty("idHen")
    private long idHen;
    @JsonProperty("idBelongsFarm")
    private int idBelongsFarm;

    public Hen() {

    }

    public Hen(int id, int age, String name, double weight, int idBelongsFarm) {
        super(age, name, weight);
        this.idHen = id;
        this.idBelongsFarm = idBelongsFarm;
    }

    @Override
    public String toString() {
        return "Hen{" +
                "age=" + this.getAge() +
                ", name='" + this.getName() + '\'' +
                ", weight=" + this.getWeight() +
                '}';
    }

    public long getIdHen() {
        return idHen;
    }

    public int getIdBelongsFarm() {
        return idBelongsFarm;
    }
}
