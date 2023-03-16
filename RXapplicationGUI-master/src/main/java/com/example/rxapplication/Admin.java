package com.example.rxapplication;

public class Admin extends Person { //This uses the OOP concept of Inhritance where the Admin and Driver uses the fname lname and age values of the Person class(Parent Class)
    private SerializableSimpleStringProperty position,email,password;//Admin details are being needed inorder to signup to the system.
    public Admin(String fname, String lname,Integer age,String email,String password, String position) {
        super(fname, lname, age);//sets the values into the variables from the super class
        this.email=new SerializableSimpleStringProperty(email);
        this.password=new SerializableSimpleStringProperty(password);
        this.position=new SerializableSimpleStringProperty(position);
    }
    public String getPosition() {
        return position.get();
    }//This is used to get the position of the Driver
    public String getPassword() {
        return password.get();
    }
    public String getEmail() {
        return email.get();
    }
    @Override
    public String getDetails() {//overrides the origianl getDetails method, as an admin will have many diffrerent piostitions.
        return super.getDetails() + " [" + getPosition() + "]";
    }
}
