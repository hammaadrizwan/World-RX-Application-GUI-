package com.example.rxapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



import java.io.*;
import java.util.*;
import java.lang.*;

//This is the real deal. All functions, controlls and every single thing of the application runs from here.
public class Controller {
    // Declaring all the variables that would be used from the FXML application.
    // sets it to private as it can be accessed only from inside this code

    //ROOT window elements, this will change as we switch between scenes(the backbone of the GUI, everything depends on these 2)
    private Stage stage;
    private Scene scene;

    //main screen elements(Home page)
    @FXML private Button refreshButton;//all the labels in the mainScreen are stored here, each an every cell in the Last race Table. Last Race Highlights is an improvement to program, and also Top2 head to head.
    @FXML private Label lrhPos1Label,lrhName1Label,lrhCar1Label,lrhPoints1Label,lrhPos2Label,lrhName2Label,lrhCar2Label,lrhPoints2Label,lrhPos3Label,lrhName3Label,lrhCar3Label,lrhPoints3Label,lrhLocationLabel,lrhDateLabel,firstPlaceDriverFname,firstPlaceDriverLname,secondPlaceDriverFname,secondPlaceDriverLname,firstPlaceDriverWins,firstPlaceDriverTop3,secondPlaceDriverTop3;

    //ADD window elements (this refers the addwindow FXML file)
    @FXML public TextField fnameInput,lnameInput,ageInput,carInput,teamInput,pointsInput;//the input fields to add the driver
    @FXML private Label fnameMessage,lnameMessage,ageMessage,teamMessage,carMessage,pointsMessage,successLabel;//the messages which need to be displayed if incorrect data has been entered


    //UDD window elements
    @FXML private TextField nameInputofDriver;
    @FXML private AnchorPane updateFieldsPane;//this is same as Add window, as fields will be updated

    //DDD window elements
    @FXML private Label nameInputStored;
    @FXML private AnchorPane deletePane;

    //VCT window elements
    @FXML private Button refreshButtonVCTWindow; //to refresh championship standings
    @FXML private TableView<Driver> championshipDataView; //table to display the data
    @FXML private TableColumn<Driver,String> firstnameColumnChampionshipData,lastnameColumnChampionshipData,teamColumnChampionshipData,carColumnChampionshipData; //each and every column and the data type it will store
    @FXML private TableColumn<Driver,Integer> ageColumnChampionshipData,pointsColumnChampionshipData;



    ArrayList<Driver> drivers = new ArrayList<Driver>(2); //Initially creates an empty ArrayList of the relavent data types to save its specific object types
    ArrayList<Race> races = new ArrayList<Race>(2);

    String championshipDataFilePath="Z:\\ProgrammingCW\\RXAppStable\\RXapplicationGUI-master\\src\\main\\java\\com\\example\\rxapplication\\championshipData.txt"; // the path of the file is being stored as variable
    String raceDataFilePath="Z:\\ProgrammingCW\\RXAppStable\\RXapplicationGUI-master\\src\\main\\java\\com\\example\\rxapplication\\raceData.txt"; // the path of the file is being stored in the variable


