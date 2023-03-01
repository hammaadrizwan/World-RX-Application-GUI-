module com.example.rxapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rxapplication to javafx.fxml;
    exports com.example.rxapplication;
}