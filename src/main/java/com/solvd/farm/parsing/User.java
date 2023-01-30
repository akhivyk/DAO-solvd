package com.solvd.farm.parsing;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    @JsonProperty("id")
    private long id;
    @XmlElement
    @JsonProperty("fname")
    private String fname;
    @XmlElement
    @JsonProperty("lname")
    private String lname;
    @XmlElement
    @JsonProperty("age")
    private int age;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlAttribute(name = "date")
    @JsonProperty("dof")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date dof;
    @XmlElement(name = "car")
    @JsonProperty("car")
    private Car car;
    @XmlElementWrapper(name = "computers")
    @XmlElement(name = "computer")
    @JsonProperty("computers")
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
                "\n id=" + id +
                "\n fname='" + fname + '\'' +
                "\n lname='" + lname + '\'' +
                "\n age=" + age +
                "\n dof=" + dof +
                "\n car=" + car +
                "\n computers=" + computers +
                '}';
    }
}
