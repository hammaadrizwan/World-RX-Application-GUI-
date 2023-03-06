package com.example.rxapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// THIS IS THE MAIN JAVA CLASS Which extends to be of Application class.Since we are using a GUI
public class RxApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("SplashScreen-view.fxml"));//this loads the splashscreen (ie an intro)
        Scene scene = new Scene(root);// this sets to be the main screen at first and all the other scenes would be in this window
        scene.getStylesheets().add(getClass().getResource("splashScreen-stylesheet.css").toExternalForm());//using CSS with Java FX, to improve the design of the GUI. loads the stylesheet from the resources folder.
        stage.setScene(scene);//displays the this screen on the window
        stage.setTitle("World RX Championship Management System");//sets the title for the window
        stage.show();//displays the window
        stage.setResizable(false);//Size is fixed
    }

    public static void main(String[] args) {
        launch();//Starts the application

    }
}