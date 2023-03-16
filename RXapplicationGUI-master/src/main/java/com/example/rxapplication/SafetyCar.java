package com.example.rxapplication;

public class SafetyCar implements Vehicle { //Using Interfaces, where SafteyCar uses the methods in the vehicle interface, display vechicle details.
    private String modelNo;
    private String engineCapacity;
    private int yearOfManufacture;


    public SafetyCar(String modelNo, String engineCapacity, int yearOfManufacture) {
        this.modelNo = modelNo;
        this.engineCapacity = engineCapacity;
        this.yearOfManufacture = yearOfManufacture;

    }

    //this ovverrides the original Function of get vehicle from the vehicle details
    public void getVehicleDetails() {
        String details =  "Safety Car Details [ " + "Model No: " + modelNo + ", " + "Engine HP: " + engineCapacity +", "+ "Year of manufacture: " + yearOfManufacture +" ]";
        System.out.println(details);
    }

}
