package com.example.rxapplication;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Driver implements Serializable {
    // These are the information the player class will store
    private SerializableSimpleStringProperty fname;
    private SerializableSimpleStringProperty lname;
    private SerializableSimpleIntegerProperty age;
    private SerializableSimpleStringProperty team;
    private SerializableSimpleStringProperty car;
    private SerializableSimpleIntegerProperty points;

    // assign all the details of the Driver
    public Driver(String fname, String lname, Integer age, String team, String car, Integer points){
        this.fname = new SerializableSimpleStringProperty(fname);
        this.lname = new SerializableSimpleStringProperty(lname);
        this.age = new SerializableSimpleIntegerProperty(age);
        this.team = new SerializableSimpleStringProperty(team);
        this.car = new SerializableSimpleStringProperty(car);
        this.points = new SerializableSimpleIntegerProperty(points);
    }

    //Getters of player class  (Encapsulation)
    public String getFname(){
        return this.fname.get();
    }
    public String getLname(){
        return this.lname.get();
    }
    public int getAge(){
        return this.age.get();
    }
    public String getTeam(){
        return this.team.get();
    }
    public String getCar(){
        return this.car.get();
    }
    public int getPoints(){
        return this.points.get();
    }

    //Setters of player class (Encapsulation)
    public void setFname(SerializableSimpleStringProperty fname){
        this.fname=fname;
    }
    public void setLname(SerializableSimpleStringProperty lname){
        this.lname=lname;
    }
    public void setAge(SerializableSimpleIntegerProperty age){
        this.age=age;
    }
    public void setCar(SerializableSimpleStringProperty car){
        this.car=car;
    }
    public void setTeam(SerializableSimpleStringProperty team){
        this.team=team;
    }
    public void setPoints(SerializableSimpleIntegerProperty points){
        this.points=points;
    }

}

