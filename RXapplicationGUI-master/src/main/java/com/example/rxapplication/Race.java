package com.example.rxapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Race implements Serializable { //race is a class that supports serialisation
    SerializableSimpleStringProperty date; //date is stored in a string format, thus since it needs to support serialization and JavaFX Tableview, i have stored it in my own Datatype,SerialisedSimpleStringProperty
    SerializableSimpleStringProperty location;//same for location
    ArrayList<Driver> drivers; // the drivers particpated in the race is stored in an arrayList of Driver data type

    public Race(String date, String location, ArrayList<Driver> drivers) {
        this.date = new SerializableSimpleStringProperty(date);//String is being converted to an object of SerializableSimpleStringProperty
        this.location = new SerializableSimpleStringProperty(location);//same for location
        this.drivers = drivers;
    }

    public String getDate() {
        return date.get();
    }// this returns the date as a String data type after converting from SerializableSimpleStringProperty

    public String getLocation() {
        return location.get();
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }//this returns the list of drivers who were in the race
}

