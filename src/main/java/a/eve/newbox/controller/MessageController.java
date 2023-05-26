package a.eve.newbox.controller;



import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;


@FxmlController("Message.fxml")
public class MessageController extends AbstractFxmlController implements Initializable {

    @FXML
    public Label msg_text;
//    @FXML
    public Button ok_btn;
//    @FXML
    public Button pass_btn;

    public void show(String msg){

        msg_text.setText(msg);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ok_btn.setOnAction(event -> {
//            showView(LoginController.class);
        });


        ok_btn = (Button) ((Button) ok_btn).lookup("#ok_btn");
        pass_btn = (Button)  pass_btn.lookup("#pass_btn");


        ok_btn.setManaged(false);
        pass_btn.setManaged(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
    }
    public void Toest(String msg){

        show_view();
        ok_btn.setManaged(false);
        pass_btn.setManaged(false);
        show(msg);



    }
    public boolean confirmed(String msg){
        show_view();
        ok_btn.setManaged(true);
        pass_btn.setManaged(true);
        show(msg);
        AtomicBoolean confirmed = new AtomicBoolean(false);

//
        ok_btn.setOnAction(event -> {
            confirmed.set(true);
            stage.close();
        });

        pass_btn.setOnAction(event ->{
            stage.close();
        });

        return confirmed.get();
    }
}
