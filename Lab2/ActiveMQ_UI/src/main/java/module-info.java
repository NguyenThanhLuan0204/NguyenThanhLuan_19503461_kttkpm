module com.example.activemq_ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.activemq_ui to javafx.fxml;
    exports com.example.activemq_ui;
}