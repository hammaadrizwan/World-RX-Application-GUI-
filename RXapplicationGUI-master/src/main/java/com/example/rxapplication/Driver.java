package com.example.rxapplication;

public class Driver extends Person {// same as the Race class, the driver class implements the serialisable functionality, got help from Telusko tutorial, links are in the report
    private SerializableSimpleStringProperty team,car;// These are the information the player class will store
    private SerializableSimpleIntegerProperty points;
    public Driver(String fname, String lname, Integer age,String team,String car,Integer points) {// assign all the details of the Driver
        super(fname, lname, age);//fname,and all the strings are being stored as an instance of SerialisedSimpleStringProperty Object.
        this.team = new SerializableSimpleStringProperty(team);
        this.car = new SerializableSimpleStringProperty(car);
        this.points = new SerializableSimpleIntegerProperty(points);// the age and points are being stored as an SerialisedSimpleIntegerProperty, as it supports serialisation. major security aspect
    }
    public String getTeam(){
        return this.team.get();// this converts the data from the SerialisationSimple properties to its original data type such as String,Integer and etc. ".get() used to access the data stored in the SimpleString or Integer Properties"
    }
    public String getCar(){
        return this.car.get();
    }
    public int getPoints(){
        return this.points.get();// int has been used for mathematical calculations, as it doesnt consume much memory. only referencing
    }
    //Setters of Driver class (Encapsulation)
    public void setCar(SerializableSimpleStringProperty car){
        this.car=car;
    }
    public void setTeam(SerializableSimpleStringProperty team){
        this.team=team;
    }
    public void setPoints(SerializableSimpleIntegerProperty points){
        this.points=points;//inorder to set values it should be in the Serializable data type
    }

}