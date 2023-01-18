package com.solvd.farm.animals;

public class Goat extends Animal {
    private long idGoat;
    private long idBelongsFarm;

    public Goat() {

    }

    public Goat(int id, int age, String name, double weight, int idBelongsFarm) {
        super(age, name, weight);
        this.idGoat = id;
        this.idBelongsFarm = idBelongsFarm;
    }

    @Override
    public String toString() {
        return "Goat{" +
                "age=" + this.getAge() +
                ", name='" + this.getName() + '\'' +
                ", weight=" + this.getWeight() +
                '}';
    }

    public long getIdGoat() {
        return idGoat;
    }

    public long getIdBelongsFarm() {
        return idBelongsFarm;
    }
}
