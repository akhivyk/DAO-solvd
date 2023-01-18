package com.solvd.farm.animals;

public class Cow extends Animal {
    private long idCow;
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
