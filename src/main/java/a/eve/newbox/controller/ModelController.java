package a.eve.newbox.controller;

import a.eve.newbox.NewBoxApplication;
import a.eve.newbox.Services.ClassificationServices;
import a.eve.newbox.Services.IntermediateServices;
import a.eve.newbox.Services.UserDataServices;
import a.eve.newbox.common.Copy;
import a.eve.newbox.common.Message;
import a.eve.newbox.common.password_support;
import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.Intermediate;
import a.eve.newbox.pojo.UserData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static a.eve.newbox.common.password_support.password_generation;

@FxmlController("Model.fxml")
public class ModelController extends AbstractFxmlController implements Initializable {
    @FXML
    public GridPane gridpane;
    @FXML
    public Label class_text;
    @FXML
    public TextField name_text;
    @FXML
    public TextField account_text;
    @FXML
    public TextField password_text;
    @FXML
    public TextField notes_text;
    @FXML
    public Slider slide;
    @FXML
    public Button rdm_password_btn;
    @FXML
    public CheckBox check_bigA;
    @FXML
    public CheckBox check_littlea;
    @FXML
    public CheckBox check_emjo;
    @FXML
    public CheckBox check_num;
    @FXML
    public Label lenght;
    @FXML
    public Button pass_button;
    @FXML
    public Button edit_button;
    @FXML
    public Button save_button;

    private UserData userData = null;

    @Autowired
    private IntermediateServices intermediateServices;
    @Autowired
    private ClassificationServices classificationServices;


    public List<Classification> list = new ArrayList<>();

    @Autowired
    private UserDataServices userDataServices;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.setResizable(false);

        lenght.textProperty().bind(slide.valueProperty().asString("%.0f"));


        edit_button.setVisible(false);
        //初始化分类渲染

        class_text.setOnMouseClicked(Event -> {
            ClassifiController view = NewBoxApplication.show_and_getView(ClassifiController.class);
            view.bind();
        });
        class_text.setWrapText(true);



        edit_button.setOnAction(event -> {
            edit();
        });


        rdm_password_btn.setOnAction(event -> rad_password());




        //按钮功能绑定
        save_button.setOnAction(Event -> {
            save();
        });
       pass_button.setOnAction(Event -> {
            stage.close();
        });



    }
    public void rad_password(){
        //初始化字符集
        StringBuilder set = new StringBuilder();
        System.out.println(set);
        //大写字母
        if (check_bigA.isSelected()){
            set.append(password_support.big_zimu);
        }
        if (check_littlea.isSelected()){
            set.append(password_support.little_zimu);
        }
        if (check_emjo.isSelected()){
            set.append(password_support.sym);
        }
        if (check_num.isSelected()){
            set.append(password_support.num);
        }
        if ("".equals(set.toString())){
            set.append(password_support.big_zimu);
            set.append(password_support.little_zimu);
            set.append(password_support.sym);
            set.append(password_support.num);
        }
        Integer length = Integer.parseInt(lenght.getText());
        String s = password_support.password_generation(set.toString(), length);
        password_text.setText(s);
    }
    public void add(){
        userData = null;
        //初始化
        name_text.setText("");
        account_text.setText("");
        password_text.setText("");
        notes_text.setText("");
        class_text.setText("");
    }
    public void show(UserData userData){

        //模型数据
        this.userData = userData;
        name_text.setText(userData.getName());
        account_text.setText(userData.getAccount());
        password_text.setText(userData.getPassword());
        notes_text.setText(userData.getNotes());
        //获取分类
        List<Intermediate> for_userdataid = intermediateServices.get_for_userdataid(userData.getId());
        List<Classification> classifications = classificationServices.list_for_intermedia(for_userdataid);
        //渲染分类
        setfl(classifications);


    }
    public void edit(){

    }


    public void save(){
        if ("".equals(name_text.getText())){
            Message.msg("名称不能为空");
            return;
        }
        if ("".equals(account_text.getText())){
            Message.msg("账户不能为空");
            return;
        }
        if ("".equals(password_text.getText())){
            Message.msg("密码不能为空");
            return;
        }
        UserData userData1 = new UserData();
        userData1.setName(name_text.getText());
        userData1.setAccount(account_text.getText());
        userData1.setPassword(password_text.getText());
        userData1.setNotes(notes_text.getText());
        boolean save = false;
        boolean updata = false;
        if (userData != null){
            //更新
            userData1.setId(userData.getId());
            save = userDataServices.updateById(userData1);
            if (save){
                Message.msg("修改成功");
                updata = true;
            }else {
                Message.msg("修改失败");
            }
        }else{
            //添加
            save = userDataServices.save(userData1);
            if (save){
                Message.msg("添加成功");
            }else {
                Message.msg("添加失败");
            }
        }
        if (list !=null && list.size() > 0){

        if (save && updata){
            List<Intermediate> for_userdataid = intermediateServices.get_for_userdataid(userData1.getId());
            intermediateServices.removeBatchByIds(for_userdataid);
        }
        List<Intermediate> collect = list.stream().map(items -> {
            Intermediate intermediate = new Intermediate();
            intermediate.setUserDataID(userData1.getId());
            intermediate.setClassificationID(items.getId());
            return intermediate;
        }).collect(Collectors.toList());
        intermediateServices.saveBatch(collect);
        }
        HomeController view = NewBoxApplication.getView(HomeController.class);
        view.Rendered_data(null);
        stage.close();
    }


    //设置分类
    public void setfl(List<Classification> list){
        this.list.removeAll(this.list);
        StringBuilder sb = new StringBuilder();
        if (list == null || list.size() <= 0){
            class_text.setText("无");
            return;
        }
        for (Classification classification : list) {
            if (classification.getId() == (-1L) || classification.getId() == (-2L)){
                continue;
            }
            this.list.add(classification);
            sb.append(classification.getName()+"  ");
        }
        class_text.setText(sb.toString());
    }
}
