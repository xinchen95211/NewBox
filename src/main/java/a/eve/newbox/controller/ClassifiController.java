package a.eve.newbox.controller;

import a.eve.newbox.NewBoxApplication;
import a.eve.newbox.Services.ClassificationServices;
import a.eve.newbox.Services.IntermediateServices;
import a.eve.newbox.common.Message;
import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.UserData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FxmlController("Classifi.fxml")
public class ClassifiController extends AbstractFxmlController implements Initializable {
    @FXML
    private ListView data_list;
    @FXML
    private TextField add_text;
    @FXML
    private Button add_button;
    @FXML
    private Button ok_button;

    @Autowired
    private ClassificationServices classificationServices;
    public ObservableList<Classification> class_view_items = FXCollections.observableArrayList();

    @Autowired
    private IntermediateServices intermediateServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //设置不可缩放
        stage.setResizable(false);

        data_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ok_button.setOnAction(Event -> {
            sets();
        });

        data_list.setCellFactory(items -> {
            ListCell<Classification> cell = new ListCell<Classification>() {
                @Override
                public void updateItem(Classification item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setStyle("-fx-background-color: pink;");
                    } else {
                            if (isSelected()) {
                                setStyle("-fx-background-color: lightblue;"); // 选中时设置背景色
                            } else {
                                setStyle("-fx-background-color: pink;");  // 非选中时无样式
                            }
                            setFont(new Font(22.00));
                            setText(item.getName());
                            setTextFill(Color.BLACK);

                        if (item.getId() == (-1L) || item.getId() == (-2L)){
                            setText(null);
                            setDisable(true);
                            setFont(new Font(1));
                        }
                    }
                }
            };
            return cell;
        });



        MenuItem item2 = new MenuItem("删除数据");
        item2.setOnAction(event -> {
            handleSelection();
        });
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(item2);
       data_list.setOnContextMenuRequested(e -> {
            if (class_view_items != null && class_view_items.size() > 2){
                contextMenu.show(data_list, e.getScreenX(), e.getScreenY());
            }
        });
       data_list.setOnKeyPressed(event -> {
           if (event.getCode() == KeyCode.DELETE){
               handleSelection();
           }
       });



        add_button.setOnAction(actionEvent -> {
            add(1,add_text.getText(),-1L,-1);
        });
        data_list.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                int index = data_list.getSelectionModel().getSelectedIndex();
                Classification selectedItem = class_view_items.get(index);
                String updata = Message.updata(selectedItem.getName());
                if (updata.equals(selectedItem.getName())){
                    return;
                }
                add(2,updata,selectedItem.getId(),index);
                System.out.println(updata);
            }
        });
    }

    private void handleSelection() {
        ObservableList<Classification> observableList = data_list.getSelectionModel().getSelectedItems();
        if (observableList != null && observableList.size() > 0){

        boolean b = classificationServices.removeBatchByIds(observableList);
        if (b){
            for (int i = 0; i < observableList.size(); i++) {
                System.out.println(observableList.size());
                Long id = observableList.get(i).getId();
                System.out.println(id);
                if (id == (-1L) || id == (-2L)){
                    continue;
                }
                class_view_items.remove(i);
                i--;
                intermediateServices.remove_for_classifiID(id);
            }


//            class_view_items.removeAll(observableList);
            Message.msg("删除成功");
        }
        }







    }

    private void sets() {
        stage.hide();
        ModelController view = NewBoxApplication.getView(ModelController.class);
        ObservableList<Classification> selectedItems = data_list.getSelectionModel().getSelectedItems();

        view.setfl(selectedItems);
    }

    //从主界面获取items
    public void bind(){
        HomeController view = NewBoxApplication.getView(HomeController.class);
        this.class_view_items = view.class_view_items;
        data_list.setItems(class_view_items);


    }
    public void add(int i,String text,Long id,int index){
        //获取分类参数
        if (text.equals("")){
            Message.msg("分类名称不能为空");
            return;
        }
        //通过分类参数去数据库里查询看看有没有重复的数据
        LambdaQueryWrapper<Classification> lam = new LambdaQueryWrapper<>();
        lam.eq(Classification::getName,text);
        Classification one = classificationServices.getOne(lam);
        if (one != null){
            Message.msg("此分类已存在");
            return;
        }
        Classification classification = new Classification();
        classification.setName(text);
        boolean save = false;
        String msg = null;
        switch (i){
            //添加
            case 1 : {
                msg = "添加";
                save = classificationServices.save(classification);
                if (save){
                    class_view_items.add(classification);
                }
                break;
            }
            //更新
            case 2 : {
                msg = "修改";
                classification.setId(id);
                save = classificationServices.updateById(classification);
                if (save){
                    class_view_items.set(index,classification);
                }

                break;
            }
        }

        if (save){
            add_text.setText("");
            Message.msg(msg + "成功");
        }else{
            Message.msg(msg +"失败");
        }


    }
}
