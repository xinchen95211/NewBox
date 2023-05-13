package com.example.newbox.controller;


import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;


@FxmlController("login.fxml")
public class LoginController extends AbstractFxmlController implements Initializable {
    @FXML
    public AnchorPane bodys;
    @FXML
    public Button login_button;
    @FXML
    public Label username_text;
    @FXML
    public PasswordField password_text;
    @FXML
    public Label msg_text;

    @Autowired
    private MessageController messageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login_button.setOnAction(Event -> {

        messageController.show_view();
        messageController.show("你好123");



        });



    }
}
