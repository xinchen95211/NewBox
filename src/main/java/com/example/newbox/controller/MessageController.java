package com.example.newbox.controller;

import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@FxmlController("fxml/Message.fxml")
public class MessageController extends AbstractFxmlController {
    @FXML
    public Label msg_text;
    @FXML
    public Button ok_btn;
    @FXML
    public Button pass_btn;

    public void show(String msg){

        msg_text.setText(msg);
    }
}
