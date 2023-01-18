package com.solvd.farm.people;

public class Farmer extends Person {
    private long idFarmer;

    public Farmer() {

    }

    public Farmer(int id, String name, int age) {
        super(name, age);
        this.idFarmer = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public long getIdFarmer() {
        return idFarmer;
    }
}
