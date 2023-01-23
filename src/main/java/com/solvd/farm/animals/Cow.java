package com.solvd.farm.animals;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Cow")
public class Cow extends Animal {
    @XmlAttribute
    private long idCow;
    @XmlAttribute
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
