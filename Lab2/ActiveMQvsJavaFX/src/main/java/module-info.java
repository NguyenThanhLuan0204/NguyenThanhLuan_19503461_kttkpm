module com.example.activemqvsjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.activemqvsjavafx to javafx.fxml;
    exports com.example.activemqvsjavafx;
}