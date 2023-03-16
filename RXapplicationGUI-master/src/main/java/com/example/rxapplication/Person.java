package com.example.rxapplication;

import java.io.Serializable;

public class Person implements Serializable {
    private SerializableSimpleStringProperty fname,lname;
    private SerializableSimpleIntegerProperty age;

    public Person(String fname, String lname,Integer age) {
        this.fname = new SerializableSimpleStringProperty(fname);
        this.lname = new SerializableSimpleStringProperty(lname);
        this.age= new SerializableSimpleIntegerProperty(age);
    }

    public String getFname() {
        return fname.get();
    }

    public String getLname() {
        return lname.get();
    }
    public int getAge(){
        return age.get();
    }


    public String getDetails() {
        return fname.get().toString() + " " + lname.get().toString();
    }
    public void setFname(SerializableSimpleStringProperty fname){
        this.fname=fname;//gets the value from the user and replaces it with the value which was stored previously in the object
    }
    public void setLname(SerializableSimpleStringProperty lname){
        this.lname=lname;
    }
    public void setAge(SerializableSimpleIntegerProperty age){
        this.age=age;
    }
}

