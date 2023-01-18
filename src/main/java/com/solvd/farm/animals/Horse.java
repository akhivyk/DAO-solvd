package com.solvd.farm.animals;

public class Horse extends Animal {
    private long idHorse;
    private int idBelongsFarm;

    public Horse() {

    }

    public Horse(int id, int age, String name, double weight, int idBelongsFarm) {
        super(age, name, weight);
        this.idHorse = id;
        this.idBelongsFarm = idBelongsFarm;
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
