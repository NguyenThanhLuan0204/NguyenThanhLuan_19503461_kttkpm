package com.example.activemqvsjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Button btnSend;
    private Button btnLoadMessage;
    @FXML
    protected void handleClickSend() throws Exception {
        //Sender.Send();
        System.out.println("Send button");
    }
}