package com.solvd.farm.animals;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animal")
public abstract class Animal {
    @XmlAttribute
    private int age;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private double weight;

    public Animal() {

    }

    public Animal(int age, String name, double weight) {
        this.age = age;
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
