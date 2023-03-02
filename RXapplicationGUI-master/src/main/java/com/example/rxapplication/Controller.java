package com.example.rxapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import org.controlsfx.control.textfield.TextFields;

import java.io.*;
import java.util.*;
import java.lang.*;

//THS Contains all the buttons for the Program
public class Controller {
    // Declaring all the variables that would be used from the FXML application.
    // sets it to private as it can be accessed only from inside this code

    //ROOT window elements, this will change as we switch between scenes
    public Stage stage;
    public Scene scene;
    public Parent root;

    //main screen elements
    @FXML
    private Button refreshButton;
    @FXML
    private Label lrhPos1Label;
    @FXML
    private Label lrhName1Label;
    @FXML
    private Label lrhCar1Label;
    @FXML
    private Label lrhPoints1Label;
    @FXML
    private Label lrhPos2Label;
    @FXML
    private Label lrhName2Label;
    @FXML
    private Label lrhCar2Label;
    @FXML
    private Label lrhPoints2Label;
    @FXML
    private Label lrhPos3Label;
    @FXML
    private Label lrhName3Label;
    @FXML
    private Label lrhCar3Label;
    @FXML
    private Label lrhPoints3Label;
    @FXML
    public Label lrhLocationLabel;
    @FXML
    private Label lrhDateLabel;
    @FXML
    private Label firstPlaceDriverFname;
    @FXML
    private Label firstPlaceDriverLname;
    @FXML
    private Label secondPlaceDriverFname;
    @FXML
    private Label secondPlaceDriverLname;
    @FXML
    private Label firstPlaceDriverWins;
    @FXML
    private Label firstPlaceDriverTop3;
    @FXML
    private Label secondPlaceDriverTop3;
    @FXML
    private Label secondPlaceDriverWins;


    //ADD window elements
    @FXML
    public Label successLabel;
    @FXML
    public TextField fnameInput;
    @FXML
    private TextField lnameInput;
    @FXML
    private TextField ageInput;
    @FXML
    private TextField carInput;
    @FXML
    private TextField teamInput;
    @FXML
    private TextField pointsInput;
    @FXML
    private Label fnameMessage;
    @FXML
    private Label lnameMessage;
    @FXML
    private Label ageMessage;
    @FXML
    private Label teamMessage;
    @FXML
    private Label carMessage;
    @FXML
    private Label pointsMessage;
    @FXML
    private Label addMessageStatus;

    //UDD ELEMENTS
    @FXML
    private TextField nameInputofDriverToBeUpdated;
    @FXML
    private AnchorPane updateFieldsPane;

    @FXML
    private TableView<Driver> table;
    @FXML
    private TableColumn<Driver,String> fname;
    @FXML
    private TableColumn<Driver,String> lname;
    @FXML
    private TableColumn<Driver,Integer> age;
    @FXML
    private TableColumn<Driver,String> team;
    @FXML
    private TableColumn<Driver,String> car;
    @FXML
    private TableColumn<Driver,Integer> points;

    ArrayList<Driver> drivers = new ArrayList<Driver>(2); //Initially creates an empty ArrayList of the relavent data types to save its specific object types
    ArrayList<Race> races = new ArrayList<Race>(2);
    String championshipDataFilePath="Z:\\ProgrammingCW\\RXapplicationGUI-master\\src\\main\\java\\com\\example\\rxapplication\\championshipData.txt"; // the path of the file is being stored as variable
    String raceDataFilePath="Z:\\ProgrammingCW\\RXapplicationGUI-master\\src\\main\\java\\com\\example\\rxapplication\\raceData.txt"; // the path of the file is being stored in the variable