    public void loadMainScreen(ActionEvent event) throws IOException { //this loads the main screen
        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-main.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("World RX Championship Management System");
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());//refers the main stylesheet
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);//now the Home page is open
    }

    public void loadLastRaceData() throws IOException, ClassNotFoundException {
        refreshButton.setOpacity(0.0f);//once the refresh button is clicked it will be disspeared as it displays all the last race data, from the VRL function(gets the most recent race)

        drivers = readFromFileChampionshipData();
        races = readFromFileRaceData(); // loads the racedata to an ArrayList
        //sortedRaces = vrlFunction(races);
        Race race = races.get(races.size()-1); //loads the last race..change to the sorted one later
        String raceLocation = race.getLocation();// gets the raceLocatoin and the raceDate from the last race,
        String raceDate = race.getDate();

        lrhLocationLabel.setText(raceLocation.toUpperCase());//setting it to the text to the table
        lrhDateLabel.setText(raceDate);
        Driver firstPLaceDriverResult = race.getDrivers().get(0);//driver positions accordingly
        Driver secondPLaceDriverResult = race.getDrivers().get(1);
        Driver thirdPLaceDriverResult = race.getDrivers().get(2);

        lrhPos1Label.setText("1");//first place driver details
        lrhName1Label.setText((firstPLaceDriverResult.getFname()+" "+firstPLaceDriverResult.getLname()));
        lrhCar1Label.setText((firstPLaceDriverResult.getCar()));
        lrhPoints1Label.setText("+10");

        lrhPos2Label.setText("2");
        lrhName2Label.setText((secondPLaceDriverResult.getFname()+" "+secondPLaceDriverResult.getLname()));
        lrhCar2Label.setText((secondPLaceDriverResult.getCar()));
        lrhPoints2Label.setText("+7");

        lrhPos3Label.setText("3");
        lrhName3Label.setText((thirdPLaceDriverResult.getFname()+" "+thirdPLaceDriverResult.getLname()));
        lrhCar3Label.setText((thirdPLaceDriverResult.getCar()));
        lrhPoints3Label.setText("+5");


        ArrayList<Driver> sortedDriver = sortChampionshipData(drivers);
        //sortedDriver = vctFunction(drivers); takes in the sorted standings table and displays the top 2 drivers.
        firstPlaceDriverFname.setText(sortedDriver.get(0).getFname().toUpperCase());
        firstPlaceDriverLname.setText(sortedDriver.get(0).getLname().toUpperCase());
        secondPlaceDriverFname.setText(sortedDriver.get(1).getFname().toUpperCase());
        secondPlaceDriverLname.setText(sortedDriver.get(1).getLname().toUpperCase());

    }


    public void addButtonClicked(ActionEvent event) throws IOException { //Opens the ADD driver window from main page
        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-addWindow.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Driver Registration");
        stage.show();
        stage.setResizable(false);

    }

    public void addButtonSignupClicked() throws IOException, ClassNotFoundException { //To save the driver detials into the system
        drivers = readFromFileChampionshipData(); // reads the drivers data to the system

        boolean fnameValid=true;// initally all the input fields are set to be valid
        boolean lnameValid=true;
        boolean ageValid=true;
        boolean teamValid=true;
        boolean carValid=true;
        boolean pointsValid=true;

        if (fnameInput.getText().toString().equals("")){//checks if the first name input field is blank
            fnameMessage.setText("Cannot be empty");//displays a message to the user to re-enter
            fnameValid=false;//sets fname validity to be false
        }else{//checks whether there is atleast one integer in the firstname: since name cannot contain any number/digits
            char[] fnameCharacters= fnameInput.getText().toString().toCharArray();// converts the string to a sqeuence of characters
            for (char character: fnameCharacters) {//and by using an enhanced for loop, the program checks whether a number is present in the list of characters
                if (Character.isDigit(character)){// using Character object's in buikt isDigit() methid to check whehter the character is a digit
                    fnameValid=false;//if it is then fnameInput will be invalid and the loop will break
                    break;
                }
            }
            if (fnameValid==false){//if the fname is invalid, a message will be displayed to the user saying its incorrect
                fnameMessage.setText("Incorrect Value");
            }else{
                fnameMessage.setText("");//if it is valid there will be no message dispalyed as the field is of correct data type.

            }
        }

        if (lnameInput.getText().toString().equals("")){//the same procedure to check for correct value entered in lastname field
            lnameMessage.setText("Cannot be empty");
            lnameValid=false;
        }else{
            char[] lnameCharacters= lnameInput.getText().toString().toCharArray();
            for (char character: lnameCharacters) {
                if (Character.isDigit(character)){
                    lnameValid=false;
                    break;
                }
            }
            if (lnameValid==false){
                lnameMessage.setText("Incorrect Value");
            }else{
                lnameMessage.setText("");

            }
        }

        if (ageInput.getText().toString().equals("")){
            ageMessage.setText("Cannot be empty");
            ageValid=false;
        }else{
            char[] ageCharacters = ageInput.getText().toString().toCharArray();// for age we check if there is no digit in the character sequence meaning it is not an integer
            for (char character: ageCharacters) {
                if (!Character.isDigit(character)){
                    ageValid=false;//if there is atleast one non integer element the validity will be false
                    break;
                }
            }if (ageValid==false){
                ageMessage.setText("Incorrect Type");
            }else{
                ageValid = true;
                ageMessage.setText("");

            }
        }

        if (carInput.getText().toString().equals("")){//checks if the car input field is empty as these inputs are important
            carMessage.setText("Cannot be empty");
            carValid=false;
        }else{
            carMessage.setText("");
        }

        if (teamInput.getText().toString().equals("")){//same procedure as carInput field
            teamMessage.setText("Cannot be empty");
            teamValid=false;
        }else{
            teamMessage.setText("");
        }

        if (pointsInput.getText().toString().equals("")){//validation procedure is same as age as we only check if the data tpe for the field is an integer.
            pointsMessage.setText("Cannot be empty");
            pointsValid=false;
        }else{
            char[] pointsCharacters = pointsInput.getText().toString().toCharArray();
            for (char character: pointsCharacters) {
                if (!Character.isDigit(character)){
                    pointsValid=false;
                    break;
                }
            }if (pointsValid==false){
                pointsMessage.setText("Incorrect Type");
            }else{
                pointsMessage.setText("");
            }
        }

        if (fnameValid && lnameValid && ageValid && teamValid && carValid && pointsValid) {// if all are valid
            String firstName = toTitleCase(fnameInput.getText().toString().strip());// we clean all the input as it is ready for the being written to the file
            String lastName = toTitleCase(lnameInput.getText().toString().strip());
            Integer age = Integer.parseInt(ageInput.getText().toString());//convert the string to an integer
            String team = (teamInput.getText().toString().strip());
            String car = (carInput.getText().toString().strip());
            Integer points = Integer.parseInt(pointsInput.getText().toString());

            Driver driver = new Driver(firstName, lastName, age, team, car, points);//creates a new driver of the Driver class
            boolean recordExsists = false;
            for (Driver availableDriver:drivers) {
                if (driver.getFname().equals(availableDriver.getFname())&&driver.getLname().equals(availableDriver.getLname())){
                    recordExsists=true;
                    break;
                }
            }
            if (recordExsists==false){
                drivers.add(driver);// adds the driver to the list of available drivers

                writeToFileChampionshipData(drivers);//saves the updated changes into the championshipData

                successLabel.setOpacity(1.0f);
                successLabel.setText(("Successfully added "+driver.getFname()));
                successLabel.setTextFill(Color.rgb(47, 130, 73));
                successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
            }else {
                successLabel.setOpacity(1.0f);
                successLabel.setText(("Error: "+driver.getFname()+" ,already exists!"));
                successLabel.setTextFill(Color.rgb(117, 29, 29));
                successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
            }

            fnameInput.setText("");
            lnameInput.setText("");
            ageInput.setText("");
            teamInput.setText("");
            carInput.setText("");
            pointsInput.setText("");
            for (Driver individualDriver : drivers) {
                System.out.println(String.format("%s   %s    %d  %s  %s      %d",individualDriver.getFname(),individualDriver.getLname(), individualDriver.getAge(), individualDriver.getTeam(), individualDriver.getCar(), individualDriver.getPoints()));
            }
        }
    }

