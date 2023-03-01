package com.example.rxapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Race implements Serializable {
    String date;
    String Location;
    ArrayList<Driver> drivers;

    public Race(String date, String location, ArrayList<Driver> drivers) {
        this.date = date;
        Location = location;
        this.drivers = drivers;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return Location;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }
}

