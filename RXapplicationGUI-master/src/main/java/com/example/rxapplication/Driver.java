package com.example.rxapplication;

import java.io.Serializable;

public class Driver implements Serializable {// same as the Race class, the driver class implements the serialisable functionality, got help from Telusko
    // These are the information the player class will store
    private SerializableSimpleStringProperty fname,lname,team,car;
    private SerializableSimpleIntegerProperty age,points;

    // assign all the details of the Driver
    public Driver(String fname, String lname, Integer age, String team, String car, Integer points){//Integer is being used than int as, Integer is more versatile compared to int when it comes to throwing Exeptions especially when performing serilisation.
        this.fname = new SerializableSimpleStringProperty(fname);//fname,and all the strings are being stored as an instance of SerialisedSimpleStringProperty Object.
        this.lname = new SerializableSimpleStringProperty(lname);
        this.age = new SerializableSimpleIntegerProperty(age);// the age and points are being stored as an SerialisedSimpleIntegerProperty, as it supports serialisation. major security aspect
        this.team = new SerializableSimpleStringProperty(team);
        this.car = new SerializableSimpleStringProperty(car);
        this.points = new SerializableSimpleIntegerProperty(points);
    }

    //Getters of player class  (Encapsulation)
    public String getFname(){
        return this.fname.get();
    }// this converts the data from the SerialisationSimple properties to its original data type such as String,Integer and etc. ".get() used to access the data stored in the SimpleString or Integer Properties"
    public String getLname(){
        return this.lname.get();
    }
    public int getAge(){
        return this.age.get();
    }// int has been used for mathematical calculations, as it doesnt consume much memory. only referencing
    public String getTeam(){
        return this.team.get();
    }
    public String getCar(){
        return this.car.get();
    }
    public int getPoints(){
        return this.points.get();
    }//same for points

    //Setters of Driver class (Encapsulation)

    //inorder to set values it should be in the Serializable data type
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

