package com.example.rxapplication;

public class RaceCar implements Vehicle {//impelements methods of the interface class. OOP concept used :interface
    private String modelNo;
    private String engineCapacity;
    private String driverName;

    public RaceCar(String modelNo, String engineCapacity, String driverName) {// a race car will have a model no,engine capacity and the name of the driver
        this.modelNo = modelNo;
        this.engineCapacity = engineCapacity;
        this.driverName = driverName;
    }

    public void getVehicleDetails() {//displays the details when called
        String details =  "Race Car Details [ " + "Model No: " + modelNo + ", " + "Engine HP: " + engineCapacity +", "+ "Driver: " + driverName +" ]";
        System.out.println(details);
    }
}