//        Collections.shuffle(drivers);
//        System.out.println();6
//        for (int i = 1; i < drivers.size(); i++) {
//            for (int j = 1; j < drivers.size() - 1; j++) {
//                Driver currentDriver = drivers.get(j);
//                Driver nextDriver = drivers.get(j + 1);
//                if (currentDriver.getPoints() < nextDriver.getPoints()) {
//                    Driver temp = currentDriver;
//                    drivers.set(j, nextDriver);
//                    drivers.set(j + 1, temp);
//                }
//            }
//        }
//        for (Driver individualDriver : drivers) {
//            System.out.println(String.format("%s   %s    %d  %s  %s      %d",individualDriver.getFname(),individualDriver.getLname(), individualDriver.getAge(), individualDriver.getTeam(), individualDriver.getCar(), individualDriver.getPoints()));
//        }

    public void deleteButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        drivers=readFromFileChampionshipData();
        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-dddWindow.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Delete Driver Details");
        stage.show();
        stage.setResizable(false);
    }
    public void findDriverToBeDeleted() throws IOException, ClassNotFoundException {
        drivers=readFromFileChampionshipData();
        boolean found = false;
        String nameOfDriverToBeUpdated = nameInputofDriver.getText().toString();

        int index = 0;
        for (Driver driver:drivers){
            String availableDriverName = driver.getFname()+" "+driver.getLname();
            if (availableDriverName.equals(nameOfDriverToBeUpdated)){
                found=true;
                nameInputStored.setText(nameOfDriverToBeUpdated);
                break;
            }
            index++;
        }
        if (found==false){
            successLabel.setOpacity(1.0f);
            successLabel.setText(("Error: "+nameOfDriverToBeUpdated+" ,does not exist!"));
            successLabel.setTextFill(Color.rgb(117, 29, 29));
            successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
        }else {
            successLabel.setOpacity(1.0f);
            successLabel.setText(("Found records of "+nameOfDriverToBeUpdated));
            successLabel.setTextFill(Color.rgb(47, 130, 73));
            successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
            deletePane.setOpacity(1.0f);
        }
    }

    public void onDeleteConfirmationClicked() throws IOException, ClassNotFoundException {
        drivers = readFromFileChampionshipData();
        String nameOfDriverToBeUpdated = nameInputStored.getText().toString();
        for (Driver driver:drivers){
            String availableDriverName = driver.getFname()+" "+driver.getLname();
            if (availableDriverName.equals(nameOfDriverToBeUpdated)){
                drivers.remove(driver);
                break;
            }
        }
        writeToFileChampionshipData(drivers);
        successLabel.setOpacity(1.0f);
        successLabel.setText(("Deleted records of "+nameOfDriverToBeUpdated));
        successLabel.setTextFill(Color.rgb(47, 130, 73));
        successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));


    }

    public void updatebuttonclicked(ActionEvent event) throws IOException, ClassNotFoundException {
        drivers=readFromFileChampionshipData();
        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-uddWindow.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Update Driver Details");
        stage.show();
        stage.setResizable(false);
    }

    //    public void refershDriverNames() throws IOException, ClassNotFoundException {
