package com.example.newbox.controller;

import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.AbstractSupportJAVAFX;
import com.example.javafxsupport.FxmlController;
import com.example.javafxsupport.GUIState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static com.example.javafxsupport.SpringJavaFX.showView;


@FxmlController("fxml/login.fxml")
public class LoginController extends AbstractFxmlController implements Initializable {
    @Autowired
    private String bvs;
    @FXML
    private AnchorPane body;
    @FXML
    private Button login_button;
    @FXML
    private PasswordField password;
    @Autowired
    private MessageController messageController;
    public void login(){
        login_button.setText(bvs);
//        showView(MessageController.class);
        messageController.show_view();
        messageController.show("你好");
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.setResizable(false);
        login_button.setOnAction(event -> login());
    }
}