    @FXML
    public void loadMainScreen(ActionEvent event) throws IOException { //this loads the main screen
        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-main.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("World RX Championship Management System");
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }
    public void loadRastRaceData() throws IOException, ClassNotFoundException {
        refreshButton.setOpacity(0.0f);
        races = readFromFileRaceData(); // reads the drivers data to the system
        Race race = races.get(races.size()-1); //loads the last race..change to the sorted one later
        String raceLocation = race.getLocation();
        String raceDate = race.getDate();

        lrhLocationLabel.setText(raceLocation.toUpperCase());
        lrhDateLabel.setText(raceDate);
        Driver firstPLaceDriverResult = race.getDrivers().get(0);
        Driver secondPLaceDriverResult = race.getDrivers().get(1);
        Driver thirdPLaceDriverResult = race.getDrivers().get(2);

        lrhPos1Label.setText("1");
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

        drivers = readFromFileChampionshipData();
        ArrayList<Driver> sortedDriver = vctFunction(drivers);
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

        //🟢🟡🔴STF FUNCTION - Store in serialised text files - serialised⬇️
        /*
        writeToFileChampionshipData(drivers,"Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\championshipData.txt");
        writeToFileRaceData(Races,"Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\championshipData.txt");
        */

        //🟢🟡🔴RFF FUNCTION - Reading from file to a seperate list of Driver and race datatypes - deserialized⬇️
        /*
        ArrayList<Driver> driverData = readFromFileChampionshipData("Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\championshipData.txt");
        ArrayList<Race> raceData = readFromFileRaceData("Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\raceData.txt");
        */

        //🟢🟡🔴VCT FUNCTION - SORTING POINTS DESCENDING ORDER⬇️
        /* for (int i = 1 ; i<driverData.size(); i++) {
            for (int j = 1 ; j<driverData.size()-1; j++) {
                Driver currentDriver = driverData.get(j);
                Driver nextDriver = driverData.get(j+1);
                if (currentDriver.getPoints()< nextDriver.getPoints()){
                    Driver temp = currentDriver;
                    driverData.set(j, nextDriver);
                    driverData.set(j+1,temp);
                }
            }
        }
        */

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

        System.out.println(" ");
        for (Driver individualDriver : drivers) {
            System.out.println(String.format("%s   %s    %d  %s  %s      %d",individualDriver.getFname(),individualDriver.getLname(), individualDriver.getAge(), individualDriver.getTeam(), individualDriver.getCar(), individualDriver.getPoints()));
        }
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
    public void refershDriverNames() throws IOException, ClassNotFoundException {
        drivers = readFromFileChampionshipData();
        String[] possibleDriverNames = new String[drivers.size()];

        int index =0;
        for (Driver driver:drivers){
            String driverName = driver.getFname()+" "+driver.getLname();
            possibleDriverNames[index] = driverName;
            index++;
        }

        TextFields.bindAutoCompletion(nameInputofDriverToBeUpdated,possibleDriverNames); //https://www.youtube.com/watch?v=SkXYg3M0hOQ&ab_channel=CoolITHelp

    }
    public void findDriverToBeUpdated() throws IOException, ClassNotFoundException {
        drivers=readFromFileChampionshipData();
        boolean found = false;
        String nameOfDriverToBeUpdated =nameInputofDriverToBeUpdated.getText().toString();

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
                storedDriver.setFname(firstName);// adds the driver to the list of available drivers
                storedDriver.setLname(lastName);
                storedDriver.setAge(age);
                storedDriver.setTeam(team);
                storedDriver.setPoints(points);
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

//    public void onSRRButtonClicked() throws IOException {
//        Date date= new Date("05/03/2004");
//        Race race =new Race(date,"Riga",drivers);
//        ArrayList<Driver> drivers = race.getDrivers();
//        System.out.println((drivers.get(0)).getFname());
//        System.out.println((drivers.get(1)).getFname());
//        System.out.println((drivers.get(2)).getFname());
//        onSTFButtonClicked();
//
//    }

//    public void onSTFButtonClicked() throws IOException {
//        writeToFileChampionshipData(drivers,"Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\championshipData.txt");
////        writeToFileRaceData(races,"Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\championshipData.txt");
//    }
//    public void onRFFButtonClicked() throws IOException, ClassNotFoundException {
//        drivers = readFromFileChampionshipData("championsipData.txt");
////        races= readFromFileRaceData("Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\raceData.txt");
//    }
//
    public void onVCTButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException  {
        System.out.println("WORK IN PROGRESS");
        ////        opens a new window to display results
//        root = FXMLLoader.load(getClass().getResource("Rx-application-VCT-view.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        ArrayList<Driver> drivers = readFromFileChampionshipData("Z:\\ProgrammingCW\\RXapplication\\src\\main\\java\\com\\example\\rxapplication\\championshipData.txt");
//
////        TableView tbv = new TableView();
////        // Create two columns
////        TableColumn<String, Driver> cl1 = new TableColumn<>("First name");
////        cl1.setCellValueFactory(new PropertyValueFactory<>("fname"));
////        TableColumn<String, Driver> cl2 = new TableColumn<>("Last name");
////        cl2.setCellValueFactory(new PropertyValueFactory<>("lname"));
////        TableColumn<Integer, Driver> cl3 = new TableColumn<>("Age");
////        cl3.setCellValueFactory(new PropertyValueFactory<>("age"));
////        TableColumn<String, Driver> cl4 = new TableColumn<>("Team");
////        cl4.setCellValueFactory(new PropertyValueFactory<>("team"));
////        TableColumn<String, Driver> cl5 = new TableColumn<>("Car");
////        cl5.setCellValueFactory(new PropertyValueFactory<>("car"));
////        TableColumn<Integer, Driver> cl6 = new TableColumn<>("Points");
////        cl6.setCellValueFactory(new PropertyValueFactory<>("points"));
////        // Add two columns into TableView
////        tbv.getColumns().add(cl1);
////        tbv.getColumns().add(cl2);
////        tbv.getColumns().add(cl3);
////        tbv.getColumns().add(cl4);
////        tbv.getColumns().add(cl5);
////        tbv.getColumns().add(cl6);
//        ArrayList<Driver> sortedDrivers = vctFunction(drivers);
//        fname.setCellValueFactory(new PropertyValueFactory<Driver,String>("fname"));
//        lname.setCellValueFactory(new PropertyValueFactory<Driver,String>("lname"));
//        age.setCellValueFactory(new PropertyValueFactory<Driver,Integer>("age"));
//        team.setCellValueFactory(new PropertyValueFactory<Driver,String>("team"));
//        car.setCellValueFactory(new PropertyValueFactory<Driver,String>("car"));
//        points.setCellValueFactory(new PropertyValueFactory<Driver,Integer>("points"));
//        ObservableList<Driver> list = (ObservableList) sortedDrivers;
//        table.setItems(list);
//
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    private ArrayList<Driver> vctFunction(ArrayList<Driver> drivers) {
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