//        drivers = readFromFileChampionshipData();
//        String[] possibleDriverNames = new String[drivers.size()];
//
//        int index =0;
//        for (Driver driver:drivers){
//            String driverName = driver.getFname()+" "+driver.getLname();
//            possibleDriverNames[index] = driverName;
//            index++;
//        }
//
//        TextFields.bindAutoCompletion(nameInputofDriver,possibleDriverNames); //https://www.youtube.com/watch?v=SkXYg3M0hOQ&ab_channel=CoolITHelp
//
//    }
    public void findDriverToBeUpdated() throws IOException, ClassNotFoundException {
        drivers=readFromFileChampionshipData();
        boolean found = false;
        String nameOfDriverToBeUpdated = nameInputofDriver.getText().toString();

        int index = 0;
        for (Driver driver:drivers){
            String availableDriverName = driver.getFname()+" "+driver.getLname();
            if (availableDriverName.equals(nameOfDriverToBeUpdated)){
                found=true;
                break;
            }
            index++;
        }
        if (found==false){
            successLabel.setOpacity(1.0f);
            successLabel.setText(("Error: "+nameOfDriverToBeUpdated+" ,does not exist!"));
            successLabel.setTextFill(Color.rgb(117, 29, 29));
            successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
            updateFieldsPane.setOpacity(0.0f);

        }else {
            successLabel.setOpacity(1.0f);
            successLabel.setText(("Found records of "+nameOfDriverToBeUpdated));
            successLabel.setTextFill(Color.rgb(47, 130, 73));
            successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
            updateFieldsPane.setOpacity(1.0f);
        }
        boolean fnameValid=true;// initally all the input fields are set to be valid
        boolean lnameValid=true;
        boolean ageValid=true;
        boolean teamValid=true;
        boolean carValid=true;
        boolean pointsValid=true;

        if (fnameInput.getText().toString().equals("")){//checks if the first name input field is blank
            fnameMessage.setText("Cannot be empty");//displays a message to the user to re-enter
            fnameValid=false;//sets fname validity to be false
        }else{//checks whether there is atleast one integer in the firstname: since name cannot contain any number/digits
            char[] fnameCharacters= fnameInput.getText().toString().toCharArray();// converts the string to a sqeuence of characters
            for (char character: fnameCharacters) {//and by using an enhanced for loop, the program checks whether a number is present in the list of characters
                if (Character.isDigit(character)){// using Character object's in buikt isDigit() methid to check whehter the character is a digit
                    fnameValid=false;//if it is then fnameInput will be invalid and the loop will break
                    break;
                }
            }
            if (fnameValid==false){//if the fname is invalid, a message will be displayed to the user saying its incorrect
                fnameMessage.setText("Incorrect Value");
            }else{
                fnameMessage.setText("");//if it is valid there will be no message dispalyed as the field is of correct data type.

            }
        }

        if (lnameInput.getText().toString().equals("")){//the same procedure to check for correct value entered in lastname field
            lnameMessage.setText("Cannot be empty");
            lnameValid=false;
        }else{
            char[] lnameCharacters= lnameInput.getText().toString().toCharArray();
            for (char character: lnameCharacters) {
                if (Character.isDigit(character)){
                    lnameValid=false;
                    break;
                }
            }
            if (lnameValid==false){
                lnameMessage.setText("Incorrect Value");
            }else{
                lnameMessage.setText("");

            }
        }

        if (ageInput.getText().toString().equals("")){
            ageMessage.setText("Cannot be empty");
            ageValid=false;
        }else{
            char[] ageCharacters = ageInput.getText().toString().toCharArray();// for age we check if there is no digit in the character sequence meaning it is not an integer
            for (char character: ageCharacters) {
                if (!Character.isDigit(character)){
                    ageValid=false;//if there is atleast one non integer element the validity will be false
                    break;
                }
            }if (ageValid==false){
                ageMessage.setText("Incorrect Type");
            }else{
                ageValid = true;
                ageMessage.setText("");

            }
        }

        if (carInput.getText().toString().equals("")){//checks if the car input field is empty as these inputs are important
            carMessage.setText("Cannot be empty");
            carValid=false;
        }else{
            carMessage.setText("");
        }

        if (teamInput.getText().toString().equals("")){//same procedure as carInput field
            teamMessage.setText("Cannot be empty");
            teamValid=false;
        }else{
            teamMessage.setText("");
        }

        if (pointsInput.getText().toString().equals("")){//validation procedure is same as age as we only check if the data tpe for the field is an integer.
            pointsMessage.setText("Cannot be empty");
            pointsValid=false;
        }else{
            char[] pointsCharacters = pointsInput.getText().toString().toCharArray();
            for (char character: pointsCharacters) {
                if (!Character.isDigit(character)){
                    pointsValid=false;
                    break;
                }
            }if (pointsValid==false){
                pointsMessage.setText("Incorrect Type");
            }else{
                pointsMessage.setText("");
            }
        }

        if (fnameValid && lnameValid && ageValid && teamValid && carValid && pointsValid) {// if all are valid
            String firstName = toTitleCase(fnameInput.getText().toString().strip());// we clean all the input as it is ready for the being written to the file
            String lastName = toTitleCase(lnameInput.getText().toString().strip());
            Integer age = Integer.parseInt(ageInput.getText().toString());//convert the string to an integer
            String team = (teamInput.getText().toString().strip());
            String car = (carInput.getText().toString().strip());
            Integer points = Integer.parseInt(pointsInput.getText().toString());

            boolean recordExsists = false;
            int availableIndex = 0;
            for (Driver driver:drivers) {
                if (availableIndex ==index){
                    continue;
                }
                if ((drivers.get(availableIndex).getFname().equals(firstName)&&drivers.get(availableIndex).getLname().equals(lastName))){
                    recordExsists=true;
                    break;
                }
                availableIndex++;
            }
            if (recordExsists==false){
                Driver storedDriver = drivers.get(index);
                storedDriver.setFname(new SerializableSimpleStringProperty(firstName));// adds the driver to the list of available drivers
                storedDriver.setLname(new SerializableSimpleStringProperty(lastName));
                storedDriver.setCar(new SerializableSimpleStringProperty(car));
                storedDriver.setAge(new SerializableSimpleIntegerProperty(age));
                storedDriver.setTeam(new SerializableSimpleStringProperty(team));
                storedDriver.setPoints(new SerializableSimpleIntegerProperty(points));
                writeToFileChampionshipData(drivers);//saves the updated changes into the championshipData

                successLabel.setOpacity(1.0f);
                successLabel.setText(("Successfully updated "+firstName));
                successLabel.setTextFill(Color.rgb(47, 130, 73));
                successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
            }else {
                successLabel.setOpacity(1.0f);
                successLabel.setText(("Error: "+firstName+" ,already exists!"));
                successLabel.setTextFill(Color.rgb(117, 29, 29));
                successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
            }

            fnameInput.setText("");
            lnameInput.setText("");
            ageInput.setText("");
            teamInput.setText("");
            carInput.setText("");
            pointsInput.setText("");

        }
    }

    public void onSRRButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-SRR-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Driver Registration");
        stage.show();
        stage.setResizable(false);
    }

    public void onSTFButtonClicked() throws IOException {
        successLabel.setOpacity(0.0f);
        successLabel.setText(("Files have been saved"));
        successLabel.setTextFill(Color.rgb(47, 130, 73));
        successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
        successLabel.setOpacity(1.0f);
    }
    public void onRFFButtonClicked() throws IOException {
        successLabel.setOpacity(0.0f);
        successLabel.setText(("Files have been successfully loaded"));
        successLabel.setTextFill(Color.rgb(47, 130, 73));
        successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
        successLabel.setOpacity(1.0f);
    }
