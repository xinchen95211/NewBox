package a.eve.newbox.controller;


import a.eve.newbox.NewBoxApplication;
import a.eve.newbox.Services.LoginServices;
import a.eve.newbox.common.LoginUtil;
import a.eve.newbox.common.Message;
import a.eve.newbox.pojo.Login;
import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;


@FxmlController("login.fxml")
public class LoginController extends AbstractFxmlController implements Initializable {
//    private static Logger LOGGER = LoggerFactory.getLogger(this.getClass());



    @FXML
    private AnchorPane body;
    @FXML
    private Button login_button;
    @FXML
    private PasswordField password;


    private ApplicationContext applicationContext;
    public LoginController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        stage.setOnCloseRequest(events -> {
            shutdown();
        });

        //设置不可缩放
        stage.setResizable(false);
        login_button.setOnAction(event -> login());
        Platform.runLater(LoginUtil::first_login_check);
        body.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER){
                login();

            }
        });
    }
    public void login(){
        boolean b = LoginUtil.Login_check(password.getText());
        if (b){
            stage.close();
            Message.msg("密码正确,欢迎您登陆");
            NewBoxApplication.showView(HomeController.class);
        }else {
            Message.msg("密码错误");
        }
    }
}
