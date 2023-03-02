module com.example.rxapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;


    opens com.example.rxapplication to javafx.fxml;
    exports com.example.rxapplication;
}