package a.eve.newbox;






import a.eve.newbox.Mapper.*;
import a.eve.newbox.Services.IntermediateServices;
import a.eve.newbox.Services.LoginServices;
import a.eve.newbox.controller.ChangePasswordController;
import a.eve.newbox.controller.LoginController;


import a.eve.newbox.controller.MessageController;
import a.eve.newbox.controller.ModelController;
import a.eve.newbox.pojo.Intermediate;
import a.eve.newbox.pojo.Login;
import com.example.javafxsupport.SpringJavaFX;
import jakarta.annotation.PostConstruct;
import javafx.application.Platform;
import javafx.stage.Modality;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@SpringBootApplication
public class NewBoxApplication extends SpringJavaFX {
    public static void main(String[] args) {
        lunch(NewBoxApplication.class, LoginController.class,args);
    }

}

