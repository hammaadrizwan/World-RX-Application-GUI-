package com.example.rxapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Race implements Serializable {
    SerializableSimpleStringProperty date;
    SerializableSimpleStringProperty Location;
    ArrayList<Driver> drivers;

    public Race(String date, String location, ArrayList<Driver> drivers) {
        this.date = new SerializableSimpleStringProperty(date);
        Location = new SerializableSimpleStringProperty(location);
        this.drivers = drivers;
    }

    public String getDate() {
        return date.get();
    }

    public String getLocation() {
        return Location.get();
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }
}

