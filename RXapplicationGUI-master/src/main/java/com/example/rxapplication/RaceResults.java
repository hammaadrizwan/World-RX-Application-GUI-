package com.example.rxapplication;

public class RaceResults {
    private Driver driver;
    private String location;
    private String date;
    private int points;

    public RaceResults(Driver driver, LocatiSon location, LocalDate date, int points) {
        this.driver = driver;
        this.location = location;
        this.date = date;
        this.points = points;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return driver.getFname() + " " + driver.getLname();
    }
}
