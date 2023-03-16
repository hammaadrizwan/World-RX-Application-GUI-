package com.example.rxapplication;

public class Admin extends Person {
    private SerializableSimpleStringProperty position,email,password;
    public Admin(String fname, String lname,Integer age,String email,String password, String position) {
        super(fname, lname, age);
        this.email=new SerializableSimpleStringProperty(email);
        this.password=new SerializableSimpleStringProperty(password);
        this.position=new SerializableSimpleStringProperty(position);
    }
    public String getPosition() {
        return position.get();
    }
    public String getPassword() {
        return password.get();
    }
    public String getEmail() {
        return email.get();
    }
    @Override
    public String getDetails() {
        return super.getDetails() + " [" + position + "]";
    }
}
