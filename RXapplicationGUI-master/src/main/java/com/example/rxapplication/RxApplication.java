package com.example.rxapplication;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


// THIS IS THE MAIN JAVA CLASS Which extends to be of Application class.
// Since we are using a GUI
public class RxApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("SplashScreen-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("World RX Championship Management System");
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();

    }
}