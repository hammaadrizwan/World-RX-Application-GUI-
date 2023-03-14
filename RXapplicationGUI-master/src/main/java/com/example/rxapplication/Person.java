package com.example.rxapplication;

public class Person {
    public String fname;
    public String lname;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }


}
class Admin extends Person {//used for signup page
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Admin() {
        this.fname="Micheal";
        this.lname="Masi";
        this.email="admin@fia.com";
        this.password="admin@123";
    }
}
