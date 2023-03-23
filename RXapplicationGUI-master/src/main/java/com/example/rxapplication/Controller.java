package com.example.rxapplication;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;

//All functions, controlls and every single thing of the application runs from here.
public class Controller {
    // Declaring all the variables that would be used from the FXML application.
    // sets it to private as it can be accessed only from inside this code

    //ROOT window elements, this will change as we switch between scenes(the backbone of the GUI, everything depends on these 2)
    private Stage stage;
    private Scene scene;

    @FXML private VBox optionsPane;

    //SIGN IN screen
    @FXML private TextField emailInput;
    @FXML private PasswordField passwordInput;
    @FXML private Button signInButton,continueToApp;
    @FXML private Label emailInputMessage,passwordInputMessage;
    //main screen elements(Home page)
    @FXML private Button refreshButton,viewRaceDataButton;//all the labels in the mainScreen are stored here, each an every cell in the Last race Table. Last Race Highlights is an improvement to program, and also Top2 head to head.
    @FXML private Label lrhPos1Label,lrhName1Label,lrhCar1Label,lrhPoints1Label,lrhPos2Label,lrhName2Label,lrhCar2Label,lrhPoints2Label,lrhPos3Label,lrhName3Label,lrhCar3Label,lrhPoints3Label,lrhLocationLabel,lrhDateLabel,firstPlaceDriverFname,firstPlaceDriverLname,secondPlaceDriverFname,secondPlaceDriverLname,firstPlaceDriverWins,firstPlaceDriverTop3,secondPlaceDriverTop3,secondPlaceDriverWins;

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
    @FXML private TableColumn<Driver,String> firstnameColumnChampionshipData,teamColumnChampionshipData,carColumnChampionshipData; //each and every column and the data type it will store
    @FXML private TableColumn<Driver,Integer> ageColumnChampionshipData,pointsColumnChampionshipData;

    //SRR window elements
    @FXML private Button simulateRaceButtonClicked;
    @FXML private AnchorPane raceInfoPane;
    @FXML private ImageView raceLocationFlag;
    @FXML private Label raceLocationLabel,raceDateLabel,srrLapByLap0,srrLapByLap1,srrLapByLap2,srrLapByLap3,srrLapByLap4,srrLapByLap5,srrLapByLap6,srrLapByLap7,srrLapByLap8,srrLapByLap9,srrLapByLap10,srrLapByLap11,srrLapByLap12,srrLapByLap13,srrLapByLap14,windowHeading1,windowHeading2;


    //VRL window elements
    @FXML private Button refreshButtonVRLWindow;
    @FXML private TableView<RaceResult> raceDataView;
    @FXML private TableColumn<RaceResult, String> dateColumnRaceData,locationColumnRaceData,nameColumnRaceData;
    @FXML private TableColumn<RaceResult, Integer> pointsColumnRaceData;

    ArrayList<Driver> drivers = new ArrayList<>(2); //Initially creates an empty ArrayList of the relavent data types to save its specific object types
    ArrayList<Race> races = new ArrayList<>(2);
    ArrayList<Admin> admins = new ArrayList<>(2);

    String championshipDataFilePath="RXapplicationGUI-master/src/main/resources/com/example/files/championshipData.txt"; // the path of the file is being stored as variable
    String raceDataFilePath="RXapplicationGUI-master/src/main/resources/com/example/files/raceData.txt"; // the path of the file is being stored in the variable
    String adminDataFilePath="RXapplicationGUI-master/src/main/resources/com/example/files/adminData.txt";
    //Use this after completing the project
//    String championshipDataFilePath="src/main/resources/com/example/files/championshipData.txt"; // the path of the file is being stored as variable
//    String raceDataFilePath="src/main/resources/com/example/files/raceData.txt"; // the path of the file is being stored in the variable
//    String adminDataFilePath="src/main/resources/com/example/files/adminData.txt";
    Random random = new Random();
    int loginAttempts =0;