//    public void onRFFButtonClicked() throws IOException, ClassNotFoundException {
//        drivers = readFromFileChampionshipData("championsipData.txt");
////        races= readFromFileRaceData("Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\raceData.txt");
//    }

    public void onVCTButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {

        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-VCT-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Championship standings");
        stage.show();
        stage.setResizable(false);


    }
    public void onRefreshButtonVCTWindowClicked() throws IOException, ClassNotFoundException { //This is to populate the fields of table view with the realvent data type
        drivers = readFromFileChampionshipData();
        refreshButtonVCTWindow.setOpacity(0.0f);//once clicked the refresh button dissapears
        firstnameColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,String>("Fname"));// this sets the value which is stored under the fname property of the drivers object
        lastnameColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,String>("Lname"));
        ageColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("Age"));
        teamColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,String>("Team"));
        carColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,String>("Car"));
        pointsColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,Integer>("Points"));

        ArrayList<Driver> sortedDrivers =sortChampionshipData(drivers);// this calls the function sort championhip data, and stores it as a new sorted list
        ObservableList<Driver> championshipDataObject = FXCollections.observableArrayList(); //then we create a championshipObject which is an object of Observalbe list that stores the detials of each Driver, we use Observable list as it supports any listners to use the elements in it, which and Arraylist cannot perform.
        for (Driver individualDriver : sortedDrivers) {
            championshipDataObject.add(individualDriver);//adds each from the list of drivers which has been sorted in descending order of points.
        }
        championshipDataView.setItems(championshipDataObject);//once the list is ready we populate the tableview by calling the setItems which displays our observable list Object.

    }
    private ArrayList<Driver> sortChampionshipData(ArrayList<Driver> drivers) {
        // Function to sort the drivers in decreasing order of points
        //this would nbe run when the VCT function is being called
        for (int i = 0; i < drivers.size(); i++) {
            for (int j = 0; j < drivers.size() - 1; j++) {
                Driver currentDriver = drivers.get(j);
                Driver nextDriver = drivers.get(j + 1);
                if (currentDriver.getPoints() < nextDriver.getPoints()) {
                    Driver temp = currentDriver;
                    drivers.set(j, nextDriver);
                    drivers.set(j + 1, temp);
                }
            }
        }
        return drivers;//returns the sorted list
    }

    private void writeToFileChampionshipData(ArrayList<Driver> drivers) throws IOException {
        File ChampionshipFileData = new File(championshipDataFilePath);//opens the file from the locaion given
        FileOutputStream fileOutputStream = new FileOutputStream(ChampionshipFileData);// fileoutput stream is used to write the data to the file
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);// this will serialize the data which is going to be written
        objectOutputStream.writeObject(drivers); // write the object with all its attributes so the data is not lost
        objectOutputStream.close();//closes all opened files to eliminate the possibility of the program to crash
        fileOutputStream.close();
    }
    private void writeToFileRaceData(ArrayList<Race> races) throws IOException {
        File ChampionshipFileData = new File(raceDataFilePath);//same for the race data
        FileOutputStream fileOutputStream = new FileOutputStream(ChampionshipFileData);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(races);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public  ArrayList<Driver> readFromFileChampionshipData() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(championshipDataFilePath); //takes in the filepath as input and returns it as an array list
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);//reads the objects in the file
        ArrayList<Driver> drivers = (ArrayList<Driver>) objectInputStream.readObject();//converts objects to an array list of that type
        return drivers;
    }

    public  ArrayList<Race> readFromFileRaceData() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(raceDataFilePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Race> races = (ArrayList<Race>) objectInputStream.readObject();
        fileInputStream.close();
        return races;
    }

    public  String toTitleCase(String str){ // to convert a string to title case
        StringBuilder stringBuilder = new StringBuilder( str.length() );// creates a new instance of stringbuilder class
        char[] characters = str.toLowerCase().toCharArray();//converts the given string to list of characters in lowercase
        characters[0] = Character.toUpperCase(characters[0]); // makes the first letter/character in uppercase
        stringBuilder.append( new String(characters));// using stringbuilder's method to convert the list of characters to a string
        return stringBuilder.toString(); // converts the stringbuilder object to a string

        // reference: https://www.javacodeexamples.com/convert-string-to-title-case-in-java-example/641
    }




}

