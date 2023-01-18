package com.solvd.farm.animals;

public class Hen extends Animal {
    private long idHen;
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
