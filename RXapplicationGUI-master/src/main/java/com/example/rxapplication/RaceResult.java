package com.example.rxapplication;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RaceResult {// this object is specifically created for the race table, as we take properties from other classes
    private SimpleStringProperty date,location,name;
    private SimpleIntegerProperty points;

    public String getDate() {
        return date.get();
    }

    public String getLocation() {
        return location.get();
    }

    public String getFullname() {
        return name.get();
    }

    public int getPoints() {
        return points.get();
    }

    public RaceResult(String date, String location, String name, Integer points) {
        this.date = new SimpleStringProperty(date);
        this.location = new SimpleStringProperty(location);
        this.name = new SimpleStringProperty(name);
        this.points = new SimpleIntegerProperty(points);
    }
}