    public void loadSignInScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signIn-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("SignIn");
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());//refers the main stylesheet
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);//now the Home page is open
    }
    public void validateFields() throws IOException, ClassNotFoundException {
        admins=readFromFileAdminData();
        String emailEntered = emailInput.getText();
        String adminFullName;
        boolean loginSuccessfull = false;
        boolean fieldEmpty = false;
        if (emailInput.getText().equals("")) {//checks if the first name input field is blank
            emailInputMessage.setText("Cannot be empty");//displays a message to the user to re-enter;
            fieldEmpty=true;
        }else {
            emailInputMessage.setText("");
        }
        if(passwordInput.getText().equals("")){
            passwordInputMessage.setText("Cannot be empty");
            fieldEmpty=true;
        }else{
            passwordInputMessage.setText("");
        }

        for (Admin admin:admins){
            if (admin.getEmail().equals(emailEntered)){
                if (admin.getPassword().equals(passwordInput.getText())){
                    signInButton.setOpacity(0.0f);
                    continueToApp.setOpacity(1.0f);
                    successLabel.setOpacity(1.0f);
                    successLabel.setText("Welcome back, "+admin.getFname()+"!");
                    successLabel.setTextFill(Color.rgb(47, 130, 73));
                    successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
                    loginSuccessfull=true;
                    fieldEmpty=false;
                    adminFullName = admin.getDetails();
                    System.out.println(opertionTime()+" "+adminFullName+" : Logged in.");
                    passwordInputMessage.setText("");
                    break;
                }
            }
        }


        if (!loginSuccessfull && !fieldEmpty){
            successLabel.setOpacity(0.0f);
            passwordInputMessage.setText("Invalid Username or password,try again");
            passwordInput.clear();
            emailInput.clear();
            loginAttempts++;
        }

        if (loginAttempts==3){
            System.out.println(opertionTime()+" 3 attempts are unsuccessful, try again later");
            System.exit(0);
        }

        writeToFileAdminData(admins);
    }

    public void onUserIconClicked(){
        optionsPane.setOpacity(1.0f);
    }
    public void onClearOptionsButtonClicked(){
        optionsPane.setOpacity(0.0f);
    }
    //Main Window Methods
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
        drivers = readFromFileChampionshipData();
        races = readFromFileRaceData(); // loads the racedata to an ArrayList

        refreshButton.setOpacity(0.0f);//once the refresh button is clicked it will be disspeared as it displays all the last race data, from the VRL function(gets the most recent race)
        viewRaceDataButton.setOpacity(1.0f);

        ArrayList<Race> sortedRaces = sortRaceData(races);
        Race race = sortedRaces.get(races.size()-1); //loads the last race..change to the sorted one later
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


        ArrayList<Driver> sortedDriver = sortChampionshipData(drivers);//reads the top 2 drivers
        Driver firstTitleContender = sortedDriver.get(0);
        Driver secondTitleContender = sortedDriver.get(1);
        firstPlaceDriverFname.setText(firstTitleContender.getFname().toUpperCase());//updates the main screen Title contender dashboard
        firstPlaceDriverLname.setText(firstTitleContender.getLname().toUpperCase());
        secondPlaceDriverFname.setText(secondTitleContender.getFname().toUpperCase());
        secondPlaceDriverLname.setText(secondTitleContender.getLname().toUpperCase());
        firstPlaceDriverTop3.setText(String.valueOf(podiumsCalculator(firstTitleContender,races)));//number of podiums between each driver
        secondPlaceDriverTop3.setText(String.valueOf(podiumsCalculator(secondTitleContender,races)));
        firstPlaceDriverWins.setText(String.valueOf(totalWinsCalculator(firstTitleContender,races)));//number of race wins
        secondPlaceDriverWins.setText(String.valueOf(totalWinsCalculator(secondTitleContender,races)));

    }
    public String podiumsCalculator(Driver driver,ArrayList<Race> races){
        int totalPodiums = 0;//this to calculate the number of podiums for any driver
        for (Race race:races){//checks in every single race of the season
            if(driver.getFname().equals(race.getDrivers().get(0).getFname())||driver.getFname().equals(race.getDrivers().get(1).getFname())||driver.getFname().equals(race.getDrivers().get(2).getFname())){
                totalPodiums++;//if the driver name is present in any of the first 3 elements in the race, then it means thats a podium for the driver.
            }
        }
        return String.valueOf(totalPodiums);//returns answer in String
    }

    public String totalWinsCalculator(Driver driver,ArrayList<Race> races){
        int totalWins=0;
        for (Race race:races){
            if(driver.getFname().equals(race.getDrivers().get(0).getFname())){//checks in each race if the driver name is prsent at the first index, it means that the driver has won the race.
                totalWins++;
            }
        }
        return String.valueOf(totalWins);
    }


    //ADD Window Methods
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
    public boolean validateAddDriverDetails(Object fname,Object lname,Object age,Object team,Object car,Object points){

        if (fname.toString().equals("")||lname.toString().equals("")||age.toString().equals("")||team.toString().equals("")||car.toString().equals("")||points.toString().equals("")){
            return false;
        }
        char[] fnameCharacters= fname.toString().toCharArray();// converts the string to a sqeuence of characters
        for (char character: fnameCharacters) {//and by using an enhanced for loop, the program checks whether a number is present in the list of characters
            if (Character.isDigit(character)){// using Character object's in buikt isDigit() methid to check whehter the character is a digit
                return false;
            }
        }
        char[] lnameCharacters= lname.toString().toCharArray();// converts the string to a sqeuence of characters
        for (char character: lnameCharacters) {//and by using an enhanced for loop, the program checks whether a number is present in the list of characters
            if (Character.isDigit(character)){// using Character object's in buikt isDigit() methid to check whehter the character is a digit
                return false;
            }
        }

        char[] ageCharacters = age.toString().toCharArray();// for age we check if there is no digit in the character sequence meaning it is not an integer
        for (char character: ageCharacters) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        char[] pointsCharacters = points.toString().toCharArray();// for age we check if there is no digit in the character sequence meaning it is not an integer
        for (char character: pointsCharacters) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        if (fname.toString().split(" ").length>1 || lname.toString().split(" ").length>1){//checks if more than one name has been entered
            return false;
        }

        return true;
    }
    public void addButtonSignupClicked() throws IOException, ClassNotFoundException { //To save the driver detials into the system
        drivers = readFromFileChampionshipData(); // reads the drivers data to the system

        boolean fnameValid=true;// initally all the input fields are set to be valid
        boolean lnameValid=true;
        boolean ageValid=true;
        boolean teamValid=true;
        boolean carValid=true;
        boolean pointsValid=true;

        if (fnameInput.getText().equals("")){//checks if the first name input field is blank
            fnameMessage.setText("Cannot be empty");//displays a message to the user to re-enter
            fnameValid=false;//sets fname validity to be false
        }else{//checks whether there is atleast one integer in the firstname: since name cannot contain any number/digits
            char[] fnameCharacters= fnameInput.getText().toCharArray();// converts the string to a sqeuence of characters
            for (char character: fnameCharacters) {//and by using an enhanced for loop, the program checks whether a number is present in the list of characters
                if (Character.isDigit(character)){// using Character object's in buikt isDigit() methid to check whehter the character is a digit
                    fnameValid=false;//if it is then fnameInput will be invalid and the loop will break
                    break;
                }
            }
            if (!fnameValid){//if the fname is invalid, a message will be displayed to the user saying its incorrect
                fnameMessage.setText("Incorrect Value");
            }else{
                fnameMessage.setText("");//if it is valid there will be no message dispalyed as the field is of correct data type.

            }
        }

        if (lnameInput.getText().equals("")){//the same procedure to check for correct value entered in lastname field
            lnameMessage.setText("Cannot be empty");
            lnameValid=false;
        }else{
            char[] lnameCharacters= lnameInput.getText().toCharArray();
            for (char character: lnameCharacters) {
                if (Character.isDigit(character)){
                    lnameValid=false;
                    break;
                }
            }
            if (!lnameValid){
                lnameMessage.setText("Incorrect Value");
            }else{
                lnameMessage.setText("");

            }
        }

        if (ageInput.getText().equals("")){
            ageMessage.setText("Cannot be empty");
            ageValid=false;
        }else{
            char[] ageCharacters = ageInput.getText().toCharArray();// for age we check if there is no digit in the character sequence meaning it is not an integer
            for (char character: ageCharacters) {
                if (!Character.isDigit(character)){
                    ageValid=false;//if there is atleast one non integer element the validity will be false
                    break;
                }
            }if (!ageValid){
                ageMessage.setText("Incorrect Type");
            }else{
                ageValid = true;
                ageMessage.setText("");

            }
        }

        if (carInput.getText().equals("")){//checks if the car input field is empty as these inputs are important
            carMessage.setText("Cannot be empty");
            carValid=false;
        }else{
            carMessage.setText("");
        }

        if (teamInput.getText().equals("")){//same procedure as carInput field
            teamMessage.setText("Cannot be empty");
            teamValid=false;
        }else{
            teamMessage.setText("");
        }

        if (pointsInput.getText().equals("")){//validation procedure is same as age as we only check if the data tpe for the field is an integer.
            pointsMessage.setText("Cannot be empty");
            pointsValid=false;
        }else{
            char[] pointsCharacters = pointsInput.getText().toCharArray();
            for (char character: pointsCharacters) {
                if (!Character.isDigit(character)){
                    pointsValid=false;
                    break;
                }
            }if (!pointsValid){
                pointsMessage.setText("Incorrect Type");
            }else{
                pointsMessage.setText("");
            }
        }

        if (fnameValid && lnameValid && ageValid && teamValid && carValid && pointsValid) {// if all are valid
            String firstName = toTitleCase(fnameInput.getText().strip());// we clean all the input as it is ready for the being written to the file
            String lastName = toTitleCase(lnameInput.getText().strip());
            Integer age = Integer.parseInt(ageInput.getText());//convert the string to an integer
            String team = (teamInput.getText().strip());
            String car = (carInput.getText().strip());
            Integer points = Integer.parseInt(pointsInput.getText());
            boolean allFieldsAreValid = validateAddDriverDetails(firstName,lastName,age,team,car,points);
            if (allFieldsAreValid){
                Driver driver = new Driver(firstName, lastName, age, team, car, points);//creates a new driver of the Driver class
                boolean recordExsists = false;
                for (Driver availableDriver:drivers) {
                    if (driver.getFname().equals(availableDriver.getFname())&&driver.getLname().equals(availableDriver.getLname())){
                        recordExsists=true;
                        break;
                    }
                }
                if (!recordExsists){
                    drivers.add(driver);// adds the driver to the list of available drivers

                    writeToFileChampionshipData(drivers);//saves the updated changes into the championshipData
                    System.out.println(opertionTime()+" : added "+driver.getDetails()+".");
                    successLabel.setOpacity(1.0f);
                    successLabel.setText(("Successfully added "+driver.getFname()));
                    RaceCar raceCar;
                    switch (carInput.getText()) {
                        case "PWR RX1e" -> {
                            raceCar = new RaceCar("PWR RX1e", "500kW", driver.getDetails());
                            raceCar.getVehicleDetails();
                            break;
                        }
                        case "Volkswagen RX1e" -> {
                            raceCar = new RaceCar("Volkswagen RX1e", "NA", driver.getDetails());
                            raceCar.getVehicleDetails();
                            break;
                        }
                        case "Lancia Delta Evo-e RX" -> {
                            raceCar = new RaceCar("Lancia Delta Evo-e RX", "158kW", driver.getDetails());
                            raceCar.getVehicleDetails();
                            break;
                        }
                        case "Peugeot 208 RX1e" -> {
                            raceCar = new RaceCar("Peugeot 208 RX1e", " 500kW ", driver.getDetails());
                            raceCar.getVehicleDetails();
                            break;
                        }
                        default -> {
                            raceCar = new RaceCar(car, "NA", driver.getDetails());
                            raceCar.getVehicleDetails();
                            break;
                        }

                    }
                    successLabel.setTextFill(Color.rgb(47, 130, 73));
                    successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
                }else {
                    successLabel.setOpacity(1.0f);
                    successLabel.setText(("Error: "+driver.getFname()+" ,already exists!"));
                    successLabel.setTextFill(Color.rgb(117, 29, 29));
                    successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
                }
            }
            else{
                System.out.println(opertionTime()+" ERROR: Invalid inputs, Try again");
            }
            fnameInput.setText("");
            lnameInput.setText("");
            ageInput.setText("");
            teamInput.setText("");
            carInput.setText("");
            pointsInput.setText("");
        }
    }
    public boolean validateDeletingAndUpdatingDriverDetails(Object name){
        if (name.toString().equals("")){
            return false;
        }
        String[] names = name.toString().split(" ");
        if (names.length==1){
            return false;
        }
        if (names.length>2){
            return false;
        }
        String fname= names[0];
        String lname= names[1];
        char[] fnameCharacters= fname.toString().toCharArray();// converts the string to a sqeuence of characters
        for (char character: fnameCharacters) {//and by using an enhanced for loop, the program checks whether a number is present in the list of characters
            if (Character.isDigit(character)){// using Character object's in buikt isDigit() methid to check whehter the character is a digit
                return false;
            }
        }
        char[] lnameCharacters= lname.toString().toCharArray();// converts the string to a sqeuence of characters
        for (char character: lnameCharacters) {//and by using an enhanced for loop, the program checks whether a number is present in the list of characters
            if (Character.isDigit(character)){// using Character object's in buikt isDigit() methid to check whehter the character is a digit
                return false;
            }
        }
//        boolean found=false;
//        for(Driver driver:drivers){
//            if (driver.getFname().equals(fname)&&driver.getLname().equals(lname)){
//                found=true;
//            }
//        }
//        if (!found){
//            return false;
//        }
        return true;
    }


    //DDD Window Methods
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
        String nameOfDriverToBeDeleted = nameInputofDriver.getText();
        if (validateDeletingAndUpdatingDriverDetails(nameOfDriverToBeDeleted)){
            for (Driver driver:drivers){
                String availableDriverName = driver.getFname()+" "+driver.getLname();
                if (availableDriverName.equals(nameOfDriverToBeDeleted)){
                    found=true;
                    nameInputStored.setText(nameOfDriverToBeDeleted);
                    break;
                }
            }if (found){
                successLabel.setTextFill(Color.rgb(47, 130, 73));
                successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
                successLabel.setText(("Found records of "+nameOfDriverToBeDeleted));
                deletePane.setOpacity(1.0f);

            }else{
                successLabel.setTextFill(Color.rgb(117, 29, 29));
                successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
                successLabel.setOpacity(1.0f);
                if (nameInputofDriver.getText().equals("")) {
                    successLabel.setText(("Cannot be empty."));
                } else {
                    successLabel.setText(("Error: " + nameOfDriverToBeDeleted + ", does not exist!"));
                }

                deletePane.setOpacity(0.0f);
            }
        }else{
            System.out.println(opertionTime()+" ERROR: Please enter a valid name");
            successLabel.setTextFill(Color.rgb(117, 29, 29));
            successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
            successLabel.setOpacity(1.0f);
            if (nameInputofDriver.getText().equals("")) {
                successLabel.setText(("Cannot be empty."));
            } else {
                successLabel.setText(("Error: " + nameOfDriverToBeDeleted + ", does not exist!"));
            }
            deletePane.setOpacity(0.0f);
        }
    }




    public void onDeleteConfirmationClicked() throws IOException, ClassNotFoundException {
        drivers = readFromFileChampionshipData();
        String nameOfDriverToBeDeleted = nameInputStored.getText();

        for (Driver driver:drivers){
            String availableDriverName = driver.getFname()+" "+driver.getLname();
            if (availableDriverName.equals(nameOfDriverToBeDeleted)){
                drivers.remove(driver);// the driver is
                System.out.println(opertionTime()+" : Deleted "+driver.getDetails()+".");
                break;
            }
        }
        writeToFileChampionshipData(drivers);
        successLabel.setOpacity(1.0f);
        successLabel.setText(("Deleted records of "+nameOfDriverToBeDeleted));
        successLabel.setTextFill(Color.rgb(47, 130, 73));
        successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));


    }


    //UDD Window Methods
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

    public void findDriverToBeUpdated() throws IOException, ClassNotFoundException {
        drivers=readFromFileChampionshipData();
        boolean found = false;
        String nameOfDriverToBeUpdated = nameInputofDriver.getText();

        int index = 0;
        if (validateDeletingAndUpdatingDriverDetails(nameOfDriverToBeUpdated)){
            for (Driver driver:drivers){
                String availableDriverName = driver.getFname()+" "+driver.getLname();
                if (availableDriverName.equals(nameOfDriverToBeUpdated)){
                    found=true;
                    break;
                }
                index++;
            }
            if (found){
                successLabel.setTextFill(Color.rgb(47, 130, 73));
                successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
                successLabel.setText(("Found records of "+nameOfDriverToBeUpdated));
                successLabel.setOpacity(1.0f);
                updateFieldsPane.setOpacity(1.0f);
                boolean fnameValid=true;// initally all the input fields are set to be valid
                boolean lnameValid=true;
                boolean ageValid=true;
                boolean teamValid=true;
                boolean carValid=true;
                boolean pointsValid=true;

                if (fnameInput.getText().equals("")){//checks if the first name input field is blank
                    fnameMessage.setText("Cannot be empty");//displays a message to the user to re-enter
                    fnameValid=false;//sets fname validity to be false
                }else{//checks whether there is atleast one integer in the firstname: since name cannot contain any number/digits
                    char[] fnameCharacters= fnameInput.getText().toCharArray();// converts the string to a sqeuence of characters
                    for (char character: fnameCharacters) {//and by using an enhanced for loop, the program checks whether a number is present in the list of characters
                        if (Character.isDigit(character)){// using Character object's in buikt isDigit() methid to check whehter the character is a digit
                            fnameValid=false;//if it is then fnameInput will be invalid and the loop will break
                            break;
                        }
                    }
                    if (!fnameValid){//if the fname is invalid, a message will be displayed to the user saying its incorrect
                        fnameMessage.setText("Incorrect Value");
                    }else{
                        fnameMessage.setText("");//if it is valid there will be no message dispalyed as the field is of correct data type.

                    }
                }

                if (lnameInput.getText().equals("")){//the same procedure to check for correct value entered in lastname field
                    lnameMessage.setText("Cannot be empty");
                    lnameValid=false;
                }else{
                    char[] lnameCharacters= lnameInput.getText().toCharArray();
                    for (char character: lnameCharacters) {
                        if (Character.isDigit(character)){
                            lnameValid=false;
                            break;
                        }
                    }
                    if (!lnameValid){
                        lnameMessage.setText("Incorrect Value");
                    }else{
                        lnameMessage.setText("");

                    }
                }

                if (ageInput.getText().equals("")){
                    ageMessage.setText("Cannot be empty");
                    ageValid=false;
                }else{
                    char[] ageCharacters = ageInput.getText().toCharArray();// for age we check if there is no digit in the character sequence meaning it is not an integer
                    for (char character: ageCharacters) {
                        if (!Character.isDigit(character)){
                            ageValid=false;//if there is atleast one non integer element the validity will be false
                            break;
                        }
                    }if (!ageValid){
                        ageMessage.setText("Incorrect Type");
                    }else{
                        ageValid = true;
                        ageMessage.setText("");

                    }
                }

                if (carInput.getText().equals("")){//checks if the car input field is empty as these inputs are important
                    carMessage.setText("Cannot be empty");
                    carValid=false;
                }else{
                    carMessage.setText("");
                }

                if (teamInput.getText().equals("")){//same procedure as carInput field
                    teamMessage.setText("Cannot be empty");
                    teamValid=false;
                }else{
                    teamMessage.setText("");
                }

                if (pointsInput.getText().equals("")){//validation procedure is same as age as we only check if the data tpe for the field is an integer.
                    pointsMessage.setText("Cannot be empty");
                    pointsValid=false;
                }else{
                    char[] pointsCharacters = pointsInput.getText().toCharArray();
                    for (char character: pointsCharacters) {
                        if (!Character.isDigit(character)){
                            pointsValid=false;
                            break;
                        }
                    }if (!pointsValid){
                        pointsMessage.setText("Incorrect Type");
                    }else{
                        pointsMessage.setText("");
                    }
                }

                if (fnameValid && lnameValid && ageValid && teamValid && carValid && pointsValid) {// if all are valid
                    String firstName = toTitleCase(fnameInput.getText().strip());// we clean all the input as it is ready for the being written to the file
                    String lastName = toTitleCase(lnameInput.getText().strip());
                    Integer age = Integer.parseInt(ageInput.getText());//convert the string to an integer
                    String team = (teamInput.getText().strip());
                    String car = (carInput.getText().strip());
                    Integer points = Integer.parseInt(pointsInput.getText());

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
                    if (!recordExsists){
                        Driver storedDriver = drivers.get(index);
                        storedDriver.setFname(new SerializableSimpleStringProperty(firstName));// adds the driver to the list of available drivers
                        storedDriver.setLname(new SerializableSimpleStringProperty(lastName));
                        storedDriver.setCar(new SerializableSimpleStringProperty(car));
                        storedDriver.setAge(new SerializableSimpleIntegerProperty(age));
                        storedDriver.setTeam(new SerializableSimpleStringProperty(team));
                        storedDriver.setPoints(new SerializableSimpleIntegerProperty(points));
                        writeToFileChampionshipData(drivers);//saves the updated changes into the championshipData
                        System.out.println(opertionTime()+" : Updated records of "+storedDriver.getDetails()+".");


                        successLabel.setTextFill(Color.rgb(47, 130, 73));
                        successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
                        successLabel.setOpacity(1.0f);
                        successLabel.setText(("Successfully updated "+firstName));
                    }else {
                        successLabel.setTextFill(Color.rgb(117, 29, 29));
                        successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
                        successLabel.setOpacity(1.0f);
                        successLabel.setText(("Error: "+firstName+" ,already exists!"));
                    }
                    fnameInput.setText("");//clears all the inputs,after updating
                    lnameInput.setText("");
                    ageInput.setText("");
                    teamInput.setText("");
                    carInput.setText("");
                    pointsInput.setText("");
                }
            }
        }else{
            System.out.println(opertionTime()+" ERROR: Please enter a valid name");
            successLabel.setTextFill(Color.rgb(117, 29, 29));
            successLabel.setBackground(Background.fill(Color.rgb(245, 110, 110)));
            successLabel.setOpacity(1.0f);
            if (nameInputofDriver.getText().equals("")) {
                successLabel.setText(("Cannot be empty."));
            } else {
                successLabel.setText(("Error: " + nameOfDriverToBeUpdated + ", does not exist!"));
            }
            updateFieldsPane.setOpacity(0.0f);
        }
    }


    //SRR Window Methods
    public void onSRRButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-SRR-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Simulate Random Race");
        stage.show();
        stage.setResizable(false);
    }
    public void startSimulationClicked() throws IOException, ClassNotFoundException, InterruptedException {
        drivers=readFromFileChampionshipData();//loads the data from both files
        races=readFromFileRaceData();

        simulateRaceButtonClicked.setOpacity(0.0f);//once clicked to sim Button dissapears
        windowHeading1.setOpacity(1.0f);//these are the GUI labels, to give a lap by lap update to the user
        windowHeading2.setOpacity(1.0f);
        srrLapByLap0.setOpacity(1.0f);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {//using the timeline object where theres a one second delay after each command is run
            srrLapByLap1.setOpacity(1.0f);
            srrLapByLap2.setOpacity(1.0f);
        }));
        timeline.play();

        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {// this is used to delay the labels being appeard on the screen,
            srrLapByLap3.setOpacity(1.0f);
            srrLapByLap4.setOpacity(1.0f);
        }));
        timeline.play();
        String[] possibleLocations = {"Nyirád","Höljes","Montalegre","Barcelona","Rīga","Norway"};
        int randomLocationIndex = random.nextInt(possibleLocations.length); //generate a random index from 0 until the last index of the list - https://www.geeksforgeeks.org/generating-random-numbers-in-java/
        String raceLocation = possibleLocations[randomLocationIndex];
        String raceDate = randomDate();
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            raceInfoPane.setOpacity(1.0f);
        }));
        timeline.play();
        switch (raceLocation){
            case "Nyirád":
                Image image = new Image(getClass().getResourceAsStream("/com/example/images/hungaryFlag.jpg"));
                raceLocationFlag.setImage(image);//the image displayed on the screen varies betwqeeen the race location, it displas the flag,racelocation and the date
                break;
            case "Höljes":
                raceLocationFlag.setImage(new Image(getClass().getResourceAsStream("/com/example/images/holjesFlag.png")));
                break;
            case "Montalegre":
                raceLocationFlag.setImage(new Image(getClass().getResourceAsStream("/com/example/images/montalegreFlag.png")));
                break;
            case "Barcelona":
                raceLocationFlag.setImage(new Image(getClass().getResourceAsStream("/com/example/images/barcelonaFlag.png")));
                break;
            case "Rīga":
                raceLocationFlag.setImage(new Image(getClass().getResourceAsStream("/com/example/images/rigaFlag.jpg")));
                break;
            case "Norway":
                raceLocationFlag.setImage(new Image(getClass().getResourceAsStream("/com/example/images/norwayFlag.png")));
                break;
        }

        raceLocationLabel.setText(raceLocation);

        System.out.println(opertionTime()+" : Is simulating a race.");

        ArrayList<Driver> finalPositions = drivers;
        Collections.shuffle(finalPositions);//simulates positions randomly using - shuffle

        ArrayList<String> previousRaceDates = new ArrayList<>();//to store all the exsisting raceDates.
        int repeatingDates = 0;//at start there are no same dates
        for (Race previousRace : races){
            String previousRaceDate = previousRace.getDate(); //gets the date which are available
            repeatingDates++;//once entered the occurence increaments by one, as there is one instance of that date, which is itself.
            if (repeatingDates==1){
                previousRaceDates.add(previousRaceDate);//adds the date to the list which contains all the dates that had races, so new races cannot use the same raceDate
                repeatingDates=0;
            }
        }
        boolean dateExists = true;//initially we say that the generated date is available in the list of exsisiting dates.
        while (!dateExists){//if date exsists is not true, it means we can use this date
            for (String date: previousRaceDates){//if we iterate in the list to check if the date is exsisitng
                if (raceDate ==date){
                    dateExists=true;
                    raceDate = randomDate();//if the date is already taken we generate a new raceDate,until this new date is not in the list of exsisting dates.
                }
            }
        }
        raceDateLabel.setText(raceDate);//after the race is started. user will be updated avout the race
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            srrLapByLap5.setOpacity(1.0f);
            srrLapByLap6.setOpacity(1.0f);
        }));
        timeline.play();
        timeline = new Timeline(new KeyFrame(Duration.seconds(7), event -> {
            srrLapByLap7.setOpacity(1.0f);
            srrLapByLap8.setOpacity(1.0f);
        }));
        timeline.play();
        timeline = new Timeline(new KeyFrame(Duration.seconds(9), event -> {
            srrLapByLap9.setOpacity(1.0f);
            srrLapByLap10.setOpacity(1.0f);
        }));
        timeline.play();//.play() used to display the given timeline
        timeline = new Timeline(new KeyFrame(Duration.seconds(11), event -> {
            srrLapByLap11.setOpacity(1.0f);
            srrLapByLap12.setOpacity(1.0f);
        }));
        timeline.play();

        timeline = new Timeline(new KeyFrame(Duration.seconds(13), event -> {
            srrLapByLap13.setOpacity(1.0f);
            srrLapByLap14.setOpacity(1.0f);
        }));
        timeline.play();
        timeline = new Timeline(new KeyFrame(Duration.seconds(15), event -> {//after race session is complete user will be notified.
            successLabel.setOpacity(1.0f);
            successLabel.setText(("Simulation complete"));
            successLabel.setTextFill(Color.rgb(47, 130, 73));
            successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
        }));
        timeline.play();
        System.out.println(opertionTime()+" : Simulated a race at "+raceLocation+" "+raceDate);

        Race race = new Race(raceDate,raceLocation,finalPositions);//then we create an object of Race class, which stores the date,location and finalPositions after the race.
        races.add(race);//this adds to the list of exsisting races.
        SafetyCar safetyCar = new SafetyCar("Audi a6 avant","245 kW",2023);//This displays the safteyCar details for the race.
        safetyCar.getVehicleDetails();

        //now we add the points to the top3 drivers
        Driver firstPlaceDriver = finalPositions.get(0);//loads the details of the first placed driver
        Driver secondPlaceDriver = finalPositions.get(1);
        Driver thirdPlaceDriver = finalPositions.get(2);//until the third place driver
        for(Driver driver:drivers){//gives points for each driver
            if (driver.equals(firstPlaceDriver)){//if first place 10 points are being added
                int currentPoints = driver.getPoints();
                driver.setPoints(new SerializableSimpleIntegerProperty(currentPoints+10));
            }else if (driver.equals(secondPlaceDriver)){//7 points
                int currentPoints = driver.getPoints();
                driver.setPoints(new SerializableSimpleIntegerProperty(currentPoints+7));
            }else if (driver.equals(thirdPlaceDriver)){
                int currentPoints = driver.getPoints();//3rd place 5 points
                driver.setPoints(new SerializableSimpleIntegerProperty(currentPoints+5));
            }else {
                int currentPoints = driver.getPoints();//0points for 4th and below
                driver.setPoints(new SerializableSimpleIntegerProperty(currentPoints));
            }
        }
        System.out.println(opertionTime()+" : Points updated");

        writeToFileRaceData(races);//updates both of the lists, saves the values into the textfile
        writeToFileChampionshipData(drivers);
        System.out.println(opertionTime()+" : Autosave complete ");

    }
    public String randomDate(){
        int month = random.nextInt(12-1+1)+1;//random month
        int day;
        switch (month){
            case 4,6,9,10:// if its one of these months such as april, there will be only 30 days
                day = random.nextInt(30-1+1)+1;
                break;
            case 2:// if its february then it will be 28 days
                day = random.nextInt(28-1+1)+1;
                break;
            default:
                day = random.nextInt(31-1+1)+1;
        }
        String date = ((day)+"/"+(month)+"/23");//creates a String of Date format
        return date;
    }


    //VCT Window Methods
    public void onVCTButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {

        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-VCT-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Championship standings");
        stage.show();
        stage.setResizable(false);
        System.out.println(opertionTime()+" : Sorted data according to descending order of points ");
    }
    public void onRefreshButtonVCTWindowClicked() throws IOException, ClassNotFoundException { //This is to populate the fields of table view with the realvent data type
        drivers = readFromFileChampionshipData();
        refreshButtonVCTWindow.setOpacity(0.0f);//once clicked the refresh button dissapears
        championshipDataView.setOpacity(1.0f);
        firstnameColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,String>("Details"));// this sets the value which is stored under the fname property of the drivers object
        //lastnameColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,String>("Details"));
        ageColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("Age"));
        teamColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,String>("Team"));
        carColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,String>("Car"));
        pointsColumnChampionshipData.setCellValueFactory(new PropertyValueFactory<Driver,Integer>("Points"));

        ArrayList<Driver> sortedDrivers =sortChampionshipData(drivers);// this calls the function sort championhip data, and stores it as a new sorted list
        ObservableList<Driver> championshipDataObject = FXCollections.observableArrayList(); //then we create a championshipObject which is an object of Observalbe list that stores the detials of each Driver, we use Observable list as it supports any listners to use the elements in it, which and Arraylist cannot perform.
        for (Driver individualDriver : sortedDrivers) {
            championshipDataObject.add(individualDriver);//adds each from the list of drivers which has been sorted in descending order of points.
        }
        System.out.println(opertionTime()+" : Viewed Championship table ");
        championshipDataView.setItems(championshipDataObject);//once the list is ready we populate the tableview by calling the setItems which displays our observable list Object.

    }
    private ArrayList<Driver> sortChampionshipData(ArrayList<Driver> drivers) {
        // Function to sort the drivers in decreasing order of points
        //this would nbe run when the VCT function is being called
        for (int outerLoop = 0; outerLoop < drivers.size(); outerLoop++) {
            for (int innerLoop = 0; innerLoop < drivers.size() - 1; innerLoop++) {
                Driver currentDriver = drivers.get(innerLoop);
                Driver nextDriver = drivers.get(innerLoop + 1);
                if (currentDriver.getPoints() < nextDriver.getPoints()) {
                    Driver temp = currentDriver;
                    drivers.set(innerLoop, nextDriver);
                    drivers.set(innerLoop + 1, temp);
                }
            }
        }

        return drivers;//returns the sorted list, implementation from https://www.geeksforgeeks.org/bubble-sort/
    }

    
    //VRL WINDOW METHODS
    public void onVRLButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("Rx-application-VRL-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Championship standings");
        stage.show();
        stage.setResizable(false);
        System.out.println(opertionTime()+" : Sorted data according ascending order of date.");
    }
    public void onRefreshButtonVRLWindowClicked() throws IOException, ClassNotFoundException {
        races=readFromFileRaceData();
        refreshButtonVRLWindow.setOpacity(0.0f);//once clicked the refresh button dissapears
        dateColumnRaceData.setCellValueFactory(new PropertyValueFactory<RaceResult,String>("Date"));
        locationColumnRaceData.setCellValueFactory(new PropertyValueFactory<RaceResult,String>("Location"));
        nameColumnRaceData.setCellValueFactory(new PropertyValueFactory<RaceResult,String>("Fullname"));
        pointsColumnRaceData.setCellValueFactory(new PropertyValueFactory<RaceResult,Integer>("Points"));

        ArrayList<Race> sortedRaceData =sortRaceData(races);
        ObservableList<RaceResult> raceDataObject = FXCollections.observableArrayList();
        for (Race race : sortedRaceData) {
            ArrayList<Driver> driversFromRace=race.getDrivers();
            int index = 0;
            for (Driver driver : driversFromRace) {
                String date = race.getDate();
                String location = race.getLocation();
                String name = driver.getDetails();
                int points = 0;
                if (index == 0) {
                    points = 10;
                } else if (index == 1) {
                    points = 7;
                } else if (index == 2) {
                    points = 5;
                }
                index++;
                RaceResult raceResult = new RaceResult(date,location,name,points);
                raceDataObject.add(raceResult);
            }
        }
        System.out.println(opertionTime()+" : Viewed previous race data ");
        raceDataView.setItems(raceDataObject);
    }

    public ArrayList<Race> sortRaceData(ArrayList<Race> races){
        for (int outerLoop = 0; outerLoop < races.size(); outerLoop++) {//outer loop to make sure every element is being considered
            for (int innerLoop = 0; innerLoop < races.size() - 1; innerLoop++) {
                Race currentRace = races.get(innerLoop);//gets the current race data
                Race nextRace = races.get(innerLoop+1);
                String[] currentRaceDate = currentRace.getDate().split("/");//splits the string to an array, which breaks the value into month and day
                String[] nextRaceDate = nextRace.getDate().split("/");
                int currentMonth = Integer.parseInt(currentRaceDate[1]);//second element in the date array is the month
                int nextMonth = Integer.parseInt(nextRaceDate[1]);
                int currentDay = Integer.parseInt(currentRaceDate[0]);//gets the day of the race
                int nextDay = Integer.parseInt(nextRaceDate[0]);

                if (currentMonth>nextMonth){//checks if current month is higher than next month(eg;is 5 is higher than 3)
                    Race temp = currentRace;//if true then next date records will be transfered to current date, using temporary varialble
                    races.set(innerLoop,nextRace);
                    races.set(innerLoop+1,temp);
                }
                if (currentMonth==nextMonth){// if true then next date records will be transfered to current date, using temporary varialble
                    if (currentDay>nextDay){
                        Race temp = currentRace;//#true then next date records will be transfered to current date, using temporary varialble
                        races.set(innerLoop,nextRace);
                        races.set(innerLoop+1,temp);
                    }
                }

            }
        }
        return races;//sends back the sorted list of races according to date
    }


    //STF Window Methods
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
    private void writeToFileAdminData(ArrayList<Admin> admins) throws IOException {
        File ChampionshipFileData = new File(adminDataFilePath);
        FileOutputStream fileOutputStream = new FileOutputStream(ChampionshipFileData);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(admins);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void onSTFButtonClicked() throws IOException, ClassNotFoundException {
        successLabel.setOpacity(0.0f);
        successLabel.setText(("Files have been saved"));
        successLabel.setTextFill(Color.rgb(47, 130, 73));
        successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
        successLabel.setOpacity(1.0f);
        System.out.println(opertionTime()+" : Saved files into the system.");
    }


    //RFF Window Methods
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

    public  ArrayList<Admin> readFromFileAdminData() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(adminDataFilePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Admin> admins = (ArrayList<Admin>) objectInputStream.readObject();
        fileInputStream.close();
        return admins;
    }
    public void onRFFButtonClicked() throws IOException {
        successLabel.setOpacity(0.0f);
        successLabel.setText(("Files have been successfully loaded"));
        successLabel.setTextFill(Color.rgb(47, 130, 73));
        successLabel.setBackground(Background.fill(Color.rgb(171, 235, 196)));
        successLabel.setOpacity(1.0f);
        System.out.println(opertionTime()+" : Loaded files from the system.");
    }
    public void onExitButtonClicked(){
        System.out.println(opertionTime()+" : Terminated the program.");
        System.out.println();
        System.exit(0);
    }
    public void onLogoutButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signIn-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("SignIn");
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());//refers the main stylesheet
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);//now the Home page is open
        System.out.println(opertionTime()+" : Logged out.");
    }
    public void onRestartButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SplashScreen-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("SignIn");
        scene.getStylesheets().add(getClass().getResource("mainstylesheet.css").toExternalForm());//refers the main stylesheet
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);//now the Home page is open
        System.out.println(opertionTime()+" : Restarted the program.");
    }

    public  String toTitleCase(String str){ // to convert a string to title case
        StringBuilder stringBuilder = new StringBuilder( str.length() );// creates a new instance of stringbuilder class
        char[] characters = str.toLowerCase().toCharArray();//converts the given string to list of characters in lowercase
        characters[0] = Character.toUpperCase(characters[0]); // makes the first letter/character in uppercase
        stringBuilder.append( new String(characters));// using stringbuilder's method to convert the list of characters to a string
        return stringBuilder.toString(); // converts the stringbuilder object to a string

        // reference: https://www.javacodeexamples.com/convert-string-to-title-case-in-java-example/641
    }

    public String opertionTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentTime = formatter.format(date);
        return currentTime;
    }




}

