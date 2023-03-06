package com.example.rxapplication;


import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableSimpleStringProperty extends SimpleStringProperty implements Serializable { //This code allows us to Serialize a SimpleStringProperty, as it originally doesnt support Serialisation

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF(get());//This writes the value of the String object to the File
    }

    private void readObject(ObjectInputStream in) throws IOException {
        set(in.readUTF());//This reads the value of the String and sets it as a SimpleStringProperty.
    }

    public SerializableSimpleStringProperty(String initialValue) {//converts the initial String Value to a SimpleStringProperty, we cannot use StringProperty as its an abstract class so we take the implementation odf that which uses stringProperties attributes
        super(initialValue);//calls the method from the super class which is StringProperty.
    }
}
