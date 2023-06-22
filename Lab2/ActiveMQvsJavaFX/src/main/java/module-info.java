/**
 *
 */
module com.example.activemqvsjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.jms.api;
    requires java.naming;
    requires log4j;

    opens com.example.activemqvsjavafx to javafx.fxml;
    exports com.example.activemqvsjavafx;
}