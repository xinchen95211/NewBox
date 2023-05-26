package a.eve.newbox.controller;

import a.eve.newbox.NewBoxApplication;
import a.eve.newbox.Services.ClassificationServices;
import a.eve.newbox.Services.IntermediateServices;
import a.eve.newbox.common.Copy;
import a.eve.newbox.common.Message;
import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.Intermediate;
import a.eve.newbox.pojo.UserData;
import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@FxmlController("view.fxml")
public class ViewController extends AbstractFxmlController implements Initializable {
    @FXML
    public GridPane gridpane;
    @FXML
    public Label class_text;
    @FXML
    public Label name_text;
    @FXML
    public Label account_text;
    @FXML
    public Label password_text;
    @FXML
    public Label notes_text;
    @FXML
    public Button edit_button;

    private UserData userData;


    @Autowired
    private IntermediateServices intermediateServices;
    @Autowired
    private ClassificationServices classificationServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.setResizable(false);
        class_text.setWrapText(true);
        notes_text.setOnMouseClicked(evemt -> {
            Copy.copy_to_bard(notes_text.getText());

        });
        account_text.setOnMouseClicked(evemt -> {
            Copy.copy_to_bard(account_text.getText());


        });
        password_text.setOnMouseClicked(evemt -> {
            Copy.copy_to_bard(password_text.getText());
        });
        name_text.setOnMouseClicked(evemt -> {
            Copy.copy_to_bard(name_text.getText());
        });

        edit_button.setOnAction(Event -> {
            edit();
        });
    }

    private void edit() {
        ModelController modelController = NewBoxApplication.show_and_getView(ModelController.class);
        modelController.show(userData);
        stage.close();
    }
    public void show(UserData userData){

        this.userData = userData;

        name_text.setText(userData.getName());
        account_text.setText(userData.getAccount());
        password_text.setText(userData.getPassword());
        notes_text.setText(userData.getNotes());

        notes_text.setWrapText(true);

        //获取分类
        List<Intermediate> for_userdataid = intermediateServices.get_for_userdataid(userData.getId());
        List<Classification> classifications = classificationServices.list_for_intermedia(for_userdataid);
        //渲染分类
        setfl(classifications);
    }


    public void setfl(List<Classification> list){
        StringBuilder sb = new StringBuilder();
        if (list == null || list.size() <= 0){
            class_text.setText("无");
            return;
        }
        for (Classification classification : list) {
            sb.append(classification.getName()+"  ");
        }
        class_text.setText(sb.toString());
    }
}
