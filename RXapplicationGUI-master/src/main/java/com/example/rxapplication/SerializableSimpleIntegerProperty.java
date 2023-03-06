package com.example.rxapplication;

import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableSimpleIntegerProperty extends SimpleIntegerProperty implements Serializable {//same as SimpleStringProperty but this time its for the IntegerProperty data type

    public SerializableSimpleIntegerProperty() {
        super();//if nothing is entered it will be empty
    }
    public SerializableSimpleIntegerProperty(int initialValue) {
        super(initialValue);//converts the integer data type to the SimpleIntegerPropety
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeInt(get());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        set(s.readInt());
    }
}

