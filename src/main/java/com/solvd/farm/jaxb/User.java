package com.solvd.farm.jaxb;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private long id;
    @XmlAttribute
    private String fname;
    @XmlAttribute
    private String lname;
    @XmlElement
    private int age;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dof;
    @XmlElement(name = "car")
    private Car car;
    @XmlElementWrapper(name = "computers")
    @XmlElement(name = "computer")
    private List<Computer> computers;

    public User() {

    }

    public User(long id, String fname, String lname, int age, Date dof, Car car, List<Computer> computers) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.dof = dof;
        this.car = car;
        this.computers = computers;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDof() {
        return dof;
    }

    public void setDof(Date dof) {
        this.dof = dof;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", dof=" + dof +
                ", car=" + car +
                ", computers=" + computers +
                '}';
    }
}
