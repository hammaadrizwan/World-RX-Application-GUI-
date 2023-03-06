package com.example.rxapplication;

import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Driver implements Serializable {
    // These are the information the player class will store
    private String fname;
    private String lname;
    private int age;
    private String team;
    private String car;
    private int points;

    // assign all the details of the
    Driver(String fname, String lname, int age, String team, String car, int points){
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.team = team;
        this.car = car;
        this.points = points;
    }

    //Getters of player class  (Encapsulation)
    String getFname(){
        return this.fname;
    }
    String getLname(){
        return this.lname;
    }
    int getAge(){
        return this.age;
    }
    String getTeam(){
        return this.team;
    }
    String getCar(){
        return this.car;
    }
    int getPoints(){
        return this.points;
    }

    //Setters of player class (Encapsulation)
    void setFname(String fname){
        this.fname=fname;
    }
    void setLname(String lname){
        this.lname=lname;
    }
    void setAge(int age){
        this.age=age;
    }
    void setCar(String car){
        this.car=car;
    }
    void setTeam(String team){this.team=team;}
    void setPoints(int points){
        this.points=points;
    }

}

