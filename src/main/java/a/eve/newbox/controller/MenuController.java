package a.eve.newbox.controller;

import a.eve.newbox.NewBoxApplication;
import a.eve.newbox.Services.ClassificationServices;
import a.eve.newbox.Services.UserDataServices;
import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.UserData;
import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FxmlController("menu.fxml")
public class MenuController extends AbstractFxmlController implements Initializable {
    @FXML
    public Button updata_password;
    @Autowired
    private ClassificationServices classificationServices;

    @Autowired
    private UserDataServices userDataServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //设置不可缩放
        stage.setResizable(false);
        updata_password.setOnAction(event -> updatapassword());
    }

    private void updatapassword() {

//        for (int i = 0; i < 10; i++) {
//            Classification classification = new Classification();
//            classification.setName("全部分类" + i);
//            classificationServices.save(classification);
//        }
//        for (int i = 0; i < 100; i++) {
//            UserData userData = new UserData();
//            userData.setName("名称" + i);
//            userData.setAccount("账户" + i);
//            userData.setPassword("123" + i);
//            userDataServices.save(userData);
//        }


        ChangePasswordController changePasswordController = NewBoxApplication.show_and_getView(ChangePasswordController.class);
        changePasswordController.inits();
    }
}
